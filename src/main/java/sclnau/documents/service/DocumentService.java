package sclnau.documents.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sclnau.documents.entity.Document;
import sclnau.documents.repository.DocumentRepo;

@Service
public class DocumentService {
    private final DocumentRepo documentRepo;

    @Autowired
    public DocumentService(DocumentRepo documentRepo) {
        this.documentRepo = documentRepo;
    }

    public void save(Document document){
        documentRepo.save(document);
    }

    public Document getById(Long id){
        return documentRepo.getReferenceById(id);
    }

    public void remove(Document document){
        documentRepo.delete(document);
    }

    public void remove(Long id){
        documentRepo.deleteById(id);
    }
}
