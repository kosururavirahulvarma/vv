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

import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.xstream.XStreamMarshaller;


@Configuration
@EnableBatchProcessing
public class XmlToDbBatchConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private PersonRepo repo;

	@Bean
	public PersonProcessor personprocessor(){
		return new PersonProcessor();
	}

	@Bean
	public StaxEventItemReader<Person> personReader(){
		StaxEventItemReader<Person> reader = new StaxEventItemReader<Person>();
		reader.setResource(new ClassPathResource("person.xml"));
		reader.setFragmentRootElementName("person");
		Map<String,String> aliasesMap =new HashMap<>();
		aliasesMap.put("person", "com.example.springbatch.entity.Person");
		XStreamMarshaller marshaller = new XStreamMarshaller();
		marshaller.setAliases(aliasesMap);
		marshaller.getXStream().allowTypes(new Class[] {Person.class});

		reader.setUnmarshaller(marshaller);
		return reader;
	}

	  @Bean
	    public RepositoryItemWriter<Person> personWriter() {
	        RepositoryItemWriter<Person> writer = new RepositoryItemWriter<>();
	        writer.setRepository(repo);
	        writer.setMethodName("save");
	        return writer;
	    }

	  @Bean
		public Step step4(){
			return stepBuilderFactory.get("xml-step").<Person,Person>chunk(10).reader(personReader()).processor(personprocessor()).writer(personWriter()).build();
		}


		@Bean
		@Qualifier("person")
		public Job exportPerosnJob(){
			return jobBuilderFactory.get("xml").incrementer(new RunIdIncrementer()).flow(step4()).end().build();
		}
}