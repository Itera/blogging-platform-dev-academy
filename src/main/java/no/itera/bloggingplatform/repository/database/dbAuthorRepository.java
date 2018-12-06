package no.itera.bloggingplatform.repository.database;

import no.itera.bloggingplatform.model.Author;
import no.itera.bloggingplatform.repository.AuthorRepository;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static no.itera.bloggingplatform.constant.ProfileDefinition.DATABASE_STORAGE;

@Repository
@Profile(DATABASE_STORAGE)
public class DbAuthorRepository extends AbstractDbRepository implements AuthorRepository {

    private String tableName = "author";

    public DbAuthorRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public Author create(Author toPersist) {
        Long nextId = getNextId(tableName);

        String password = "default";
        if (toPersist.getPassword() != null)
            password = new String(toPersist.getPassword());

        int row = getJdbcTemplate().update("INSERT INTO author (ID, USERNAME, FIRSTNAME, LASTNAME, EMAIL, PASSWORD, PHONE) VALUES (?, ?, ?, ?, ?, ?, ?)",
                nextId,
                toPersist.getUserName(),
                toPersist.getFirstName(),
                toPersist.getLastName(),
                toPersist.getEmail(),
                password,
                toPersist.getPhone()
        );

        if (row > 0) {
            return read(nextId);
        } else {
            return new Author();
        }
    }

    @Override
    public Author read(Long id) {
        String sql = "SELECT * FROM author WHERE id = ? limit 1";
        return getJdbcTemplate().queryForObject(sql, new Object[] {id}, ((resultSet, i) -> authorMapper(resultSet)));
    }

    @Override
    public List<Author> readAll() {
        String sql = "SELECt * FROM author";
        return getJdbcTemplate().query(sql, ((resultSet, i) -> authorMapper(resultSet)));
    }

    @Override
    public Author update(Author toUpdate) {

        String password = "default";
        if (toUpdate.getPassword() != null)
            password = new String(toUpdate.getPassword());

        String sql = "UPDATE author SET FIRSTNAME=?, EMAIL=?, PHONE=?, USERNAME=?, LASTNAME=?, PASSWORD=? WHERE id = ? LIMIT 1";
        getJdbcTemplate().update(sql,
                toUpdate.getFirstName(),
                toUpdate.getEmail(),
                toUpdate.getPhone(),
                toUpdate.getUserName(),
                toUpdate.getLastName(),
                password,
                toUpdate.getKey() );

        return read(toUpdate.getKey());

    }

    @Override
    public Author delete(Long id) {
        Author deleteAuthor = read(id);
        delete(id, tableName);
        return deleteAuthor;
    }

    private Author authorMapper(ResultSet resultSet) throws SQLException {
        Author author = new Author();

        author.setKey(resultSet.getLong("ID"));
        author.setFirstName(resultSet.getString("FIRSTNAME"));
        author.setEmail(resultSet.getString("EMAIL"));
        author.setPhone(resultSet.getString("PHONE"));
        author.setUserName(resultSet.getString("USERNAME"));
        author.setLastName(resultSet.getString("LASTNAME"));
        author.setPassword(resultSet.getString("PASSWORD").toCharArray());

        return author;
    }

    @Override
    public boolean exists(Long id) {
        return exists(id, tableName);
    }
}
