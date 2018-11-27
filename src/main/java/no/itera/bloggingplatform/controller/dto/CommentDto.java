package no.itera.bloggingplatform.controller.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.immutables.value.Value;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(as = ImmutableCommentDto.class)
@JsonDeserialize(as = ImmutableCommentDto.class)
@Value.Immutable
public interface CommentDto {

  @Nullable
  @Value.Parameter
  Long getId();

  @Past
  @Value.Parameter
  Date getDate();

  @NotBlank
  @Value.Parameter
  String getAuthor();

  @Size(min = 5, max = 200)
  @Value.Parameter
  String getContent();

  @Positive
  @Value.Parameter
  Long getPostId();
}
