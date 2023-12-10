package sclnau.documents.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sclnau.documents.entity.Document;

import java.util.List;

@Repository
public interface DocumentRepo extends JpaRepository<Document, Long> {
    List<Document> findAllByGroup_IdIsOrderByCreationDateAsc(Long id);
}
