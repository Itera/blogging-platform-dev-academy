package no.itera.bloggingplatform.controller.dto;

import java.util.Date;

import org.immutables.value.Value;
import org.springframework.lang.Nullable;

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
