package se.lexicon.spring_bootmvcthymeleaf.converter;

import org.springframework.stereotype.Component;
import se.lexicon.spring_bootmvcthymeleaf.model.dto.CategoryForm;
import se.lexicon.spring_bootmvcthymeleaf.model.dto.CategoryView;
import se.lexicon.spring_bootmvcthymeleaf.model.entity.Category;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class CategoryConverter implements Converter<Category, CategoryView, CategoryForm> {

    @Override
    public CategoryView toView(Category entity) {
        return new CategoryView(entity.getId(), entity.getName(), entity.getCreateDate());
    }

    @Override
    public Category toEntity(CategoryForm form) {
        return new Category(form.getName());
    }

    @Override
    public Collection<CategoryView> toViews(Collection<Category> entities) {

//        List<CategoryView> categoryViewList = new ArrayList<>();
//        for (Category c: entities) {
//            categoryViewList.add(
//                    toView(c)
//            );
//        }
//        return categoryViewList;

        return entities.stream().map(this::toView).collect(Collectors.toList());
    }

    @Override
    public Collection<Category> toEntities(Collection<CategoryForm> forms) {
        return forms.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
