package sclnau.documents.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "document_table")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String path;

    private String name;

    private Timestamp creationDate = Timestamp.valueOf(LocalDateTime.now());

    @ManyToOne
    private Group group;

    @ManyToOne
    private User user;
}
