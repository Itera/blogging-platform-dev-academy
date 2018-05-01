package no.itera.bloggingplatform.repository.memory;

import no.itera.bloggingplatform.model.Comment;
import no.itera.bloggingplatform.repository.CommentRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static no.itera.bloggingplatform.constant.ProfileDefinition.IN_MEMORY_STORAGE;

@Repository
@Profile(IN_MEMORY_STORAGE)
public class InMemCommentRepository extends DefaultInMemoryRepository<Comment> implements CommentRepository {

    @Override
    public List<Comment> findByPostId(Long postId) {
        return readAll()
                .stream()
                .filter(comment -> postId.equals(comment.getPostId()))
                .collect(Collectors.toList());
    }
}
