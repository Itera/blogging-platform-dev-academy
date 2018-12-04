package no.itera.bloggingplatform.controller.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;

@Value.Immutable
@JsonSerialize(as = ImmutableCategoryDto.class)
@JsonDeserialize(as = ImmutableCategoryDto.class)
public interface CategoryDto {

    @Nullable
    @Value.Parameter
    Long getId();

    @NotBlank
    @Value.Parameter
    String getName();
}
