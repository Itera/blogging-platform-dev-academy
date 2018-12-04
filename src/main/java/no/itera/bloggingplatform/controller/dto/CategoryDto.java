package no.itera.bloggingplatform.controller.dto;

import javax.validation.constraints.Size;

import org.immutables.value.Value;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(as = ImmutableCategoryDto.class)
@JsonDeserialize(as = ImmutableCategoryDto.class)
@Value.Immutable
public interface CategoryDto {

  @Nullable
  @Value.Parameter
  Long getId();

  @Size(min = 3, max = 20)
  @Value.Parameter
  String getName();
}
