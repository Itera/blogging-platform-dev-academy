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
        List<Comment> postComments = new ArrayList<>();
        for (Comment comment: this.readAll()
             ) {
            if (comment.getPostId().equals(postId)){
                postComments.add(comment);
            }
        }
        return postComments;
    }
}
