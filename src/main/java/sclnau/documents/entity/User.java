package sclnau.documents.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.SortComparator;
import sclnau.documents.enums.Role;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Entity
@Data
@Table
public class User {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    @OneToMany(mappedBy = "user")
    private List<Document> documents = new ArrayList<>();

    private Role role = Role.ROLE_USER;

    public List<Document> getDocuments(){
        documents.sort((doc1, doc2) -> doc2.getCreationDate().compareTo(doc1.getCreationDate()));

        return documents;
    }
    public void addDocument(Document document){
        document.setUser(this);
        documents.add(document);
    }

    public void removeDocument(Document document){
        document.setUser(null);
        documents.remove(document);
    }

    public void removeDocument(Long id){
        documents.removeIf(document -> document.getId().equals(id));
    }
}
