package com.example.springbatch.config;

import java.util.HashMap;

import com.example.springbatch.entity.Books;
import com.example.springbatch.repository.SpringBatchRepo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.domain.Sort;

@Configuration
@EnableBatchProcessing

public class SpringBatchConfig {

	   private JobBuilderFactory jobBuilderFactory;

	    private StepBuilderFactory stepBuilderFactory;

	    private SpringBatchRepo repo;


		public SpringBatchConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
				SpringBatchRepo repo) {
			this.jobBuilderFactory = jobBuilderFactory;
			this.stepBuilderFactory = stepBuilderFactory;
			this.repo = repo;
		}



		//csv to db

		@Bean
	    public FlatFileItemReader<Books> reader() {
	        FlatFileItemReader<Books> itemReader = new FlatFileItemReader<>();
	        itemReader.setResource(new FileSystemResource("src/main/resources/books.csv"));
	        itemReader.setName("csvReader");
	        itemReader.setLinesToSkip(1);
	        itemReader.setLineMapper(lineMapper());
	        return itemReader;
	    }

	    private LineMapper<Books> lineMapper() {
	        DefaultLineMapper<Books> lineMapper = new DefaultLineMapper<>();

	        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
	        lineTokenizer.setDelimiter(";");
	        lineTokenizer.setStrict(false);
	        lineTokenizer.setNames("id","title", "author", "genre", "height", "publisher");

	        BeanWrapperFieldSetMapper<Books> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
	        fieldSetMapper.setTargetType(Books.class);

	        lineMapper.setLineTokenizer(lineTokenizer);
	        lineMapper.setFieldSetMapper(fieldSetMapper);
	        return lineMapper;

	    }

	    @Bean
	    public BooksProcessor processor() {
	        return new BooksProcessor();
	    }

	    @Bean
	    public GenreProcessor processorGenre() {
	        return new GenreProcessor();
	    }

	    @Bean
	    public RepositoryItemWriter<Books> writer() {
	        RepositoryItemWriter<Books> writer = new RepositoryItemWriter<>();
	        writer.setRepository(repo);
	        writer.setMethodName("save");
	        return writer;
	    }

	    @Bean
	    public Step step1() {
	        return stepBuilderFactory.get("csv-step").<Books, Books>chunk(10)
	                .reader(reader())
	                .processor(processor())
	                .writer(writer())
	                .taskExecutor(taskExecutor())
	                .build();
	    }

	    @Bean
	    public Step stepGenre() {
	        return stepBuilderFactory.get("csv-step-genre").<Books, Books>chunk(10)
	                .reader(reader())
	                .processor(processorGenre())
	                .writer(writer())
	                .taskExecutor(taskExecutor())
	                .build();
	    }

	    @Bean
	    @Qualifier("publisher")
	    public Job runJob1() {
	        return jobBuilderFactory.get("importbooks/publisher").incrementer(new RunIdIncrementer())
	                .flow(step1()).end().build();

	    }

	    @Bean
	    @Qualifier("genre")
	    public Job runJob2() {
	        return jobBuilderFactory.get("importbooks/genre").incrementer(new RunIdIncrementer())
	                .flow(stepGenre()).end().build();

	    }

	    @Bean
	    public TaskExecutor taskExecutor() {
	        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
	        asyncTaskExecutor.setConcurrencyLimit(10);
	        return asyncTaskExecutor;
	    }

	   //db to csv
	    @Bean
	    public RepositoryItemReader<Books> repoReader(){
	        RepositoryItemReader<Books> repositoryItemReader = new RepositoryItemReader<>();
	        repositoryItemReader.setRepository(repo);
	        repositoryItemReader.setMethodName("findAll");
	        final HashMap<String, Sort.Direction> sorts = new HashMap<>();
	        sorts.put("id", Sort.Direction.ASC);
	        repositoryItemReader.setSort(sorts);

	        return repositoryItemReader;
	    }
	    @Bean
		public FlatFileItemWriter<Books> newWriter() {

			FlatFileItemWriter<Books> writer = new FlatFileItemWriter<>();
			writer.setResource(new FileSystemResource("E://DbToCsv/cdv_output.csv"));
			DelimitedLineAggregator<Books> aggregator = new DelimitedLineAggregator<>();
			BeanWrapperFieldExtractor<Books> fieldExtractor = new BeanWrapperFieldExtractor<>();
			fieldExtractor.setNames(new String[] {"id","author", "genre", "height", "publisher", "title"});

			aggregator.setFieldExtractor(fieldExtractor);
			writer.setLineAggregator(aggregator);

			return writer;

		}

	    @Bean
	    public DbProcessor dbProcessor() {
	        return new DbProcessor();
	    }

	    @Bean
	    public Step step3() {
	        return stepBuilderFactory.get("db-step").<Books, Books>chunk(10)
	                .reader(repoReader())
	                .processor(dbProcessor())
	                .writer(newWriter())

	                .build();
	    }

	    @Bean
	    @Qualifier("dbtocsv")
	    public Job runJob3() {
	        return jobBuilderFactory.get("exportbooks/dbtocsv")

	                .flow(step3()).end().build();

	    }
}