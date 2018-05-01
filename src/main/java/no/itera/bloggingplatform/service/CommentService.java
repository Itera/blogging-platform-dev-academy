package no.itera.bloggingplatform.service;

import no.itera.bloggingplatform.model.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> findCommentsForPost(Long postId);

    Comment createCommentForPostId(Long postId, Comment comment);

    Comment findCommentById(Long commentId);

    Comment deleteCommentById(Long commentId);
}
