package no.itera.bloggingplatform.repository.database;

import static no.itera.bloggingplatform.constant.ProfileDefinition.DATABASE_STORAGE;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import no.itera.bloggingplatform.model.Comment;
import no.itera.bloggingplatform.repository.CommentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Profile(DATABASE_STORAGE)
public class DbCommentRepository extends AbstractDbRepository implements CommentRepository {

  @Autowired
  public DbCommentRepository(JdbcTemplate jdbcTemplate) {
    super(jdbcTemplate);
  }

  @Override
  public List<Comment> findByPostId(Long postId) {
    return getJdbcTemplate().query(
        "SELECT * FROM COMMENT c JOIN POST p ON c.POST_ID = p.ID WHERE c.POST_ID = ?",
        new Object[]{postId},
        (resultSet, i) -> mapToComment(resultSet)
    );
  }

  @Override
  public Comment create(Comment toPersist) {
    Long generatedId = getNextId();
    getJdbcTemplate().update(
        "INSERT INTO COMMENT (ID, CREATED, USERNAME, CONTENT, POST_ID) VALUES (?, ?, ?, ?, ?)",
        generatedId,
        toPersist.getDate(),
        toPersist.getAuthorName(),
        toPersist.getContent(),
        toPersist.getPostId()
    );

    return read(generatedId);
  }

  @Override
  public Comment read(Long id) {
    return getJdbcTemplate().queryForObject(
        "SELECT * FROM COMMENT WHERE ID = ?",
        new Object[]{id},
        (resultSet, i) -> mapToComment(resultSet)
    );
  }

  @Override
  public List<Comment> readAll() {
    return getJdbcTemplate().query(
        "SELECT * FROM COMMENT",
        (resultSet, i) -> mapToComment(resultSet)
    );
  }

  @Override
  public Comment update(Comment toUpdate) {
    getJdbcTemplate().update(
        "UPDATE COMMENT SET CREATED = ?, USERNAME = ?, CONTENT = ? WHERE ID = ?",
        toUpdate.getDate(),
        toUpdate.getAuthorName(),
        toUpdate.getContent(),
        toUpdate.getKey()
    );
    return read(toUpdate.getKey());
  }

  @Override
  public Comment delete(Long id) {
    Comment toBeDeleted = read(id);
    getJdbcTemplate().update("DELETE FROM COMMENT WHERE ID = ?", id);
    return toBeDeleted;
  }

  @Override
  public boolean exists(Long id) {
    return getJdbcTemplate().queryForObject(
        "SELECT count(ID) FROM COMMENT WHERE ID = ?",
        Long.class,
        id
    ) == 1;
  }

  @Override
  Long getMaxId() {
    return getJdbcTemplate().queryForObject("SELECT max(ID) FROM COMMENT", Long.class);
  }

  private Comment mapToComment(ResultSet resultSet) throws SQLException {
    Comment comment = new Comment();
    comment.setKey(resultSet.getLong("ID"));
    comment.setAuthorName(resultSet.getString("USERNAME"));
    comment.setContent(resultSet.getString("CONTENT"));
    comment.setPostId(resultSet.getLong("POST_ID"));
    comment.setDate(resultSet.getDate("CREATED"));
    return comment;
  }
}
