package no.itera.bloggingplatform.service.jpa.impl;

import no.itera.bloggingplatform.model.Author;
import no.itera.bloggingplatform.service.AuthorService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AuthorServiceImplTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Autowired
    @Qualifier("JPA")
    private AuthorService jpaAuthorService;

    @Test
    public void shouldPerformCrudOperations() {
        // Create
        Author author = createAuthor();

        Author persistedAuthor = jpaAuthorService.createAuthor(author);

        assertNotNull(persistedAuthor.getKey());

        // Read
        Author loaded = jpaAuthorService.findAuthorById(persistedAuthor.getKey());
        assertEquals(persistedAuthor, loaded);

        // Update
        Author toUpdate = createAuthor();
        toUpdate.setFirstName("Jozef");

        Author updated = jpaAuthorService.updateAuthor(persistedAuthor.getKey(), toUpdate);

        assertEquals("Jozef", updated.getFirstName());

        // Delete
        jpaAuthorService.deleteAuthor(persistedAuthor.getKey());

        expectedException.expect(EntityNotFoundException.class);
        expectedException.expectMessage("Unable to find no.itera.bloggingplatform.model.Author with id " + persistedAuthor.getKey());

        assertNull(jpaAuthorService.findAuthorById(persistedAuthor.getKey()));
    }

    private Author createAuthor() {
        Author author = new Author();

        author.setEmail("some@email.com");
        author.setFirstName("Xavier");
        author.setLastName("Testerson");
        author.setPassword("swordfish".toCharArray());
        author.setUserName("testerson");
        author.setPhone("0900123456");

        return author;
    }

}