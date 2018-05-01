package no.itera.bloggingplatform.repository.memory;

import no.itera.bloggingplatform.model.Category;
import no.itera.bloggingplatform.model.Post;
import no.itera.bloggingplatform.repository.PostRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static no.itera.bloggingplatform.constant.ProfileDefinition.IN_MEMORY_STORAGE;

@Repository
@Profile(IN_MEMORY_STORAGE)
public class InMemPostRepository extends DefaultInMemoryRepository<Post> implements PostRepository {

    @Override
    public List<Post> findByAuthor(Long authorId) {
        return readAll()
                .stream()
                .filter(post -> authorId.equals(post.getAuthor().getKey()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Post> findByCategory(Long categoryId) {
        return readAll()
                .stream()
                .filter(post -> post.getCategories()
                        .stream()
                        .map(Category::getKey)
                        .anyMatch(categoryKey -> categoryKey.equals(categoryId)))
                .collect(Collectors.toList());
    }
}
