package sclnau.documents.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Entity
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String name;

    private String image;

    @OneToMany(mappedBy = "group")
    @BatchSize(size = 30)
    private List<Document> documents = new ArrayList<>();

    public void addDocument(Document document){
        this.documents.add(document);
    }

    public void removeDocumentById(Long id){
        this.documents.removeIf(doc -> Objects.equals(doc.getId(), id));
    }

    public void removeDocument(Document document){
        this.documents.remove(document);
    }
}
