package no.itera.bloggingplatform.repository.memory;

import no.itera.bloggingplatform.model.Comment;
import no.itera.bloggingplatform.repository.CommentRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CommentRepositoryImpl extends BasicRepositoryImpl<Comment> implements CommentRepository {

    @Override
    public List<Comment> findByPostId(Long postId) {

        List<Comment> foundComments = new ArrayList<>();

        for (Comment comment : inMemoryStorage.values()) {
            if (comment.getPostId().equals(postId))
                foundComments.add(comment);
        }

        return foundComments;
    }
}
