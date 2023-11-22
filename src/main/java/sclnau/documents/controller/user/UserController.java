package sclnau.documents.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @Autowired
    public UserController(GroupService groupService, UserService userService, SubjectService subjectService, DocumentsFileManager documentsFileManager, DocumentService documentService, FileNameGenerator fileNameCreator) {
        this.groupService = groupService;
        this.userService = userService;
        this.subjectService = subjectService;
        this.documentsFileManager = documentsFileManager;
        this.documentService = documentService;
        this.fileNameCreator = fileNameCreator;
    }

//    @GetMapping("/profile")
//    public String userProfile(Model model){
//        if (userService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).isPresent()) {
//            User user = userService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
//            model.addAttribute("user", user);
//            model.addAttribute("groups", groupService.getAll());
//        }
//        return "user/profile";
//    }
//
//    @PostMapping("/deleteBook")
//    public String deleteBook(@RequestParam("bookId") Long bookId){
//        Book book = documentService.getById(bookId);
//
//        if (userService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).isPresent()) {
//            User user = userService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
//            user.removeBook(bookId);
//        }
//
//        documentsFileManager.deleteBook(book);
//
//        documentService.remove(bookId);
//
//        return "redirect:/user/profile";
//    }
//
//    @GetMapping("/upload/{id}")
//    public String uploadPdf(Model model, @PathVariable Long id){
//        model.addAttribute("group", groupService.getById(id));
//        model.addAttribute("subjects", groupService.getById(id).getSubjects());
//        model.addAttribute("groups", groupService.getAll());
//
//
//        return "user/upload_pdf";
//    }
//
//    @GetMapping("/upload")
//    public String uploadIndex(){
//        return "redirect:/groups";
//    }
//
//    @PostMapping("/uploadPost")
//    public String handleFormSubmission(@RequestParam("groupId") String groupId,
//                                       @RequestParam("subjectName") String subjectName,
//                                       @RequestParam("name") String name,
//                                       @RequestParam("author") String author,
//                                       @RequestParam("file") MultipartFile file) throws Exception {
//
//        if(file != null) {
//            Group group = groupService.getById(Long.valueOf(groupId));
//
//            Subject subject = subjectService.getByNameAndGroupId(subjectName, group.getId());
//
//            Book book = new Book();
//            documentService.save(book);
//
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//            subject.setGroup(group);
//
//            String fileName =
//                    fileNameCreator.createNameForFile(file, book.getId());
//
//            String filePath =
//                    documentsFileManager.saveFileAndGetFilePath(file, fileName);
//
//            book.setName(name);
//            book.setAuthor(author);
//            book.setPdfPath(filePath);
//            book.setSubject(subject);
//
//            if(userService.getByEmail(authentication.getName()).isPresent()) {
//                User user = userService.getByEmail(authentication.getName()).get();
//                user.addBook(book);
//                userService.save(user);
//            }
//
//            documentService.save(book);
//            groupService.save(group);
//            documentService.save(book);
//        }
//
//
//        return "redirect:/user/upload/"+groupId+"?success";
//    }
}
