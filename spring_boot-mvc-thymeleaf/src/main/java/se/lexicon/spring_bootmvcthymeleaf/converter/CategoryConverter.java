package se.lexicon.spring_bootmvcthymeleaf.converter;

import org.springframework.stereotype.Component;
import se.lexicon.spring_bootmvcthymeleaf.model.dto.CategoryView;
import se.lexicon.spring_bootmvcthymeleaf.model.entity.Category;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class CategoryConverter implements Converter<Category, CategoryView>{

    @Override
    public CategoryView toView(Category entity) {
        return new CategoryView(entity.getId(), entity.getName(), entity.getCreateDate());
    }

    @Override
    public Category toEntity(CategoryView view) {
        return new Category(view.getId(), view.getName(), view.getCreateDate());
    }

    @Override
    public Collection<CategoryView> toViews(Collection<Category> entities) {

        List<CategoryView> categoryViewList = new ArrayList<>();
        for (Category c: entities) {
            categoryViewList.add(
                    toView(c)
            );
        }
        return categoryViewList;
    }

    @Override
    public Collection<Category> toEntities(Collection<CategoryView> views) {
        return null;
    }
}
