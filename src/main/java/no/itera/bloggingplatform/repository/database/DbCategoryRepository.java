package no.itera.bloggingplatform.repository.database;

import no.itera.bloggingplatform.model.Category;
import no.itera.bloggingplatform.repository.CategoryRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static no.itera.bloggingplatform.constant.ProfileDefinition.DATABASE_STORAGE;

@Repository
@Profile(DATABASE_STORAGE)
public class DbCategoryRepository extends AbstractDbRepository implements CategoryRepository {

    private String tableName = "category";

    public DbCategoryRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public Category create(Category toPersist) {
        Long nextId = getNextId(tableName);

        String sql = "INSERT INTO " +tableName+ " (ID, NAME) VALUES (?, ?)";

        int row = getJdbcTemplate().update(sql,
                nextId,
                toPersist.getName()
        );

        if (row > 0) {
            return read(nextId);
        } else {
            return new Category();
        }

    }

    @Override
    public Category read(Long id) {
        String sql = "SELECT * FROM "+tableName+" WHERE id = ? limit 1";
        return getJdbcTemplate().queryForObject(sql, new Object[] {id}, ((resultSet, i) -> categoryMapper(resultSet)));
    }

    @Override
    public List<Category> readAll() {
        String sql = "SELECt * FROM "+tableName;
        return getJdbcTemplate().query(sql, ((resultSet, i) -> categoryMapper(resultSet)));
    }

    @Override
    public Category update(Category toUpdate) {
        String sql = "UPDATE "+tableName+" SET NAME=? WHERE id = ? LIMIT 1";
        getJdbcTemplate().update(sql,
                toUpdate.getName(),
                toUpdate.getKey()
        );

        return read(toUpdate.getKey());
    }

    @Override
    public Category delete(Long id) {
        Category deleteCateg = read(id);
        delete(id, tableName);
        return deleteCateg;
    }

    @Override
    public boolean exists(Long id) {
        return exists(id, tableName);
    }

    private Category categoryMapper(ResultSet resultSet) throws SQLException {
        Category category = new Category();

        category.setKey(resultSet.getLong("ID"));
        category.setName((resultSet.getString("NAME")));

        return category;
    }

}
