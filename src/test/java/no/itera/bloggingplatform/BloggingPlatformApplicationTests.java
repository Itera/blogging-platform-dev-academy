package no.itera.bloggingplatform;

import no.itera.bloggingplatform.model.Author;
import no.itera.bloggingplatform.repository.AuthorRepository;
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

		//rozsirenie testu:

		author.setFirstName("Povodny");
		author.setLastName("Autorovic");
		authorRepository.update(author.getKey(), author);

		Author nextAuthor = new Author();
		nextAuthor.setEmail("autor@najvacsi.net");
		nextAuthor.setFirstName("Dalsi");
		nextAuthor.setLastName("Autoricenko");
		authorRepository.create(nextAuthor);

		for (Author authorInRepo : authorRepository.readAll())
			System.out.println(authorRepository.read(authorInRepo.getKey()).getFirstName());
	}

}
