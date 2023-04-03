package com.bookclub.dao.service;

import com.bookclub.dao.models.Book;
import com.bookclub.dao.repository.BookRepostiory;
import com.bookclub.service.SequenceGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BookService {

    private SequenceGenerator sequenceGenerator = new SequenceGenerator();

    private final BookRepostiory bookRepostiory = new BookRepostiory();

    public String createNewBook(String title,String author,String publisher) throws IOException {
        long bookNo = sequenceGenerator.generateSequence("bookNo");
        String uuid = UUID.randomUUID().toString();
        Book book = new Book(uuid,bookNo,title,author,publisher);
        return bookRepostiory.createOrUpdateDocument(book);
    }
}
