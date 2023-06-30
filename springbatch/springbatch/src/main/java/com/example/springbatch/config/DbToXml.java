package com.example.springbatch.config;

import java.util.HashMap;
import java.util.Map;

import com.example.springbatch.entity.Person;
import com.example.springbatch.repository.PersonRepo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Sort;
import org.springframework.oxm.xstream.XStreamMarshaller;

@Configuration
@EnableBatchProcessing
public class DbToXml {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private PersonRepo repo;

	@Bean
	public PersonProcessor personProcessorDb(){
		return new PersonProcessor();
	}

	 @Bean
	    public RepositoryItemReader<Person> repositoryReader(){
	        RepositoryItemReader<Person> repositoryItemReader = new RepositoryItemReader<>();
	        repositoryItemReader.setRepository(repo);
	        repositoryItemReader.setMethodName("findAll");
	        final HashMap<String, Sort.Direction> sorts = new HashMap<>();
	        sorts.put("personId", Sort.Direction.ASC);
	        repositoryItemReader.setSort(sorts);

	        return repositoryItemReader;
	    }

	 @Bean
		public StaxEventItemWriter<Person> dbToXmlwriter(){
			StaxEventItemWriter<Person> writer = new StaxEventItemWriter<>();
			writer.setResource(new FileSystemResource("E://DbToCsv/person.xml"));

			Map<String,String> aliasesMap =new HashMap<>();
			aliasesMap.put("person", "com.example.springbatch.entity.Person");
			XStreamMarshaller marshaller = new XStreamMarshaller();
			marshaller.setAliases(aliasesMap);
			writer.setMarshaller(marshaller);
			writer.setRootTagName("persons");
			writer.setOverwriteOutput(true);
			return writer;
		}
	 @Bean
		public Step step10(){
			return stepBuilderFactory.get("step10").<Person,Person>chunk(100).reader(repositoryReader()).processor(personProcessorDb()).writer(dbToXmlwriter()).build();
		}
	 @Qualifier("dbtoxml")
		@Bean
		public Job dbToXmlJob(){
			return jobBuilderFactory.get("dbtoxml").incrementer(new RunIdIncrementer()).flow(step10()).end().build();
		}
}
