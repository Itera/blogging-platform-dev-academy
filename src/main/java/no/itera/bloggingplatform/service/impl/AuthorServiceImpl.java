package no.itera.bloggingplatform.service.impl;

import no.itera.bloggingplatform.model.Author;
import no.itera.bloggingplatform.model.Post;
import no.itera.bloggingplatform.repository.AuthorRepository;
import no.itera.bloggingplatform.repository.PostRepository;
import no.itera.bloggingplatform.repository.memory.PostRepositoryImpl;
import no.itera.bloggingplatform.service.AuthorService;
import no.itera.bloggingplatform.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    private PostRepository postRepository;
    private List<Post> posts;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, PostRepository postRepository) {
        this.authorRepository = authorRepository;
        this.postRepository = postRepository;

        posts = new ArrayList<>();
    }

    @Override
    public List<Author> listAllAuthors() {
        return authorRepository.readAll();
    }

    @Override
    public Author createAuthor(Author author) {
        return authorRepository.create(author);
    }

    @Override
    public Author findAuthorById(Long authorId) {
        return authorRepository.read(authorId);
    }

    @Override
    public Author updateAuthor(Long authorId, Author toUpdate) {
        return authorRepository.update(toUpdate);
    }

    @Override
    public Author deleteAuthor(Long authorId) {
        posts = postRepository.findByAuthor(authorId);

        if (!posts.isEmpty()){
            for (Post post: posts){
                postRepository.delete(post.getKey());
            }
        }

        return authorRepository.delete(authorId);
    }
}
