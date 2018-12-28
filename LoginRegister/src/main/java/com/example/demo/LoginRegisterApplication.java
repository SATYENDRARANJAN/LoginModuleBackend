package com.example.demo;

import java.util.HashSet;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.model.Book;
import com.example.demo.model.Publisher;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.PublisherRepository;
@SpringBootApplication
public class LoginRegisterApplication implements CommandLineRunner {
	private static Logger logger =  LoggerFactory.getLogger(LoginRegisterApplication.class);
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private PublisherRepository publisherRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(LoginRegisterApplication.class, args);
	}
	
	@Override
	@Transactional
	public void run(String ...strings)  throws Exception{
		final Publisher publisherA = new Publisher("PublisherA");
		final Publisher publisherB = new Publisher("PublisherB");
		final Publisher publisherC = new Publisher("PublisherC");
		
		
		bookRepository.saveAll(new HashSet<Book>() {{
			add(new Book("BookA", new HashSet<Publisher>() {{
				add(publisherA);
				add(publisherB);
			}}));
			add(new Book("BookB", new HashSet<Publisher>() {{
				add(publisherB);
				add(publisherC);
			}}));
		}});
		
		// fetch all books
        for(Book book : bookRepository.findAll()) {
            logger.info(book.toString());
        }
	}
}

