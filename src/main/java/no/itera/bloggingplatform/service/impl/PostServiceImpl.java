package no.itera.bloggingplatform.service.impl;

import no.itera.bloggingplatform.model.Author;
import no.itera.bloggingplatform.model.Category;
import no.itera.bloggingplatform.model.Comment;
import no.itera.bloggingplatform.model.Post;
import no.itera.bloggingplatform.repository.AuthorRepository;
import no.itera.bloggingplatform.repository.CategoryRepository;
import no.itera.bloggingplatform.repository.CommentRepository;
import no.itera.bloggingplatform.repository.PostRepository;
import no.itera.bloggingplatform.repository.memory.AuthorRepositoryImpl;
import no.itera.bloggingplatform.repository.memory.CategoryRepositoryImpl;
import no.itera.bloggingplatform.repository.memory.CommentRepositoryImpl;
import no.itera.bloggingplatform.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    private CategoryRepository categoryRepository;

    private AuthorRepository authorRepository;
    private CommentRepository commentRepository;

    private List<Category> categories;
    private List<Author> authors;
    private List<Comment> comments;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, CategoryRepository categoryRepository, AuthorRepository authorRepository, CommentRepository commentRepository){
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
        this.authorRepository = authorRepository;
        this.commentRepository = commentRepository;

        comments = new ArrayList<>();
        categories = new ArrayList<>();
        authors = new ArrayList<>();
        categories = categoryRepository.readAll();
        authors = authorRepository.readAll();

    }

    @Override
    public List<Post> listAllPosts() {
        return postRepository.readAll();
    }

    @Override
    public Post createNewPost(Post post) {
        if (post.getCategories() != null) {
            for (Category category : post.getCategories()) {
                if (!categories.contains(category)) {
                    categoryRepository.create(category);
                    categories = categoryRepository.readAll();
                }
            }
        }

        if (post.getAuthor() != null) {
            if (!authors.contains(post.getAuthor())) {
                authorRepository.create(post.getAuthor());
                authors = authorRepository.readAll();
            }
        }

        return postRepository.create(post);
    }

    @Override
    public List<Post> findByAuthor(Long authorId) {
        return postRepository.findByAuthor(authorId);
    }

    @Override
    public List<Post> findByCategory(Long categoryId) {
        return postRepository.findByCategory(categoryId);
    }

    @Override
    public Post findPostById(Long postId) {
        return postRepository.read(postId);
    }

    @Override
    public Post updatePost(Long postId, Post toUpdate) {
        for (Category category: toUpdate.getCategories()){
            if (!categories.contains(category)){
                categoryRepository.create(category);
                categories = categoryRepository.readAll();
            }
        }

        return postRepository.update(toUpdate);
    }

    @Override
    public Post deletePost(Long postId) {
        comments = commentRepository.findByPostId(postId);
        if (!comments.isEmpty()){
            for (Comment comment: comments){
                commentRepository.delete(comment.getPostId());
            }
        }
        return postRepository.delete(postId);
    }
}
