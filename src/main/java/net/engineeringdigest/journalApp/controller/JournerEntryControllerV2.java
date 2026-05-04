package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntries;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import net.engineeringdigest.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

// Controller responsible for handling all journal-related REST endpoints
@RestController // Marks this class as a REST controller (returns data instead of views)
@RequestMapping("/journalv2") // Base path for all APIs in this controller

public class JournerEntryControllerV2 {

    @Autowired
    private JournalEntryService JournalEntryService;

    @Autowired
    private UserService userService;



    @GetMapping
    public ResponseEntity<?> getAllJournalEntries() {

        List<JournalEntries> all = JournalEntryService.getAll();

        return  new  ResponseEntity<>(all,HttpStatus.OK);
    }

    @GetMapping("/{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName) {

        User user = userService.findByUserName(userName);

        List<JournalEntries> all = user.getJournalEntries();

        if(all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return  new  ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{userName}")
    public ResponseEntity<JournalEntries> createEntry(@RequestBody JournalEntries myNewEntry, @PathVariable("userName") String userName) {
        System.out.println("Journal new Entry for " + userName +" : "+ myNewEntry);
        try {
            JournalEntryService.saveEntry(myNewEntry, userName);
            return new ResponseEntity<>(myNewEntry, HttpStatus.CREATED); // data save to the database
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // data save to the database
        }
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntries> getJournalEntryById(@PathVariable ObjectId myId) {
        Optional<JournalEntries> journalEntry = JournalEntryService.findById(myId);
        if(journalEntry.isPresent()){
            return  new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }

        System.out.println(">>>>>>>>>>>>>>>>>>> Not Found <<<<<<<<<<<<<<<<<");
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{userName}/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId, @PathVariable String userName) {
        JournalEntryService.deleteById(myId, userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PutMapping("/id/{userName}/{myId}")
    public ResponseEntity<?> updateJournalEntryById(
            @PathVariable ObjectId myId,
            @PathVariable String userName,
            @RequestBody JournalEntries newEntry
    ) {

        JournalEntries old = JournalEntryService.findById(myId).orElse(null);

        if(old != null){
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ?  newEntry.getTitle() :old.getTitle() );
            old.setContent(newEntry.getContent() != null &&  !newEntry.getContent().equals("")? newEntry.getContent():old.getContent());
            JournalEntryService.saveEntry(old);
            return  new ResponseEntity<>(old, HttpStatus.OK);
        }

        return  new ResponseEntity<>(HttpStatus.NOT_FOUND); // data save to the database
    }



    @GetMapping("/test")
    public String test() {
        System.out.println("TEST HIT");
        return "OK";
    }

}
