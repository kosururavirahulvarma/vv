package com.example.springbatch.config;

import com.example.springbatch.entity.Books;

import org.springframework.batch.item.ItemProcessor;

public class GenreProcessor implements ItemProcessor<Books,Books> {

    @Override
    public Books process(Books book) throws Exception {
      if(book.getGenre().equals("data_science")) {
    	  return book; 
      }
      else {
    	  return null;
      }
    }
}