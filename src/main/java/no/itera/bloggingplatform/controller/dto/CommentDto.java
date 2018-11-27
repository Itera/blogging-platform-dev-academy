package no.itera.bloggingplatform.controller.dto;

import java.util.Date;

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

  @Value.Parameter
  Date getDate();

  @Value.Parameter
  String getAuthor();

  @Value.Parameter
  String getContent();

  @Value.Parameter
  Long getPostId();
}
