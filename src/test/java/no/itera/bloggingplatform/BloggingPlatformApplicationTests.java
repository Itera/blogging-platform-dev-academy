package no.itera.bloggingplatform;

import no.itera.bloggingplatform.model.Author;
import no.itera.bloggingplatform.repository.AuthorRepository;
import no.itera.bloggingplatform.service.AuthorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BloggingPlatformApplicationTests {

	@Autowired
	private AuthorRepository authorRepository;

	@Test
	public void contextLoads() {
		Author author = new Author();
		author.setEmail("name@email.com");

		System.out.println(author.getKey()); // toto bude null

		authorRepository.create(author);

		author = authorRepository.read(author.getKey());

		System.out.println(author.getKey()); // toto uz bude 1
	}

}
