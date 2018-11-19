package no.itera.bloggingplatform.service.impl;

import no.itera.bloggingplatform.model.Author;
import no.itera.bloggingplatform.model.Comment;
import no.itera.bloggingplatform.model.Post;
import no.itera.bloggingplatform.repository.AuthorRepository;
import no.itera.bloggingplatform.repository.CommentRepository;
import no.itera.bloggingplatform.repository.PostRepository;
import no.itera.bloggingplatform.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;
    private PostRepository postRepository;
    private CommentRepository commentRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, PostRepository postRepository, CommentRepository commentRepository) {
        this.authorRepository = authorRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Author> listAllAuthors() {
        return authorRepository.readAll();
    }

    @Override
    public Author createAuthor(Author author) {
        authorRepository.create(author);
        return null;
    }

    @Override
    public Author findAuthorById(Long authorId) {
        return authorRepository.read(authorId);
    }

    @Override
    public Author updateAuthor(Long authorId, Author toUpdate) {

        return authorRepository.update(authorId, toUpdate);
    }

    @Override
    public Author deleteAuthor(Long authorId) {

        List<Post> authorsPosts = postRepository.findByAuthor(authorId);
        List<Comment> postComments = new ArrayList<>();

        for (Post authorsPost : authorsPosts) {
            postComments = commentRepository.findByPostId(authorsPost.getKey());

            for (Comment postComment : postComments) {
                commentRepository.delete(postComment.getKey());
            }

            postRepository.delete(authorsPost.getKey());
        }

        return authorRepository.delete(authorId);
    }
}
