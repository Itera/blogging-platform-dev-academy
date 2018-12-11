package no.itera.bloggingplatform.repository.database;

import no.itera.bloggingplatform.model.Comment;
import no.itera.bloggingplatform.repository.CommentRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static no.itera.bloggingplatform.constant.ProfileDefinition.DATABASE_STORAGE;

@Repository
@Profile(DATABASE_STORAGE)
public class DbCommentRepository extends AbstractDbRepository implements CommentRepository {

    private String tableName = "comment";

    public DbCommentRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public List<Comment> findByPostId(Long postId) {
        String sql = "SELECt * FROM "+tableName+" WHERE POST_ID = ?";
        return getJdbcTemplate().query(sql, new Object[] {postId}, ((resultSet, i) -> commentMapper(resultSet)));
    }

    @Override
    public Comment create(Comment toPersist) {
        Long nextId = getNextId(tableName);

        String sql = "INSERT INTO " +tableName+ " (ID, CREATED, CONTENT, USERNAME, POST_ID) VALUES (?, ?, ?, ?)";

        int row = getJdbcTemplate().update(sql,
                nextId,
                toPersist.getDate(),
                toPersist.getContent(),
                toPersist.getAuthorName(),
                toPersist.getPostId()
        );

        if (row > 0) {
            return read(nextId);
        } else {
            return new Comment();
        }
    }

    @Override
    public Comment read(Long id) {
        String sql = "SELECT * FROM "+tableName+" WHERE ID = ?";
        return getJdbcTemplate().queryForObject(sql, new Object[] {id}, ((resultSet, i) -> commentMapper(resultSet)));
    }

    @Override
    public List<Comment> readAll() {
        String sql = "SELECt * FROM "+tableName;
        return getJdbcTemplate().query(sql, ((resultSet, i) -> commentMapper(resultSet)));
    }

    @Override
    public Comment update(Comment toUpdate) {
        String sql = "UPDATE "+tableName+" SET CREATED=?, CONTENT=?, USERNAME=?, POST_ID=? WHERE id = ? LIMIT 1";
        getJdbcTemplate().update(sql,
                toUpdate.getDate(),
                toUpdate.getContent(),
                toUpdate.getAuthorName(),
                toUpdate.getPostId(),
                toUpdate.getKey()
        );

        return read(toUpdate.getKey());
    }

    @Override
    public Comment delete(Long id) {
        Comment deleteComment = read(id);
        delete(id, tableName);
        return deleteComment;
    }

    @Override
    public boolean exists(Long id) {
        return exists(id, tableName);
    }

    private Comment commentMapper(ResultSet resultSet) throws SQLException {
        Comment comment = new Comment();

        comment.setKey(resultSet.getLong("ID"));
        comment.setDate(resultSet.getDate("CREATED"));
        comment.setContent(resultSet.getString("CONTENT"));
        comment.setAuthorName(resultSet.getString("USERNAME"));
        comment.setPostId(resultSet.getLong("POST_ID"));

        return comment;
    }
}
