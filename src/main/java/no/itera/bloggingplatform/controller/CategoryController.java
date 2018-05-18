package no.itera.bloggingplatform.controller;

import static no.itera.bloggingplatform.controller.mapper.CategoryMapper.mapListToApi;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import no.itera.bloggingplatform.controller.dto.CategoryDto;
import no.itera.bloggingplatform.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/category", produces = APPLICATION_JSON_VALUE)
public class CategoryController {

  private CategoryService categoryService;

  @Autowired
  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @RequestMapping(method = GET)
  @ResponseStatus(HttpStatus.OK)
  public List<CategoryDto> fetchAllCategories() {
    return mapListToApi(categoryService.listAllCategories());
  }
}
