package no.itera.bloggingplatform.repository.memory;

import no.itera.bloggingplatform.model.Category;
import no.itera.bloggingplatform.model.Comment;
import no.itera.bloggingplatform.model.Post;
import no.itera.bloggingplatform.repository.PostRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PostRepositoryImpl extends BasicRepositoryImpl<Post> implements PostRepository {

    @Override
    public List<Post> findByAuthor(Long authorId) {
        List<Post> posts = new ArrayList<>();
        for (Post post: this.readAll()
        ) {
            if (post.getAuthor().getKey().equals(authorId)){
                posts.add(post);
            }
        }
        return posts;
    }

    @Override
    public List<Post> findByCategory(Long categoryId) {
        List<Post> posts = new ArrayList<>();
        for (Post post: this.readAll()
        ) {
            for (Category category: post.getCategories()) {
                if (category.getKey().equals(categoryId)){
                    posts.add(post);
                    break;
                }
            }
        }
        return posts;
    }
}
