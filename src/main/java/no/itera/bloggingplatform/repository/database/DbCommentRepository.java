package no.itera.bloggingplatform.repository.database;

import no.itera.bloggingplatform.model.Comment;
import no.itera.bloggingplatform.repository.CommentRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static no.itera.bloggingplatform.constant.ProfileDefinition.DATABASE_STORAGE;

@Repository
@Profile(DATABASE_STORAGE)
public class DbCommentRepository extends AbstractDbRepository implements CommentRepository {

    public DbCommentRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public List<Comment> findByPostId(Long postId) {
        return null;
    }

    @Override
    public Comment create(Comment toPersist) {
        return null;
    }

    @Override
    public Comment read(Long id) {
        return null;
    }

    @Override
    public List<Comment> readAll() {
        return null;
    }

    @Override
    public Comment update(Comment toUpdate) {
        return null;
    }

    @Override
    public Comment delete(Long id) {
        return null;
    }

    @Override
    public boolean exists(Long id) {
        return exists(id, "comment");
    }
}
