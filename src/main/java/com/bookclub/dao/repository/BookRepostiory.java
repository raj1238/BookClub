package com.bookclub.dao.repository;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import com.bookclub.dao.models.Book;
import com.bookclub.database.es.ElasticSearchConnector;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public class BookRepostiory {

    private final String indexName = "books";

    public String createOrUpdateDocument(Book book) throws IOException {

        ElasticsearchClient elasticsearchClient = ElasticSearchConnector.getClient();

        IndexResponse response = elasticsearchClient.index(i -> i
                .index(indexName)
                .id(book.getId())
                .document(book)
        );
        if(response.result().name().equals("Created")){
            return new StringBuilder("Document has been successfully created.").toString();
        }else if(response.result().name().equals("Updated")){
            return new StringBuilder("Document has been successfully updated.").toString();
        }
        return new StringBuilder("Error while performing the operation.").toString();
    }
}
