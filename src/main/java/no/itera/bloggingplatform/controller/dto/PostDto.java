package no.itera.bloggingplatform.controller.dto;

import java.util.Date;
import java.util.List;

import org.immutables.value.Value;
import org.springframework.lang.Nullable;

@Value.Immutable
public interface PostDto {

  @Nullable
  @Value.Parameter
  Long getId();

  @Value.Parameter
  String getTitle();

  @Value.Parameter
  Date getDate();

  @Value.Parameter
  String getPerex();

  @Value.Parameter
  String getContent();

  @Value.Parameter
  AuthorDto getAuthor();

  @Value.Parameter
  List<CategoryDto> getCategories();
}
