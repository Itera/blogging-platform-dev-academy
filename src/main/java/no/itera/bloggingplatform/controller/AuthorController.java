package no.itera.bloggingplatform.controller;

import static no.itera.bloggingplatform.controller.mapper.AuthorMapper.mapToApi;
import static no.itera.bloggingplatform.controller.mapper.AuthorMapper.mapToDomain;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;
import java.util.stream.Collectors;

import no.itera.bloggingplatform.controller.dto.AuthorDto;
import no.itera.bloggingplatform.controller.mapper.AuthorMapper;
import no.itera.bloggingplatform.service.AuthorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/authors", produces = APPLICATION_JSON_VALUE)
public class AuthorController {

  private AuthorService authorService;

  @Autowired
  public AuthorController(AuthorService authorService) {
    this.authorService = authorService;
  }

  @RequestMapping(method = GET)
  @ResponseStatus(HttpStatus.OK)
  public List<AuthorDto> fetchAllAuthors() {
    return authorService.listAllAuthors().stream()
        .map(AuthorMapper::mapToApi)
        .collect(Collectors.toList());
  }

  @RequestMapping(method = GET, value = "/{authorId}")
  @ResponseStatus(HttpStatus.OK)
  public AuthorDto fetchAuthorById(@PathVariable Long authorId) {
    return mapToApi(authorService.findAuthorById(authorId));
  }

  @RequestMapping(method = POST)
  @ResponseStatus(HttpStatus.CREATED)
  public AuthorDto createAuthor(@RequestBody AuthorDto author) {
    return mapToApi(authorService.createAuthor(mapToDomain(author)));
  }

  @RequestMapping(method = PUT)
  @ResponseStatus(HttpStatus.OK)
  public AuthorDto updateAuthor(@RequestBody AuthorDto author) {
    return mapToApi(authorService.updateAuthor(author.getId(), mapToDomain(author)));
  }

  @RequestMapping(method = DELETE, value = "/{authorId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteAuthorByUsername(@PathVariable Long authorId) {
    authorService.deleteAuthor(authorId);
  }
}
