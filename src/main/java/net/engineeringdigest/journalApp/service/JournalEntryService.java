package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntries;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository JournalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional // treated as a single operations(atomic and isolation)
    public void saveEntry(JournalEntries JournalEntries, String userName){
        try {
            User user = userService.findByUserName(userName);

            JournalEntries.setDate(LocalDateTime.now());
            JournalEntries saved = JournalEntryRepository.save(JournalEntries);
            user.getJournalEntries().add(saved);
//            user.setUserName(null); // exceptions
            userService.saveEntry(user);
        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("A error occure while saving entry.", e);
        }
    }

    public void saveEntry(JournalEntries JournalEntries){
        JournalEntries.setDate(LocalDateTime.now());
         JournalEntryRepository.save(JournalEntries);
    }

    public List<JournalEntries> getAll(){
        return JournalEntryRepository.findAll();
    }

    public Optional<JournalEntries> findById(ObjectId id){
        return JournalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id, String userName){
        User user = userService.findByUserName(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userService.saveEntry(user);
        JournalEntryRepository.deleteById(id);
    }
}
//controller -> service -> repositiry