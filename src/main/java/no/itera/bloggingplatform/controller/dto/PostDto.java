package no.itera.bloggingplatform.controller.dto;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.immutables.value.Value;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(as = ImmutablePostDto.class)
@JsonDeserialize(as = ImmutablePostDto.class)
@Value.Immutable
public interface PostDto {

  @Nullable
  @Value.Parameter
  Long getId();

  @Size(min = 5, max = 60)
  @Value.Parameter
  String getTitle();

  @Past
  @Value.Parameter
  Date getDate();

  @Size(min = 5, max = 250)
  @Value.Parameter
  String getPerex();

  @Size(min = 5, max = 50000)
  @Value.Parameter
  String getContent();

  @Valid
  @Value.Parameter
  AuthorDto getAuthor();

  @Valid
  @NotEmpty
  @Value.Parameter
  List<CategoryDto> getCategories();
}
