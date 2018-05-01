package no.itera.bloggingplatform.repository.memory;

import no.itera.bloggingplatform.model.Category;
import no.itera.bloggingplatform.repository.CategoryRepository;

public class InMemCategoryRepository extends DefaultInMemoryRepository<Category> implements CategoryRepository {
}
