package no.itera.bloggingplatform.service.impl;

import no.itera.bloggingplatform.model.Category;
import no.itera.bloggingplatform.repository.memory.InMemCategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceImplTest {

    @Spy
    private InMemCategoryRepository inMemCategoryRepository;

    private CategoryServiceImpl categoryService;

    private Category category;

    @Before
    public void before() {
        category = new Category();
        category.setName("Books");

        categoryService = new CategoryServiceImpl(inMemCategoryRepository);
    }

    @Test
    public void shouldCreateCategoryOnlyOnce() {
        categoryService.createCategory(category);
        categoryService.createCategory(category);

        verify(inMemCategoryRepository, times(1)).create(category);
    }

}