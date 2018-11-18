package no.itera.bloggingplatform.repository.memory;

import no.itera.bloggingplatform.model.Author;
import no.itera.bloggingplatform.repository.AuthorRepository;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorRepositoryImpl extends BasicRepositoryImpl<Author> implements AuthorRepository {
}
