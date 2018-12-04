package no.itera.bloggingplatform.controller.mapper;

import no.itera.bloggingplatform.controller.dto.AuthorDto;
import no.itera.bloggingplatform.controller.dto.ImmutableAuthorDto;
import no.itera.bloggingplatform.model.Author;

import javax.validation.Valid;

public final class AuthorMapper {

  private AuthorMapper() {
  }

  public static AuthorDto mapToApi(Author author) {
    return ImmutableAuthorDto.builder()
        .id(author.getKey())
        .userName(author.getUserName())
        .firstName(author.getFirstName())
        .lastName(author.getLastName())
        .email(author.getEmail())
        .phone(author.getPhone())
        .build();
  }

  public static Author mapToDomain(@Valid AuthorDto authorDto) {
    Author author = new Author();
    author.setKey(authorDto.getId());
    author.setUserName(authorDto.getUserName());
    author.setFirstName(authorDto.getFirstName());
    author.setLastName(authorDto.getLastName());
    author.setEmail(authorDto.getEmail());
    author.setPhone(authorDto.getPhone());
    return author;
  }
}
