package com.bookclub.service;

import com.bookclub.dao.util.DatabaseSequence;
import com.bookclub.database.mongo.MongoConnector;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@RequiredArgsConstructor
@Service
public class SequenceGenerator {

    private final MongoTemplate template = new MongoTemplate(MongoConnector.getClient(),"book_club");

    public long generateSequence(String seqName) {
        DatabaseSequence counter = template.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("sequenceNo",1), options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSequenceNo() : 1;
    }

}
