package com.example.springbatch.entity;

import javax.persistence.Entity;

import javax.persistence.Id;

@Entity

public class Books {
	@Id

	private Integer id;

	private String title;

	private String author;

	private String genre;

	private Integer height;

	private String publisher;

	public Books() {
	}

	public Books(Integer id, String title, String author, String genre, Integer height, String publisher) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.height = height;
		this.publisher = publisher;
	}

	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	@Override
	public String toString() {
		return "Books [id=" + id + ", title=" + title + ", author=" + author + ", genre=" + genre + ", height=" + height
				+ ", publisher=" + publisher + "]";
	}

}