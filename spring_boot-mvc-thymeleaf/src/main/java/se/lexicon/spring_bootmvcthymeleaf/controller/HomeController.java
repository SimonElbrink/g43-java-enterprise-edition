package se.lexicon.spring_bootmvcthymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    //http://localhost:8080
    //http://localhost:8080/
    //http://localhost:8080/index
    @RequestMapping(path = {"","/","/index"},method = RequestMethod.GET)
    public String defaultPage(){
        return "index"; // index.html
    }

    // http://localhost:8080/homepage
    @GetMapping("/homepage")
    public String homePage(){
        return "index";
    }

    //http://localhost:8080/custom
    @GetMapping("/custom")
    public String customMessage(Model model){
        String message = "Message From Controller";

        model.addAttribute("message",message);

        return "index";
    }


}
