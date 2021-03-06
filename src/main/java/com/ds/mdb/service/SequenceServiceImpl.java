package com.ds.mdb.service;

import com.ds.mdb.model.Sequence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class SequenceServiceImpl implements SequenceService {

    @Autowired
    private MongoOperations mongoOperations;

    public Long getNextSequence(String collectionName) {

        Sequence sequence = mongoOperations.findAndModify(
                query(where("_id").is(collectionName)),
                new Update().inc("sequence", 1),
                options().returnNew(true),
                Sequence.class);

        if (sequence == null) {
            // if there is no sequence declared in DB then mark initial sequence number as 1.
            sequence = new Sequence(collectionName, 1);
            mongoOperations.insert(sequence);
        }

        return sequence.getSequence();
    }

    @Override
    public Long getLastSequence(String inKey) {
        Sequence lastSequence = mongoOperations.findOne(
                query(where("_id").is(inKey)),
                Sequence.class);
        return lastSequence != null ? lastSequence.getSequence() : 0L;
    }
}
