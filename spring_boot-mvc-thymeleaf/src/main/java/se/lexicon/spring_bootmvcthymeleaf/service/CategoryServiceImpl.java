package se.lexicon.spring_bootmvcthymeleaf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.spring_bootmvcthymeleaf.converter.CategoryConverter;
import se.lexicon.spring_bootmvcthymeleaf.model.dto.CategoryForm;
import se.lexicon.spring_bootmvcthymeleaf.model.dto.CategoryView;
import se.lexicon.spring_bootmvcthymeleaf.model.entity.Category;
import se.lexicon.spring_bootmvcthymeleaf.repository.CategoryRepository;

import java.util.Collection;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryConverter categoryConverter) {
        this.categoryRepository = categoryRepository;
        this.categoryConverter = categoryConverter;
    }

    CategoryRepository categoryRepository;
    CategoryConverter categoryConverter;

    @Override
    public CategoryView findById(int id) {
        return categoryConverter.toView(categoryRepository.findById(id).orElseThrow(RuntimeException::new));
    }

    @Override
    public Collection<CategoryView> findAll() {

        //Find All Categories
        List<Category> all = categoryRepository.findAll();

        //Converting entity -> View
        Collection<CategoryView> categoryViewList = categoryConverter.toViews(all);

        return categoryViewList;
    }

    @Override
    public CategoryView create(CategoryForm categoryForm) {
        //Create Entity
        Category category = categoryConverter.toEntity(categoryForm);

        //Save Entity
        Category savedEntity = categoryRepository.save(category);

        //Convert to View
        CategoryView categoryView = categoryConverter.toView(savedEntity);

        //Return View
        return categoryView;
    }

    @Override
    public boolean deleteById(int id) {
        if (categoryRepository.findById(id).isPresent()) {
            categoryRepository.deleteById(id);
            return true;
        }

        return false;
    }
}
