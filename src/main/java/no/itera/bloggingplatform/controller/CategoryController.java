package no.itera.bloggingplatform.controller;

import no.itera.bloggingplatform.controller.dto.CategoryDto;
import no.itera.bloggingplatform.controller.mapper.CategoryMapper;
import no.itera.bloggingplatform.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static no.itera.bloggingplatform.controller.mapper.CategoryMapper.mapToApi;
import static no.itera.bloggingplatform.controller.mapper.CategoryMapper.mapToDomain;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;


@RestController
@RequestMapping(value = "/categories", produces = APPLICATION_JSON_VALUE)
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(method = GET)
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryDto> fetchAllCategories() {
        return categoryService.listAllCategories().stream()
                .map(CategoryMapper::mapToApi)
                .collect(Collectors.toList());
    }

//    @RequestMapping(method = POST)
//    @ResponseStatus(HttpStatus.CREATED)
//    public CategoryDto createCategory(@RequestBody CategoryDto categoryDto) {
//        return mapToApi(categoryService.createCategory(mapToDomain(categoryDto)));
//    }
//
//    @RequestMapping(method = PUT, value = "/{categoryId}")
//    @ResponseStatus(HttpStatus.OK)
//    public CategoryDto updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable Long categoryId) {
//        return mapToApi(categoryService.updateCategory(categoryId, mapToDomain(categoryDto)));
//    }
}
