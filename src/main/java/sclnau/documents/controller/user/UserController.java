package sclnau.documents.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sclnau.documents.entity.Document;
import sclnau.documents.entity.Group;
import sclnau.documents.entity.User;
import sclnau.documents.service.*;

@Controller
@RequestMapping("/user")
public class UserController {
    private final GroupService groupService;
    private final UserService userService;
    private final SubjectService subjectService;
    private final DocumentsFileManager documentsFileManager;
    private final DocumentService documentService;
    private final FileNameGenerator fileNameCreator;

    private User currentUser;

    @Autowired
    public UserController(GroupService groupService, UserService userService, SubjectService subjectService, DocumentsFileManager documentsFileManager, DocumentService documentService, FileNameGenerator fileNameCreator) {
        this.groupService = groupService;
        this.userService = userService;
        this.subjectService = subjectService;
        this.documentsFileManager = documentsFileManager;
        this.documentService = documentService;
        this.fileNameCreator = fileNameCreator;
    }

    @GetMapping("/profile")
    public String userProfile(Model model){
        if (userService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).isPresent()) {
            User user = userService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
            model.addAttribute("user", user);
            model.addAttribute("groups", groupService.getAll());
        }
        return "user/profile";
    }

    @PostMapping("/deleteBook")
    public String deleteBook(@RequestParam("documentId") Long docId){
        Document document = documentService.getById(docId);

        if (userService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).isPresent()) {
            User user = userService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
            user.removeDocument(docId);
        }

        documentsFileManager.deleteDocument(document);

        documentService.remove(docId);

        return "redirect:/user/profile";
    }

    @GetMapping("/upload")
    public String uploadPdf(Model model){
        model.addAttribute("groups", groupService.getAll());

        return "user/upload";
    }


    @PostMapping("/uploadDoc")
    public String handleFormSubmission(@RequestParam("groupId") String groupId,
                                       @RequestParam("name") String name,
                                       @RequestParam("file") MultipartFile file) throws Exception {

        if(file != null) {
            Group group = groupService.getById(Long.valueOf(groupId));
            Document document = new Document();

            setCurrentUser();

            documentService.save(document);

            String fileName =
                    fileNameCreator.generateHashedFileName(file, document.getId());

            String filePath =
                    documentsFileManager.saveFileAndGetFilePath(file, fileName);

            document.setGroup(group);
            document.setName(name);
            document.setPath(filePath);

            currentUser.addDocument(document);
            userService.save(currentUser);

            groupService.save(group);
            documentService.save(document);
        }


        return "redirect:/user/upload/"+groupId+"?success";
    }

    private void setCurrentUser(){
        currentUser = userService
                .getByEmail(SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName())
                .orElseThrow(() -> new RuntimeException("User with that email doesn't exist"));
    }
}
