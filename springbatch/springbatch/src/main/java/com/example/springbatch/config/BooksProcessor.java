package com.example.springbatch.config;

import com.example.springbatch.entity.Books;

import org.springframework.batch.item.ItemProcessor;

public class BooksProcessor implements ItemProcessor<Books,Books> {

    @Override
    public Books process(Books book) throws Exception {
      if(book.getPublisher().equals("Penguin")) {
    	  return book; 
      }
      else {
    	  return null;
      }
    }

}