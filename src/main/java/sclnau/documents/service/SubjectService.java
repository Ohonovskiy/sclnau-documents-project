package sclnau.documents.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sclnau.documents.entity.Document;
import sclnau.documents.repository.DocumentRepo;

import java.util.List;

@Service
public class SubjectService {
    private final DocumentRepo documentRepo;

    @Autowired
    public SubjectService(DocumentRepo documentRepo) {
        this.documentRepo = documentRepo;
    }

    public void save(Document document){
        documentRepo.save(document);
    }

    public Document getById(Long id){
        return documentRepo.getReferenceById(id);
    }

    public List<Document> getAll(){
        return documentRepo.findAll();
    }

    public List<Document> getAllWhereGroupId(Long id){
        return documentRepo.findAllByGroup_IdIsOrderByCreationDateAsc(id);
    }
}
