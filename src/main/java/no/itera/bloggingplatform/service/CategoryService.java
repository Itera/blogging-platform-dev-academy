package no.itera.bloggingplatform.service;

import no.itera.bloggingplatform.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> listAllCategories();

    Category createCategory(Category category);

    Category updateCategory(Long key, Category category);

}
