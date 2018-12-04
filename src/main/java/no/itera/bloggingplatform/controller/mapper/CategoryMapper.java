package no.itera.bloggingplatform.controller.mapper;

import java.util.List;
import java.util.stream.Collectors;

import no.itera.bloggingplatform.controller.dto.CategoryDto;
import no.itera.bloggingplatform.controller.dto.ImmutableCategoryDto;
import no.itera.bloggingplatform.model.Category;

public final class CategoryMapper {

  private CategoryMapper() {
  }

  public static CategoryDto mapToApi(Category category) {
    return ImmutableCategoryDto.of(category.getKey(), category.getName());
  }

  public static List<CategoryDto> mapListToApi(List<Category> categories) {
    return categories.stream()
        .map(CategoryMapper::mapToApi)
        .collect(Collectors.toList());
  }

  public static Category mapToDomain(CategoryDto categoryDto) {
    Category category = new Category();
    category.setKey(categoryDto.getId());
    category.setName(categoryDto.getName());
    return category;
  }

  public static List<Category> mapListToDomain(List<CategoryDto> categories) {
    return categories.stream()
        .map(CategoryMapper::mapToDomain)
        .collect(Collectors.toList());

  }
}
