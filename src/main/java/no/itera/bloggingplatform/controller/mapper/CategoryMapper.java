package no.itera.bloggingplatform.controller.mapper;

import no.itera.bloggingplatform.controller.dto.AuthorDto;
import no.itera.bloggingplatform.controller.dto.CategoryDto;
import no.itera.bloggingplatform.controller.dto.ImmutableAuthorDto;
import no.itera.bloggingplatform.controller.dto.ImmutableCategoryDto;
import no.itera.bloggingplatform.model.Author;
import no.itera.bloggingplatform.model.Category;

import javax.validation.Valid;

public final class CategoryMapper {

    private CategoryMapper() {
    }

    public static CategoryDto mapToApi(Category category) {
        return ImmutableCategoryDto.builder()
                .id(category.getKey())
                .name(category.getName())
                .build();
    }

    public static Category mapToDomain(@Valid CategoryDto categoryDto) {
        Category category = new Category();
        category.setKey(categoryDto.getId());
        category.setName(categoryDto.getName());
        return category;
    }
}
