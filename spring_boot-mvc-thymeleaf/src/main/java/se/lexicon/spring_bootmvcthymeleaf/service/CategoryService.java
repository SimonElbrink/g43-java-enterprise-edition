package se.lexicon.spring_bootmvcthymeleaf.service;

import se.lexicon.spring_bootmvcthymeleaf.model.dto.CategoryForm;
import se.lexicon.spring_bootmvcthymeleaf.model.dto.CategoryView;

import java.util.Collection;

public interface CategoryService {

   CategoryView findById(int id);
   Collection<CategoryView> findAll();

   CategoryView create(CategoryForm categoryForm);

   boolean deleteById(int id);


}
