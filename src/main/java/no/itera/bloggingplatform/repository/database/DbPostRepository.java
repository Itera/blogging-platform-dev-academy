package no.itera.bloggingplatform.repository.database;

import no.itera.bloggingplatform.model.Post;
import no.itera.bloggingplatform.repository.PostRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static no.itera.bloggingplatform.constant.ProfileDefinition.DATABASE_STORAGE;

@Repository
@Profile(DATABASE_STORAGE)
public class DbPostRepository extends AbstractDbRepository implements PostRepository {

    public DbPostRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public List<Post> findByAuthor(Long authorId) {
        return null;
    }

    @Override
    public List<Post> findByCategory(Long categoryId) {
        return null;
    }

    @Override
    public Post create(Post toPersist) {
        return null;
    }

    @Override
    public Post read(Long id) {
        return null;
    }

    @Override
    public List<Post> readAll() {
        return null;
    }

    @Override
    public Post update(Post toUpdate) {
        return null;
    }

    @Override
    public Post delete(Long id) {
        return null;
    }

    @Override
    public boolean exists(Long id) {
        return exists(id, "post");
    }

}
