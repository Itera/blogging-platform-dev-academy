package no.itera.bloggingplatform.repository.database;

import static no.itera.bloggingplatform.constant.ProfileDefinition.DATABASE_STORAGE;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import no.itera.bloggingplatform.model.Category;
import no.itera.bloggingplatform.repository.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Profile(DATABASE_STORAGE)
public class DbCategoryRepository extends AbstractDbRepository implements CategoryRepository {

  @Autowired
  public DbCategoryRepository(JdbcTemplate jdbcTemplate) {
    super(jdbcTemplate);
  }

  @Override
  public Category create(Category toPersist) {
    Long generatedId = getNextId();
    getJdbcTemplate().update(
        "INSERT INTO CATEGORY (ID, NAME) VALUES (?, ?)",
        generatedId,
        toPersist.getName()
    );

    return read(generatedId);
  }

  @Override
  public Category read(Long id) {
    return getJdbcTemplate().queryForObject(
        "SELECT * FROM CATEGORY WHERE ID = ?",
        new Object[]{id},
        (resultSet, i) -> mapToCategory(resultSet)
    );
  }

  @Override
  public List<Category> readAll() {
    return getJdbcTemplate().query(
        "SELECT * FROM CATEGORY",
        (resultSet, i) -> mapToCategory(resultSet)
    );
  }

  @Override
  public Category update(Category toUpdate) {
    getJdbcTemplate().update(
        "UPDATE CATEGORY SET NAME = ? WHERE ID = ?",
        toUpdate.getName(),
        toUpdate.getKey()
    );
    return read(toUpdate.getKey());
  }

  @Override
  public Category delete(Long id) {
    Category toBeDeleted = read(id);
    getJdbcTemplate().update("DELETE FROM CATEGORY WHERE ID = ?", id);
    return toBeDeleted;
  }

  @Override
  public boolean exists(Long id) {
    return getJdbcTemplate().queryForObject(
        "SELECT count(ID) FROM CATEGORY WHERE ID = ?",
        Long.class,
        id
    ) == 1;
  }

  @Override
  Long getMaxId() {
    return getJdbcTemplate().queryForObject("SELECT max(ID) FROM CATEGORY", Long.class);
  }

  @Override
  public List<Category> findCategoriesForPost(Long postId) {
    return getJdbcTemplate().query(
        "SELECT * FROM POST_CATEGORY pc JOIN CATEGORY c ON c.ID = pc.CATEGORY_ID WHERE pc.POST_ID = ?",
        new Object[]{postId},
        (resultSet, i) -> mapToCategory(resultSet)
    );
  }

  private static Category mapToCategory(ResultSet resultSet) throws SQLException {
    Category category = new Category();
    category.setKey(resultSet.getLong("ID"));
    category.setName(resultSet.getString("NAME"));

    return category;
  }
}
