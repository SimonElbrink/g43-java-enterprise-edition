package se.lexicon.spring_bootmvcthymeleaf.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import se.lexicon.spring_bootmvcthymeleaf.model.dto.CategoryView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {



    List<CategoryView> categoryViewList = new ArrayList<>();

    public CategoryController() {
        categoryViewList.add(new CategoryView(1,"Clothes", LocalDate.now()));
        categoryViewList.add(new CategoryView(2,"Scarf", LocalDate.now()));
        categoryViewList.add(new CategoryView(3,"Electronics", LocalDate.now()));
        categoryViewList.add(new CategoryView(4,"Fuel", LocalDate.now()));
        categoryViewList.add(new CategoryView(5,"Food", LocalDate.now()));
    }

    //http://localhost:8080/category/list
    @GetMapping("/list")
    public String category(Model model) {

        model.addAttribute("categoryViews", categoryViewList);
        model.addAttribute("categoryListSize", categoryViewList.size());
        return "category/categories-view";
    }


    //http://localhost:8080/category/view/1
    //http://localhost:8080/category/view/5
    @GetMapping("/view/{id}")
    public String findById(@PathVariable("id") int id, Model model){

        System.out.println("ID: "+ id);

        CategoryView categoryView = categoryViewList.stream().filter(category -> category.getId() == id ).findFirst().orElse(null);

        model.addAttribute("categoryView", categoryView);

        return "category/category-view";
    }

}
