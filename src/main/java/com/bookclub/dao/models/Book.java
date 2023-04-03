package com.bookclub.dao.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    private String id;

    @NonNull
    private long bookNo;

    @NonNull
    private String title;

    private String author;

    private String publisher;

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", bookNo='" + bookNo + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
