package com.example.demo.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.example.demo.models.Author;
import com.example.demo.models.Book;
import com.example.demo.models.Publisher;
import com.example.demo.repositories.AuthorRepository;
import com.example.demo.repositories.BookRepository;
import com.example.demo.repositories.PublisherRepository;


@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {
	
	private AuthorRepository authorRepository;
	private BookRepository bookRepository;
	private PublisherRepository publisherRepository;
	
	
	
	
	public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository,
			PublisherRepository publisherRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		initData();
		
	}

	private void initData() {
		
		System.out.println("############################# Bootstraping   ");
		Publisher abc = new Publisher("ABC publications");
		Publisher xyz = new Publisher("XYZ publications");
		publisherRepository.save(abc);
		publisherRepository.save(xyz);

		
		
		Author naina = new Author("Naina", "Rajan");
		Book atomic = new Book("Atomic Habits", "1234", abc);
		naina.getBooks().add(atomic);
		atomic.getAuthors().add(naina);
		
		
		authorRepository.save(naina);
		bookRepository.save(atomic);
		
		Author thomas = new Author("Thomas", "John");
		Book ferrari = new Book("Monk who sold his Ferrari", "3710", xyz);
		naina.getBooks().add(ferrari);
		atomic.getAuthors().add(thomas);
		
		
		
		authorRepository.save(thomas);
		bookRepository.save(ferrari);
		
	}

}
