package no.itera.bloggingplatform.controller.mapper;

import no.itera.bloggingplatform.controller.dto.CommentDto;
import no.itera.bloggingplatform.controller.dto.ImmutableCommentDto;
import no.itera.bloggingplatform.model.Comment;

import javax.validation.Valid;

public final class CommentMapper {

    private CommentMapper(){

    }

    public static CommentDto mapToApi(Comment comment){
        return ImmutableCommentDto.builder()
                .id(comment.getKey())
                .date(comment.getDate())
                .author(comment.getAuthorName())
                .content(comment.getContent())
                .postId(comment.getPostId())
                .build();
    }

    public static  Comment mapToDomain(@Valid CommentDto commentDto){
        Comment comment = new Comment();
        comment.setKey(commentDto.getId());
        comment.setDate(commentDto.getDate());
        comment.setAuthorName(commentDto.getAuthor());
        comment.setContent(commentDto.getContent());
        comment.setPostId(commentDto.getPostId());
        return comment;
    }
}
