package com.example.demo.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Publisher {

	
	private int id ;
	private String name;
	private Set<Book> books;
	
	public Publisher() {
	}
	
	public Publisher(String name ) {
		this.name = name ;
	}
	
	public Publisher(String name , Set<Book> books) {
		this.name = name ;
		this.books = books;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    @ManyToMany(mappedBy = "publishers")
	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

}
