package pl.san.mw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.san.mw.model.AppUser;
import pl.san.mw.model.Item;
import pl.san.mw.repositories.UserRepository;
import pl.san.mw.service.UserService;

import java.sql.SQLException;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

//autor Michał Wojda indeks:23512
@Controller
public class UserController {

    private UserService userService;
    private UserRepository userRepository;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/register")
    public String loadRegisterPage(Model model) {
        model.addAttribute("user", new AppUser());
        return "register";


    }
    @GetMapping("/loginFailed")
    public String loginFailed(Model model){
        model.addAttribute("loginFailed",true);
        return "login";

    }



    @PostMapping("/register")
    public String registerUser(@ModelAttribute AppUser appUser){

        try{
            userService.createSimpleUser(appUser);
        }
        catch(Exception e){
            return "registerError";
        }
        return "registerSuccess";



    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/profile")
    public String profilePage(Model model){
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        AppUser user = userRepository.findByUsername(username);
        double totalPrice= user.getRentedItems()
                .stream()
                .collect(Collectors.summingDouble(Item::getPrice));
        model.addAttribute("username",username);
        model.addAttribute("itemList",user.getRentedItems());
        model.addAttribute("totalPrice",totalPrice);
        return "profile";
    }

}
