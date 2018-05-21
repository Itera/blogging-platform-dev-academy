package no.itera.bloggingplatform.repository;

import java.util.List;

import no.itera.bloggingplatform.model.Category;

public interface CategoryRepository extends BasicRepository<Category> {
  List<Category> findCategoriesForPost(Long postId);
}
