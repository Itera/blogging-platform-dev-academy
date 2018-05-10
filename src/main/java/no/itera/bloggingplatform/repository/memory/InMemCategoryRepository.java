package no.itera.bloggingplatform.repository.memory;

import no.itera.bloggingplatform.model.Category;
import no.itera.bloggingplatform.repository.CategoryRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import static no.itera.bloggingplatform.constant.ProfileDefinition.IN_MEMORY_STORAGE;

@Repository
@Profile(IN_MEMORY_STORAGE)
public class InMemCategoryRepository extends DefaultInMemoryRepository<Category> implements CategoryRepository {
}
