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
        return categoryRepository.create(category);
    }

    @Override
    public Category updateCategory(Long key, Category category) {
        return categoryRepository.update(key, category);

    }
}
