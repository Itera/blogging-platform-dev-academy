package no.itera.bloggingplatform.service.impl;

import no.itera.bloggingplatform.constant.ProfileDefinition;
import no.itera.bloggingplatform.model.Author;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(ProfileDefinition.IN_MEMORY_STORAGE)
public class AuthorServiceImplTest {

    @Autowired
    private AuthorServiceImpl authorService;

    private Author author;

    @Before
    public void before() {
        author = new Author();
        author.setFirstName("Xavier");
        author.setLastName("Testerson");
        author.setPhone("0900123456");
        author.setUserName("xavier_t");
        author.setPassword("12345".toCharArray());
        author.setEmail("xavier_testerson@gmail.com");
    }

    @Test
    public void shouldCreateNewAuthor() {
        Author persisted = authorService.createAuthor(author);

        assertNotNull(persisted.getKey());
    }

}