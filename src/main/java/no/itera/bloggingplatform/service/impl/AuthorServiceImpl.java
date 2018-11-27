package no.itera.bloggingplatform.service.impl;

import no.itera.bloggingplatform.model.Author;
import no.itera.bloggingplatform.model.Post;
import no.itera.bloggingplatform.repository.AuthorRepository;
import no.itera.bloggingplatform.repository.PostRepository;
import no.itera.bloggingplatform.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;
    private PostRepository postRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, PostRepository postRepository) {
        this.authorRepository = authorRepository;
        this.postRepository = postRepository;
    }

    @Override
    public List<Author> listAllAuthors() {
        return authorRepository.readAll();
    }

    @Override
    public Author createAuthor(Author author) {
        if (authorExists(author)) {
            if (author.getKey() == null) {
                return listAllAuthors().stream().filter(it -> it.getUserName().equals(author.getUserName()))
                    .findFirst().orElseThrow(RuntimeException::new);
            }
            return author;
        }
        return authorRepository.create(author);
    }

    @Override
    public Author findAuthorById(Long authorId) {
        return authorRepository.read(authorId);
    }

    @Override
    public Author updateAuthor(Long authorId, Author toUpdate) {
        toUpdate.setKey(authorId);
        return authorRepository.update(toUpdate);
    }

    @Override
    public Author deleteAuthor(Long authorId) {
        postRepository.findByAuthor(authorId).stream().map(Post::getKey).forEach(it -> postRepository.delete(it));
        return authorRepository.delete(authorId);
    }

    private boolean authorExists(Author author) {
        return listAllAuthors()
            .stream()
            .anyMatch(it -> it.getUserName().equals(author.getUserName()));
    }

}
