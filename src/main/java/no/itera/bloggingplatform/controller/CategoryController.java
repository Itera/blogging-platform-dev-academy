package no.itera.bloggingplatform.controller;

import static java.util.Arrays.asList;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import no.itera.bloggingplatform.controller.dto.CategoryDto;
import no.itera.bloggingplatform.controller.dto.ImmutableCategoryDto;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categories", produces = APPLICATION_JSON_VALUE)
public class CategoryController {

  @RequestMapping(method = GET)
  @ResponseStatus(HttpStatus.OK)
  public List<CategoryDto> fetchAllCategories() {
    return asList(
        ImmutableCategoryDto.of(1L, "sport"),
        ImmutableCategoryDto.of(2L, "movies")
    );
  }
}
