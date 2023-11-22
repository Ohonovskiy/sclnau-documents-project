package sclnau.documents.repository;

import sclnau.documents.entity.Group;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepo extends JpaRepository<Group, Long> {
    @EntityGraph(attributePaths = {"subjects", "subjects.books"})
    List<Group> findAll();
}
