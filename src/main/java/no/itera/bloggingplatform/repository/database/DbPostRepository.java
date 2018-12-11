package no.itera.bloggingplatform.repository.database;

import no.itera.bloggingplatform.model.Author;
import no.itera.bloggingplatform.model.Category;
import no.itera.bloggingplatform.model.Post;
import no.itera.bloggingplatform.repository.AuthorRepository;
import no.itera.bloggingplatform.repository.CategoryRepository;
import no.itera.bloggingplatform.repository.PostRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static no.itera.bloggingplatform.constant.ProfileDefinition.DATABASE_STORAGE;

@Repository
@Profile(DATABASE_STORAGE)
public class DbPostRepository extends AbstractDbRepository implements PostRepository {

    public DbPostRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    String tableName = "post";

    @Override
    public List<Post> findByAuthor(Long authorId) {
        String sql = "SELECT * FROM "+tableName+" WHERE AUTHOR_ID = ?";
        return getJdbcTemplate().query(sql, new Object[] {authorId}, ((resultSet, i) -> postMapper(resultSet)));
    }

    @Override
    public List<Post> findByCategory(Long categoryId) {
       String sql = "SELECT * FROM "+tableName+", POST_CATEGORY WHERE "+tableName+".id = POST_CATEGORY.POST_ID AND POST_CATEGORY.CATEGORY_ID = ?";
       return getJdbcTemplate().query(sql, new Object[] {categoryId}, ((resultSet, i) -> postMapper(resultSet)));
    }

    @Override
    public Post create(Post toPersist) {
        Long nextId = getNextId(tableName);
        String sql = "INSERT INTO "+tableName+" (ID, TITLE, CREATED, PEREX, CONTENT, AUTHOR_ID) VALUES (?, ?, ?, ?, ?, ?)";

        getJdbcTemplate().update(
                sql,
                nextId,
                toPersist.getTitle(),
                toPersist.getDate(),
                toPersist.getPerex(),
                toPersist.getContent(),
                toPersist.getAuthor().getKey()
        );

        sql = "INSERT INTO POST_CATEGORY (POST_ID, CATEGORY_ID) VALUES (?, ?)";
        for (Category category : toPersist.getCategories()) {
            getJdbcTemplate().update(sql, nextId, category.getKey());
        }

        return read(nextId);

    }

    @Override
    public Post read(Long id) {
        String sql = "SELECT * FROM "+tableName+" WHERE ID = ?";
        return getJdbcTemplate().queryForObject(sql, new Object[] {id}, ((resultSet, i) -> postMapper(resultSet)));
    }

    @Override
    public List<Post> readAll() {
        String sql = "SELECT * FROM "+tableName;
        return getJdbcTemplate().query(sql, ((resultSet, i) -> postMapper(resultSet)));
    }

    @Override
    public Post update(Post toUpdate) {
        String sql = "UPDATE "+tableName+" SET TITLE=?, CREATED=?, PEREX=?, CONTENT=?, AUTHOR_ID=? WHERE id = ? LIMIT 1";
        getJdbcTemplate().update(sql,
                toUpdate.getTitle(),
                toUpdate.getDate(),
                toUpdate.getPerex(),
                toUpdate.getContent(),
                toUpdate.getAuthor(),
                toUpdate.getKey()
        );

        sql = "DELETE FROM POST_CATEGORY WHERE post_id = ?";
        getJdbcTemplate().update(sql, toUpdate.getKey());

        sql = "INSERT INTO POST_CATEGORY (POST_ID, CATEGORY_ID) VALUES (?, ?)";
        for (Category category : toUpdate.getCategories()) {
            getJdbcTemplate().update(sql, toUpdate.getKey(), category.getKey());
        }

        return read(toUpdate.getKey());
    }

    @Override
    public Post delete(Long id) {
        Post deletePost = read(id);
        delete(id, tableName);

        String sql = "DELETE FROM POST_CATEGORY WHERE post_id = ?";
        getJdbcTemplate().update(sql, id);

        return deletePost;
    }

    @Override
    public boolean exists(Long id) {
        return exists(id, "post");
    }

    private Post postMapper(ResultSet resultSet) throws SQLException {
        Post post = new Post();
        AuthorRepository authorRepository = new DbAuthorRepository(getJdbcTemplate());
        List<Category> categoriesList = new ArrayList<>();

        Author author = authorRepository.read(resultSet.getLong("AUTHOR_ID"));

        CategoryRepository categoryRepository = new DbCategoryRepository(getJdbcTemplate());
        String sql = "SELECT CATEGORY_ID FROM POST_CATEGORY WHERE POST_ID = ?";
        List<Map<String, Object>> list = getJdbcTemplate().queryForList(sql, new Object[] {resultSet.getLong("ID")});
        for (Map<String, Object> row : list) {
            categoriesList.add(categoryRepository.read((Long) row.get("CATEGORY_ID")));
        }

        post.setKey(resultSet.getLong("ID"));
        post.setDate(resultSet.getDate("CREATED"));
        post.setPerex(resultSet.getString("PEREX"));
        post.setContent(resultSet.getString("CONTENT"));
        post.setTitle(resultSet.getString("TITLE"));
        post.setAuthor(author);
        post.setCategories(categoriesList);
        return post;
    }

}
