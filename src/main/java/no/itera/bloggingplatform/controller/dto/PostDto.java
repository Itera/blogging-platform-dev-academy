package no.itera.bloggingplatform.controller.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import no.itera.bloggingplatform.model.Author;
import no.itera.bloggingplatform.model.Category;
import org.immutables.value.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Value.Immutable
@JsonSerialize(as = ImmutablePostDto.class)
@JsonDeserialize(as = ImmutablePostDto.class)
public interface PostDto {

    @Nullable
    @Value.Parameter
    Long getId();

    @NotBlank
    @Value.Parameter
    String getTitle();

    @NotNull
    @Value.Parameter
    Date getDate();

    @NotBlank
    @Value.Parameter
    String getPerex();

    @NotBlank
    @Value.Parameter
    String getContent();

    @NotNull
    @Value.Parameter
    AuthorDto getAuthor();

    @NotEmpty
    @Value.Parameter
    List<CategoryDto> getCategories();
}
