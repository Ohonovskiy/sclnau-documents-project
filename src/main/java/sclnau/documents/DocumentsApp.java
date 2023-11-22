package sclnau.documents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan
public class DocumentsApp {

    public static void main(String[] args) {
        SpringApplication.run(DocumentsApp.class, args);
    }
}
