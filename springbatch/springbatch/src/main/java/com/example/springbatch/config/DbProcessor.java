package com.example.springbatch.config;

import com.example.springbatch.entity.Books;

import org.springframework.batch.item.ItemProcessor;

public class DbProcessor implements ItemProcessor<Books, Books> {

	@Override
	public Books process(Books book) throws Exception {
		book.getTitle().toUpperCase();

		return book;

	}
}
