package sclnau.documents.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import sclnau.documents.enums.Role;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
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
