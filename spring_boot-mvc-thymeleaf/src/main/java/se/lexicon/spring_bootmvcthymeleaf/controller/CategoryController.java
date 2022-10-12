package se.lexicon.spring_bootmvcthymeleaf.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import se.lexicon.spring_bootmvcthymeleaf.model.dto.CategoryForm;
import se.lexicon.spring_bootmvcthymeleaf.model.dto.CategoryView;
import se.lexicon.spring_bootmvcthymeleaf.repository.CategoryRepository;
import se.lexicon.spring_bootmvcthymeleaf.service.CategoryService;

import javax.validation.Valid;

@Controller
@RequestMapping("/category")
public class CategoryController {


    @Autowired
    public CategoryController(CategoryService categoryService, CategoryRepository categoryRepository) {
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
    }

    private CategoryService categoryService;

    private CategoryRepository categoryRepository;


    public CategoryController() {
//        categoryViewList.add(new CategoryView(1,"Clothes", LocalDate.now()));
//        categoryViewList.add(new CategoryView(2,"Scarf", LocalDate.now()));
//        categoryViewList.add(new CategoryView(3,"Electronics", LocalDate.now()));
//        categoryViewList.add(new CategoryView(4,"Fuel", LocalDate.now()));
//        categoryViewList.add(new CategoryView(5,"Food", LocalDate.now()));
    }

    //http://localhost:8080/category/list
    @GetMapping("/list")
    public String category(Model model) {
        model.addAttribute("categoryViews", categoryService.findAll());
        return "category/categories-view";
    }


    //http://localhost:8080/category/view/1
    //http://localhost:8080/category/view/5
    @GetMapping("/view/{id}")
    public String findById(@PathVariable("id") int id, Model model) {

        System.out.println("ID: " + id);

        CategoryView categoryView = categoryService.findById(id);

        model.addAttribute("categoryView", categoryView);

        return "category/category-view";
    }

    @PostMapping("/view")
    public String findByIdPost(@RequestParam("id") Integer id, Model model) {
        System.out.println("ID: " + id);
        CategoryView categoryView = categoryService.findById(id);
        model.addAttribute("categoryView", categoryView);

        return "category/category-view";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {

        System.out.println("ID To Delete: " + id);

        boolean result = categoryService.deleteById(id);

        if (result){
            redirectAttributes.addFlashAttribute("message", "Category with id: " + id +" was successfully deleted!");
            redirectAttributes.addFlashAttribute("alertClass", "alert alert-info");
        }else {
            System.out.println("was not deleted!");
            //Display Error message
        }


        return "redirect:/category/list"; // Redirect to method with mapping /category/list
        //return "categories-view"; // HTML FILE
    }

    @GetMapping("/form")
    public String categoryForm(Model model) {
        CategoryForm categoryForm = new CategoryForm();
        model.addAttribute("category", categoryForm);

        return "category/category-form";
    }

    @PostMapping("/add")
    public String addCategory(@ModelAttribute("category") @Valid CategoryForm categoryForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        System.out.println("categoryForm = " + categoryForm);

        if (categoryRepository.findByName(categoryForm.getName()).isPresent()) {
            FieldError fieldError = new FieldError("category", "name", "Not allowed to have duplicate Categories");
            bindingResult.addError(fieldError);
        }

        if (bindingResult.hasErrors()) {
            return "category/category-form"; // Return to creation form with errors attached.
        }



        categoryService.create(categoryForm);

        redirectAttributes.addFlashAttribute("message", "Category " + categoryForm.getName() + " was successfully added!");
        redirectAttributes.addFlashAttribute("alertClass", "alert alert-success");


//        throw new IllegalArgumentException("Custom Exception");
        return "redirect:/category/list";
    }


}
