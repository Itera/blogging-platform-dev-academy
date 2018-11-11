package no.itera.bloggingplatform.service.impl;

import no.itera.bloggingplatform.model.Author;
import no.itera.bloggingplatform.repository.AuthorRepository;
import no.itera.bloggingplatform.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
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
        toUpdate.setKey(authorId);
        return authorRepository.update(toUpdate);
    }

    @Override
    public Author deleteAuthor(Long authorId) {
        return authorRepository.delete(authorId);
    }

    @Override
    public boolean exists(Author author) {
        return authorRepository.exists(author.getKey());
    }
}
