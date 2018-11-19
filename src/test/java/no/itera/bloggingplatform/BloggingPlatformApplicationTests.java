package no.itera.bloggingplatform;

import no.itera.bloggingplatform.model.Author;
import no.itera.bloggingplatform.model.Category;
import no.itera.bloggingplatform.model.Post;
import no.itera.bloggingplatform.repository.AuthorRepository;
import no.itera.bloggingplatform.service.AuthorService;
import no.itera.bloggingplatform.service.CategoryService;
import no.itera.bloggingplatform.service.PostService;
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

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private PostService postService;

	@Test
	public void contextLoads() {
		Author author = new Author();
		author.setEmail("name@email.com");

		System.out.println(author.getKey()); // toto bude null

		System.out.println("Authors size: " + authorRepository.readAll().size());

		authorRepository.create(author);

		author = authorRepository.read(author.getKey());

		System.out.println(author.getKey()); // toto uz bude 1

		System.out.println("Authors size: " + authorRepository.readAll().size());

		System.out.println("Category size: " + categoryService.listAllCategories().size());

		Category category = new Category();
		category.setName("Test");

		categoryService.createCategory(category);

		System.out.println("Category size: " + categoryService.listAllCategories().size());

		Author author1 = new Author();
		author1.setFirstName("test");
		Post post = new Post();
		post.setAuthor(author1);
		postService.createNewPost(post);

		System.out.println("Authors size: " + authorRepository.readAll().size());
		System.out.println("Post size: " + postService.listAllPosts().size());
	}

}
