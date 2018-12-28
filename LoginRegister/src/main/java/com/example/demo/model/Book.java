package com.example.demo.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;


@Entity
public class Book {
	
	private int id ;
	private String name ;
	private Set<Publisher> publishers;
	
	public Book() {
		
	}

	public Book(String name) {
		super();
		this.name = name;
	}

	public Book(String name, Set<Publisher> publishers) {
		super();
		this.name = name;
		this.publishers = publishers;
	}

	@Id
	@GeneratedValue (strategy =GenerationType.AUTO)
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

	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "book_publisher", joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
    		inverseJoinColumns = @JoinColumn(name = "publisher_id", referencedColumnName = "id"))
	public Set<Publisher> getPublishers() {
		return publishers;
	}

	public void setPublishers(Set<Publisher> publishers) {
		this.publishers = publishers;
	}
}
