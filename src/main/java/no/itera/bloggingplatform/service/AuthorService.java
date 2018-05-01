package no.itera.bloggingplatform.service;

import no.itera.bloggingplatform.model.Author;

import java.util.List;

public interface AuthorService {

    List<Author> listAllAuthors();

    Author createAuthor(Author author);

    Author findAuthorById(Long authorId);

    Author updateAuthor(Long authorId, Author toUpdate);

    Author deleteAuthor(Long authorId);

}
