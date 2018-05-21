package no.itera.bloggingplatform.repository.database;

import static no.itera.bloggingplatform.constant.ProfileDefinition.DATABASE_STORAGE;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import no.itera.bloggingplatform.model.Author;
import no.itera.bloggingplatform.repository.AuthorRepository;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Profile(DATABASE_STORAGE)
public class DbAuthorRepository extends AbstractDbRepository implements AuthorRepository {

  public DbAuthorRepository(JdbcTemplate jdbcTemplate) {
    super(jdbcTemplate);
  }

  @Override
  public Author create(Author toPersist) {
    Long generatedId = getNextId();
    getJdbcTemplate().update(
        "INSERT INTO AUTHOR (ID, USERNAME, FIRSTNAME, LASTNAME, EMAIL, PASSWORD, PHONE) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?);",
        generatedId,
        toPersist.getUserName(),
        toPersist.getFirstName(),
        toPersist.getLastName(),
        toPersist.getEmail(),
        toPersist.getPassword(),
        toPersist.getPhone()
    );

    return read(generatedId);
  }

  @Override
  public Author read(Long id) {
    return getJdbcTemplate().queryForObject(
        "SELECT * FROM AUTHOR WHERE ID = ?",
        new Object[]{id},
        (resultSet, i) -> mapToAuthor(resultSet)
    );
  }

  @Override
  public List<Author> readAll() {
    return getJdbcTemplate().query(
        "SELECT * FROM AUTHOR",
        (resultSet, i) -> mapToAuthor(resultSet)
    );
  }

  @Override
  public Author update(Author toUpdate) {
    getJdbcTemplate().update(
        "UPDATE AUTHOR SET USERNAME = ?, FIRSTNAME = ?, LASTNAME = ?, EMAIL = ?, PHONE = ? WHERE ID = ?",
        toUpdate.getUserName(),
        toUpdate.getFirstName(),
        toUpdate.getLastName(),
        toUpdate.getEmail(),
        toUpdate.getPhone(),
        toUpdate.getKey()
    );
    return read(toUpdate.getKey());
  }

  @Override
  public Author delete(Long id) {
    Author toBeDeleted = read(id);
    getJdbcTemplate().update("DELETE FROM CATEGORY WHERE ID = ?", id);
    return toBeDeleted;
  }

  @Override
  public boolean exists(Long id) {
    return getJdbcTemplate().queryForObject(
        "SELECT count(ID) FROM AUTHOR WHERE ID = ?",
        Long.class,
        id
    ) == 1;
  }

  @Override
  Long getMaxId() {
    return getJdbcTemplate().queryForObject("SELECT max(ID) FROM AUTHOR", Long.class);
  }

  private static Author mapToAuthor(ResultSet resultSet) throws SQLException {
    Author author = new Author();
    author.setKey(resultSet.getLong("ID"));
    author.setUserName(resultSet.getString("USERNAME"));
    author.setFirstName(resultSet.getString("FIRSTNAME"));
    author.setLastName(resultSet.getString("LASTNAME"));
    author.setEmail(resultSet.getString("EMAIL"));
    author.setPassword(resultSet.getString("PASSWORD").toCharArray());
    author.setPhone(resultSet.getString("PHONE"));

    return author;
  }
}
