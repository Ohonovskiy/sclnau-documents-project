package sclnau.documents.repository;

import sclnau.documents.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepo extends JpaRepository<Document, Long> {
    List<Document> findAllByGroup_IdIs(Long id);
}
