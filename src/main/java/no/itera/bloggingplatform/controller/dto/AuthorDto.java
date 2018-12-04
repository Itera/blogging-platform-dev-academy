package no.itera.bloggingplatform.controller.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

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

  @NotBlank
  @Value.Parameter
  String getUserName();

  @Email
  @Value.Parameter
  String getEmail();

  @NotBlank
  @Value.Parameter
  String getFirstName();

  @NotBlank
  @Value.Parameter
  String getLastName();

  @Nullable
  @Value.Parameter
  String getPhone();
}
