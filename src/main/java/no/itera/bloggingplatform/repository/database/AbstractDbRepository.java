package no.itera.bloggingplatform.repository.database;

import org.springframework.jdbc.core.JdbcTemplate;

public abstract class AbstractDbRepository {

  private final JdbcTemplate jdbcTemplate;

  public AbstractDbRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  JdbcTemplate getJdbcTemplate() {
    return jdbcTemplate;
  }

  Long getNextId(String tableName) {
    String sql = "SELECT max(id)+1 FROM " + tableName;
    return jdbcTemplate.queryForObject(sql, Long.class);
  }

  boolean exists(Long id, String tableName) {
    String sql = "SELECT COUNT(id) FROM " + tableName + " WHERE id = ?";
    return jdbcTemplate.queryForObject(sql, Long.class, id) > 0;
  }

  boolean delete(Long id, String tableName) {
    String sql = "DELETE FROM " + tableName + " WHERE id = ?";
    return (jdbcTemplate.update(sql, new Object[] { id })) > 0;

  }

//  abstract Long getMaxId();
}
