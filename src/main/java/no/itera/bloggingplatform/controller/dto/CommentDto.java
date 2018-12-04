package no.itera.bloggingplatform.controller.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import no.itera.bloggingplatform.model.Author;
import org.immutables.value.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Value.Immutable
@JsonSerialize(as = ImmutableCommentDto.class)
@JsonDeserialize(as = ImmutableCommentDto.class)
public interface CommentDto {

    @Nullable
    @Value.Parameter
    Long getId();

    @NotNull
    @Value.Parameter
    Date getDate();

    @NotBlank
    @Value.Parameter
    String getAuthor();

    @NotBlank
    @Value.Parameter
    String getContent();

    @NotNull
    @Value.Parameter
    Long getPostId();
}
