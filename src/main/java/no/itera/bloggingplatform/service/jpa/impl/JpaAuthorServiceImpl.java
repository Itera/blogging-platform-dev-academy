package no.itera.bloggingplatform.service.jpa.impl;

import no.itera.bloggingplatform.model.Author;
import no.itera.bloggingplatform.repository.jpa.AuthorJpaRepository;
import no.itera.bloggingplatform.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Qualifier("JPA")
@Transactional
public class JpaAuthorServiceImpl implements AuthorService {

    private AuthorJpaRepository authorJpaRepository;

    @Autowired
    public JpaAuthorServiceImpl(AuthorJpaRepository authorJpaRepository) {
        this.authorJpaRepository = authorJpaRepository;
    }

    @Override
    public List<Author> listAllAuthors() {
        return authorJpaRepository.findAll();
    }

    @Override
    public Author createAuthor(Author author) {
        return authorJpaRepository.save(author);
    }

    @Override
    public Author findAuthorById(Long authorId) {
        return authorJpaRepository.getOne(authorId);
    }

    @Override
    public Author updateAuthor(Long authorId, Author toUpdate) {
        authorJpaRepository.update(
                authorId,
                toUpdate.getEmail(),
                toUpdate.getFirstName(),
                toUpdate.getLastName(),
                toUpdate.getPhone(),
                toUpdate.getUserName(),
                toUpdate.getPassword()
        );

        return authorJpaRepository.getOne(authorId);
    }

    @Override
    public Author deleteAuthor(Long authorId) {
        Author toDelete = authorJpaRepository.getOne(authorId);

        authorJpaRepository.delete(toDelete);
        authorJpaRepository.flush();

        return toDelete;
    }

    @Override
    public boolean exists(Author author) {
        return authorJpaRepository.existsById(author.getKey());
    }
}
