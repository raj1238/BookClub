package com.bookclub;

import com.bookclub.dao.service.BookService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class BookClubApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(BookClubApplication.class, args);

		System.out.println("Hi, Let's goooooo!!");

		BookService bookService = new BookService();

		bookService.createNewBook("new book","raj shah","skanda sky");

	}

}
