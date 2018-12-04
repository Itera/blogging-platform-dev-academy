package no.itera.bloggingplatform.controller.dto;

import org.hibernate.validator.constraints.Length;
import org.immutables.value.Value;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Value.Immutable
@JsonSerialize(as = ImmutableAuthorDto.class)
@JsonDeserialize(as = ImmutableAuthorDto.class)
public interface AuthorDto {

  @Nullable
  @Value.Parameter
  Long getId();

  @NotBlank
  @Value.Parameter
  String getUserName();

  @Email()
  @Value.Parameter
  String getEmail();

  @NotBlank
  @Value.Parameter
  String getFirstName();

  @NotBlank
  @Value.Parameter
  String getLastName();

  @NotBlank
  @Value.Parameter
  String getPhone();
}
