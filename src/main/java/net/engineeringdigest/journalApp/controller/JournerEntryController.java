package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntries;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// Controller responsible for handling all journal-related REST endpoints
@RestController // Marks this class as a REST controller (returns data instead of views)
@RequestMapping("/_journal") // Base path for all APIs in this controller
public class JournerEntryController {

    private Map<ObjectId, JournalEntries> journalentries = new HashMap<>();

    @GetMapping
    public List<JournalEntries> getAll() {
        System.out.println("Fetching all journal entries...");
        // Convert map values to a list and return
        return new ArrayList<>(journalentries.values());
    }


    @PostMapping
    public boolean createEntry(@RequestBody JournalEntries myNewEntry) {
        // Add the new entry to the in-memory store using its ID as key
        journalentries.put(myNewEntry.getId(), myNewEntry);
        System.out.println("Added new journal entry: " + myNewEntry.getTitle());
        return true;
    }


    @GetMapping("/id/{myId}")
    public JournalEntries getJournalEntryById(@PathVariable Long myId) {
        System.out.println("Fetching journal entry with ID: " + myId);
        return journalentries.get(myId);
    }

    @DeleteMapping("/id/{myId}")
    public JournalEntries deleteJournalEntryById(@PathVariable Long myId) {
        System.out.println("Deleting journal entry with ID: " + myId);
        return journalentries.remove(myId);
    }


    @PutMapping("/id/{myId}")
    public JournalEntries updateJournalEntryById(@PathVariable ObjectId myId, @RequestBody JournalEntries myNewEntry) {
        System.out.println("Updating journal entry with ID: " + myId);
        // Replace old entry with new one; returns old value if it existed
        return journalentries.put(myId, myNewEntry);
    }

}
