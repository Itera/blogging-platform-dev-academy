package no.itera.bloggingplatform.repository.memory;

import no.itera.bloggingplatform.model.Category;
import no.itera.bloggingplatform.repository.CategoryRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepositoryImpl extends BasicRepositoryImpl<Category> implements CategoryRepository {
}
