package no.itera.bloggingplatform.repository.memory;

import no.itera.bloggingplatform.model.Comment;
import no.itera.bloggingplatform.repository.CommentRepository;

import java.util.List;
import java.util.stream.Collectors;

public class InMemCommentRepository extends DefaultInMemoryRepository<Comment> implements CommentRepository {

    @Override
    public List<Comment> findByPostId(Long postId) {
        return readAll()
                .stream()
                .filter(comment -> postId.equals(comment.getPostId()))
                .collect(Collectors.toList());
    }
}
