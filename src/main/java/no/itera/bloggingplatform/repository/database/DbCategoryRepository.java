package no.itera.bloggingplatform.repository.database;

import no.itera.bloggingplatform.model.Category;
import no.itera.bloggingplatform.repository.CategoryRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static no.itera.bloggingplatform.constant.ProfileDefinition.DATABASE_STORAGE;

@Repository
@Profile(DATABASE_STORAGE)
public class DbCategoryRepository extends AbstractDbRepository implements CategoryRepository {

    public DbCategoryRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public Category create(Category toPersist) {
        return null;
    }

    @Override
    public Category read(Long id) {
        return null;
    }

    @Override
    public List<Category> readAll() {
        return null;
    }

    @Override
    public Category update(Category toUpdate) {
        return null;
    }

    @Override
    public Category delete(Long id) {
        return null;
    }

    @Override
    public boolean exists(Long id) {
        return exists(id, "category");
    }

}
