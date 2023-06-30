package com.example.springbatch.config;

import com.example.springbatch.entity.Person;

import org.springframework.batch.item.ItemProcessor;

public class PersonProcessor implements ItemProcessor<Person,Person> {

    @Override
    public Person process(Person Person) throws Exception {
      if(Person.getAge()>23) {
    	  return Person; 
      }
      else {
    	  return null;
      }
    }

}