package se.lexicon.spring_bootmvcthymeleaf.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category")
public class CategoryController {

    //http://localhost:8080/category/list
    @GetMapping("/list")
    public String category(Model model) {

        model.addAttribute("categoryListSize", 30);
        return "category/categories-view";
    }

}
