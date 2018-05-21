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

  Long getNextId() {
    return getMaxId() + 1;
  }

  abstract Long getMaxId();
}
