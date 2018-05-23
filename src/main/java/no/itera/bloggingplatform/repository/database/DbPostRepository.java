package no.itera.bloggingplatform.repository.database;

import static no.itera.bloggingplatform.constant.ProfileDefinition.DATABASE_STORAGE;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import no.itera.bloggingplatform.model.Post;
import no.itera.bloggingplatform.repository.AuthorRepository;
import no.itera.bloggingplatform.repository.CategoryRepository;
import no.itera.bloggingplatform.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Profile(DATABASE_STORAGE)
public class DbPostRepository extends AbstractDbRepository implements PostRepository {

  private AuthorRepository authorRepository;
  private CategoryRepository categoryRepository;

  @Autowired
  public DbPostRepository(
      JdbcTemplate jdbcTemplate,
      AuthorRepository authorRepository,
      CategoryRepository categoryRepository
  ) {
    super(jdbcTemplate);
    this.authorRepository = authorRepository;
    this.categoryRepository = categoryRepository;
  }

  @Override
  public List<Post> findByAuthor(Long authorId) {
    return getJdbcTemplate().query(
        "SELECT * FROM POST p WHERE p.AUTHOR_ID = ?",
        new Object[]{authorId},
        (resultSet, i) -> mapToPost(resultSet, authorRepository, categoryRepository)
    );
  }

  @Override
  public List<Post> findByCategory(Long categoryId) {
    return getJdbcTemplate().query(
        "SELECT * FROM POST p JOIN POST_CATEGORY ps ON p.ID = ps.POST_ID WHERE ps.CATEGORY_ID = ?",
        new Object[]{categoryId},
        (resultSet, i) -> mapToPost(resultSet, authorRepository, categoryRepository)
    );
  }

  @Override
  public Post create(Post toPersist) {
    Long generatedId = getNextId();
    getJdbcTemplate().update(
        "INSERT INTO POST (ID, TITLE, CREATED, PEREX, CONTENT, AUTHOR_ID) VALUES (?, ?, ?, ?, ?, ?)",
        generatedId,
        toPersist.getTitle(),
        toPersist.getDate(),
        toPersist.getPerex(),
        toPersist.getContent(),
        toPersist.getAuthor().getKey()
    );

    toPersist.getCategories().stream()
        .distinct()
        .forEach(category -> getJdbcTemplate().update(
            "INSERT INTO POST_CATEGORY (POST_ID, CATEGORY_ID) VALUES (?, ?)",
            generatedId,
            category.getKey()
        ));

    return read(generatedId);
  }

  @Override
  public Post read(Long id) {
    return getJdbcTemplate().queryForObject(
        "SELECT * FROM POST WHERE ID = ?",
        new Object[]{id},
        (resultSet, i) -> mapToPost(resultSet, authorRepository, categoryRepository)
    );
  }

  @Override
  public List<Post> readAll() {
    return getJdbcTemplate().query(
        "SELECT * FROM POST",
        (resultSet, i) -> mapToPost(resultSet, authorRepository, categoryRepository)
    );
  }

  @Override
  public Post update(Post toUpdate) {
    getJdbcTemplate().update(
        "UPDATE POST SET TITLE = ?, CREATED = ?, PEREX = ?, CONTENT = ? WHERE ID = ?",
        toUpdate.getTitle(),
        toUpdate.getDate(),
        toUpdate.getPerex(),
        toUpdate.getContent(),
        toUpdate.getKey()
    );
    return read(toUpdate.getKey());
  }

  @Override
  public Post delete(Long id) {
    Post toBeDeleted = read(id);
    getJdbcTemplate().update("DELETE FROM POST WHERE ID = ?", id);
    return toBeDeleted;
  }

  @Override
  public boolean exists(Long id) {
    return getJdbcTemplate().queryForObject(
        "SELECT count(ID) FROM POST WHERE ID = ?",
        Long.class,
        id
    ) == 1;
  }

  @Override
  Long getMaxId() {
    return getJdbcTemplate().queryForObject("SELECT max(ID) FROM POST", Long.class);
  }

  private static Post mapToPost(
      ResultSet resultSet,
      AuthorRepository authorRepository,
      CategoryRepository categoryRepository
  ) throws SQLException {
    Long postId = resultSet.getLong("ID");

    Post post = new Post();
    post.setKey(postId);
    post.setAuthor(authorRepository.read(resultSet.getLong("AUTHOR_ID")));
    post.setTitle(resultSet.getString("TITLE"));
    post.setPerex(resultSet.getString("PEREX"));
    post.setContent(resultSet.getString("CONTENT"));
    post.setDate(resultSet.getDate("CREATED"));
    post.setCategories(categoryRepository.findCategoriesForPost(postId));

    return post;
  }
}
