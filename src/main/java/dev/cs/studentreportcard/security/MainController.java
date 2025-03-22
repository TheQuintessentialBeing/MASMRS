package dev.cs.studentreportcard.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping()
public class MainController {
    @Autowired
    //ProductService productService;
    @GetMapping("/")
    public String root() {
        //   productService.clearVirtualCart();
        System.out.println("Tobe Deleted For testing only:- INDEX CONTROLLER PAGE IS CALLED ...");
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        //   productService.clearVirtualCart();
        System.out.println("Tobe Deleted For testing only:- LOGOUT CONTROLLER PAGE IS CALLED ...");
        return "login";
    }

    @GetMapping("/aboutus")
    public String placeholder(Model model) {
        //   productService.clearVirtualCart();
        System.out.println("Tobe Deleted For testing only:-  PLACEHOLDER CONTROLLER PAGE IS CALLED ...");
        return "aboutus";
    }

    @GetMapping("/contactus")
    public String contactus(Model model) {
        //   productService.clearVirtualCart();
        System.out.println("Tobe Deleted For testing only:- CONTCT US CONTROLLER PAGE IS CALLED ...");
        return "contactus";
    }


    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }
}