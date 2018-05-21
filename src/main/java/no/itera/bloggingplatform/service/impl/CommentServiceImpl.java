package no.itera.bloggingplatform.service.impl;

import no.itera.bloggingplatform.model.Comment;
import no.itera.bloggingplatform.repository.CommentRepository;
import no.itera.bloggingplatform.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> findCommentsForPost(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    @Override
    public Comment createCommentForPostId(Long postId, Comment comment) {
        if (comment.getKey() != null) {
            return comment;
        }
        comment.setPostId(postId);
        return commentRepository.create(comment);
    }

    @Override
    public Comment findCommentById(Long commentId) {
        return commentRepository.read(commentId);
    }

    @Override
    public Comment deleteCommentById(Long commentId) {
        return commentRepository.delete(commentId);
    }

}
