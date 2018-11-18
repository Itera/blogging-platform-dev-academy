package no.itera.bloggingplatform.repository.memory;

import no.itera.bloggingplatform.model.Category;
import no.itera.bloggingplatform.model.Post;
import no.itera.bloggingplatform.repository.PostRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PostRepositoryImpl extends BasicRepositoryImpl<Post> implements PostRepository {
    @Override
    public List<Post> findByAuthor(Long authorId) {
        List<Post> foundPosts = new ArrayList<>();

        for (Post post : inMemoryStorage.values()) {
            if (post.getAuthor().getKey().equals(authorId))
                foundPosts.add(post);
        }
        return foundPosts;
    }

    @Override
    public List<Post> findByCategory(Long categoryId) {
        List<Post> foundPosts = new ArrayList<>();

        for (Post post : inMemoryStorage.values()) {
            for (Category category : post.getCategories()) {
                if (category.getKey().equals(categoryId))
                    foundPosts.add(post);
            }
        }
        return foundPosts;
    }


}
