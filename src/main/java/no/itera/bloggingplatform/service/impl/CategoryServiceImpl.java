package no.itera.bloggingplatform.service.impl;

import no.itera.bloggingplatform.model.Category;
import no.itera.bloggingplatform.repository.CategoryRepository;
import no.itera.bloggingplatform.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> listAllCategories() {
        return categoryRepository.readAll();
    }

    @Override
    public Category createCategory(Category category) {
        if (category.getKey() != null) {
            return category;
        }

        if (nameExists(category)) {
            return category;
        }

        return categoryRepository.create(category);
    }

    private boolean nameExists(Category category) {
        return categoryRepository.readAll().stream()
                .map(Category::getName)
                .anyMatch(categoryName -> categoryName.equals(category.getName()));
    }

    @Override
    public Category updateCategory(Long key, Category category) {
        category.setKey(key);
        return categoryRepository.update(category);
    }
}
