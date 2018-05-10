package no.itera.bloggingplatform.repository;

import no.itera.bloggingplatform.model.Comment;

import java.util.List;

public interface CommentRepository extends BasicRepository<Comment> {

    List<Comment> findByPostId(Long postId);

}
