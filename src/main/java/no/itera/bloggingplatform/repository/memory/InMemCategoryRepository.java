package no.itera.bloggingplatform.repository.memory;

import static no.itera.bloggingplatform.constant.ProfileDefinition.IN_MEMORY_STORAGE;

import java.util.List;

import no.itera.bloggingplatform.model.Category;
import no.itera.bloggingplatform.repository.CategoryRepository;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile(IN_MEMORY_STORAGE)
public class InMemCategoryRepository extends DefaultInMemoryRepository<Category> implements CategoryRepository {

  @Override
  public List<Category> findCategoriesForPost(Long postId) {
    throw new IllegalArgumentException("Not implemented for IN_MEMORY_STORAGE.");
  }
}
