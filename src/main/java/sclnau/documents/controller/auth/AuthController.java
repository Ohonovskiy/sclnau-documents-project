package sclnau.documents.controller.auth;

import sclnau.documents.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping("/registration")
//    public String registration(Model model){
//        model.addAttribute("user", new User());
//
//        return "user/registration";
//    }
//
//    @PostMapping("/register")
//    public String registerUser(@RequestParam("email") String email,
//                               @RequestParam("password") String password,
//                               Model model){
//
//        Optional<User> user1 = userService.getByEmail(email);
//
//        if(user1.isPresent()){
//            model.addAttribute("errorMessage", "Ця електронна адреса вже використовується");
//            return "user/registration";
//        }
//
//        User user = new User();
//        user.setEmail(email);
//        user.setPassword(password);
//
//        userService.save(user);
//
//        return "redirect:/auth/login?success=true";
//    }

    @GetMapping("/login")
    public String login(){
        return "user/login";
    }
}
