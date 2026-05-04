package net.engineeringdigest.journalApp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

//pojo(plain old java object) class

@Document(collection = "journal_entries") // mapped with mongoodb collctions
@Getter
@Setter
@NoArgsConstructor
public class JournalEntries {
    @Id // cosnider as apriamry key // it can be overriden by the cliet's data id
    private ObjectId id;

    @NonNull
    private String title;

    private String content;
    private LocalDateTime date;

    @DBRef // reference of journal entries
    private List<JournalEntries> journalEntries;
}