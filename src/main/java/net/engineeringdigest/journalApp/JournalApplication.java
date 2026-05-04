package net.engineeringdigest.journalApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class JournalApplication {

    public static void main(String[] args) {
        SpringApplication.run(JournalApplication.class, args);
    }

    @Bean
    public PlatformTransactionManager add(MongoDatabaseFactory dbFactory){
        return new MongoTransactionManager(dbFactory);
    }
}
//
//https://www.youtube.com/watch?v=HjDyv7gL4Wg&list=PLA3GkZPtsafacdBLdd3p1DyRd5FGfr3Ue&index=18
//Connecting Spring Boot to MongoDB Atlas: A Step-by-Step Tutorial



//controller -> service -> repositiry