package no.itera.bloggingplatform.controller.dto;

import org.immutables.value.Value;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(as = ImmutableAuthorDto.class)
@JsonDeserialize(as = ImmutableAuthorDto.class)
@Value.Immutable
public interface AuthorDto {

  @Nullable
  @Value.Parameter
  Long getId();

  @Value.Parameter
  String getUserName();

  @Value.Parameter
  String getEmail();

  @Value.Parameter
  String getFirstName();

  @Value.Parameter
  String getLastName();

  @Nullable
  @Value.Parameter
  String getPhone();
}
