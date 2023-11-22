package sclnau.documents.service;

import sclnau.documents.entity.Group;
import sclnau.documents.repository.GroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    private final GroupRepo groupRepo;

    @Autowired
    public GroupService(GroupRepo groupRepo) {
        this.groupRepo = groupRepo;
    }

    public void save(Group group){
        groupRepo.save(group);
    }

    public Group getById(Long id){
        return groupRepo.getReferenceById(id);
    }

    public List<Group> getAll(){
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        return groupRepo.findAll(sort);
    }
}
