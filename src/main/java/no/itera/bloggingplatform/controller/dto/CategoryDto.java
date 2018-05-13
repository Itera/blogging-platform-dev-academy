package no.itera.bloggingplatform.controller.dto;

import org.immutables.value.Value;

@Value.Immutable
public interface CategoryDto {

  @Value.Parameter
  Long getId();

  @Value.Parameter
  String getName();
}
