package no.itera.bloggingplatform.controller;

import static java.util.Collections.singletonList;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;

import no.itera.bloggingplatform.controller.dto.AuthorDto;
import no.itera.bloggingplatform.controller.dto.ImmutableAuthorDto;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/author", produces = APPLICATION_JSON_VALUE)
public class AuthorController {

  @RequestMapping(method = GET)
  @ResponseStatus(HttpStatus.OK)
  public List<AuthorDto> fetchAllAuthors() {
    return singletonList(ImmutableAuthorDto.builder()
        .id(1L)
        .userName("findAll")
        .firstName("John")
        .lastName("Doe")
        .email("")
        .phone("")
        .build()
    );
  }

  @RequestMapping(method = GET, value = "/{authorId}")
  @ResponseStatus(HttpStatus.OK)
  public AuthorDto fetchAuthorById(@PathVariable Long authorId) {
    return ImmutableAuthorDto.builder()
        .id(authorId)
        .userName("fetchedByName")
        .firstName("John")
        .lastName("Doe")
        .email("")
        .phone("")
        .build();
  }

  @RequestMapping(method = POST)
  @ResponseStatus(HttpStatus.CREATED)
  public AuthorDto createAuthor(@RequestBody AuthorDto author) {
    return ImmutableAuthorDto.copyOf(author).withId(4L).withUserName("Just Created");
  }

  @RequestMapping(method = PUT)
  @ResponseStatus(HttpStatus.OK)
  public AuthorDto updateAuthor(@RequestBody AuthorDto author) {
    return author;
  }

  @RequestMapping(method = DELETE, value = "/{authorId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteAuthorByUsername(@PathVariable Long authorId) {
  }
}
