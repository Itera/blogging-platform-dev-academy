package no.itera.bloggingplatform.controller.mapper;

import no.itera.bloggingplatform.controller.dto.CommentDto;
import no.itera.bloggingplatform.controller.dto.ImmutableCommentDto;
import no.itera.bloggingplatform.model.Comment;

public final class CommentMapper {

  private CommentMapper() {
  }

  public static CommentDto mapToApi(Comment comment) {
    return ImmutableCommentDto.builder()
        .id(comment.getKey())
        .content(comment.getContent())
        .postId(comment.getPostId())
        .author(comment.getAuthorName())
        .date(comment.getDate())
        .build();
  }

  public static Comment mapToDomain(CommentDto commentDto) {
    Comment comment = new Comment();
    comment.setKey(commentDto.getId());
    comment.setContent(commentDto.getContent());
    comment.setPostId(commentDto.getPostId());
    comment.setAuthorName(commentDto.getAuthor());
    comment.setDate(commentDto.getDate());
    return comment;
  }
}
