package se.lexicon.spring_bootmvcthymeleaf.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import se.lexicon.spring_bootmvcthymeleaf.model.dto.CategoryForm;
import se.lexicon.spring_bootmvcthymeleaf.model.dto.CategoryView;

import javax.validation.Valid;
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

    @PostMapping("/view")
    public String findByIdPost(@RequestParam("id") Integer id, Model model){
        System.out.println("ID: "+ id);
        CategoryView categoryView = categoryViewList.stream().filter(category -> category.getId() == id ).findFirst().orElse(null);
        model.addAttribute("categoryView", categoryView);

        return "category/category-view";
    }

    @GetMapping("/delete/{id}")
    public String deleteById( @PathVariable("id") Integer id){

        System.out.println("ID To Delete: "+ id);

        categoryViewList.removeIf(view -> view.getId() == id);

        return "redirect:/category/list"; // Redirect to method with mapping /category/list
        //return "categories-view"; // HTML FILE
    }
    @GetMapping("/form")
    public String categoryForm(Model model){
        CategoryForm categoryForm = new CategoryForm();
        model.addAttribute("category", categoryForm);

        return "category/category-form";
    }

    @PostMapping("/add")
    public String addCategory(@ModelAttribute("category") @Valid CategoryForm categoryForm, BindingResult bindingResult){

        System.out.println("categoryForm = " + categoryForm);

        if (categoryViewList.stream().anyMatch(name -> name.getName().equals(categoryForm.getName()))){
            FieldError fieldError = new FieldError("category", "name", "Not allowed to have duplicate Categories");
            bindingResult.addError(fieldError);
        }

        if (bindingResult.hasErrors()){
            return "category/category-form"; // Return to creation form with errors attached.
        }

        int randomInt = (int) (Math.random() * 100);
        LocalDate timeNow = LocalDate.now();

        CategoryView categoryToAdd = new CategoryView(randomInt, categoryForm.getName(), timeNow);

        categoryViewList.add(categoryToAdd);


//        throw new IllegalArgumentException("Custom Exception");


        return "redirect:/category/list";
    }



}
