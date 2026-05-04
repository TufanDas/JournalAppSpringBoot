package net.engineeringdigest.journalApp.repository;

import net.engineeringdigest.journalApp.entity.JournalEntries;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepository extends MongoRepository<JournalEntries, ObjectId> {

}

//controller -> service -> repositiry
