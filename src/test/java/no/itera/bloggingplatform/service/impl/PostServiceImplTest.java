package no.itera.bloggingplatform.service.impl;

import no.itera.bloggingplatform.model.Author;
import no.itera.bloggingplatform.model.Category;
import no.itera.bloggingplatform.model.Post;
import no.itera.bloggingplatform.repository.PostRepository;
import no.itera.bloggingplatform.service.AuthorService;
import no.itera.bloggingplatform.service.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PostServiceImplTest {

    private PostServiceImpl postService;

    @Mock
    private PostRepository postRepository;

    @Mock
    private AuthorService authorService;

    @Mock
    private CategoryService categoryService;

    private Post post;

    @Before
    public void before() {
        postService = new PostServiceImpl(
                postRepository,
                authorService,
                categoryService
        );

        createPost();
    }

    @Test
    public void shouldCreateNewPost() {
        postService.createNewPost(post);

        verify(postRepository).create(post);
        verify(authorService).createAuthor(any(Author.class));
        verify(categoryService, times(2)).createCategory(any(Category.class));
    }

    @Test
    public void shouldCreateNewPostWithoutPersistingAuthor() {
        when(authorService.exists(post.getAuthor())).thenReturn(true);

        postService.createNewPost(post);

        verify(postRepository).create(post);
        verify(authorService).exists(any(Author.class));
        verifyNoMoreInteractions(authorService);
        verify(categoryService, times(2)).createCategory(any(Category.class));
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionWhenCreatingAuthor() {
        doThrow(new RuntimeException()).when(authorService).createAuthor(any(Author.class));

        postService.createNewPost(post);
    }

    private void createPost() {
        post = new Post();
        post.setTitle("New post");

        Category books = new Category();
        books.setName("Books");
        Category films = new Category();
        films.setName("Films");

        post.setCategories(Arrays.asList(books, films));
        post.setDate(new Date());
        post.setPerex("Short content");
        post.setContent("Long content");

        Author author = new Author();
        author.setFirstName("Xavier");
        author.setLastName("Testerson");
        author.setPhone("0900123456");
        author.setUserName("xavier_t");
        author.setPassword("12345".toCharArray());
        author.setEmail("xavier_testerson@gmail.com");

        post.setAuthor(author);
    }
}