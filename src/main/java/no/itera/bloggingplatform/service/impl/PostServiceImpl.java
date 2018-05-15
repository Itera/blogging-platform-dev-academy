package no.itera.bloggingplatform.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import no.itera.bloggingplatform.model.Author;
import no.itera.bloggingplatform.model.Category;
import no.itera.bloggingplatform.model.Post;
import no.itera.bloggingplatform.repository.PostRepository;
import no.itera.bloggingplatform.service.AuthorService;
import no.itera.bloggingplatform.service.CategoryService;
import no.itera.bloggingplatform.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

  private PostRepository postRepository;
  private AuthorService authorService;
  private CategoryService categoryService;

  @Autowired
  public PostServiceImpl(PostRepository postRepository, AuthorService authorService, CategoryService categoryService) {
    this.postRepository = postRepository;
    this.authorService = authorService;
    this.categoryService = categoryService;
  }

  @Override
  public List<Post> listAllPosts() {
    return postRepository.readAll();
  }

  @Override
  public Post createNewPost(Post post) {
    if (post.getKey() != null) {
      return post;
    }

    Author persistedAuthor;
    if (!authorService.exists(post.getAuthor())) {
      persistedAuthor = authorService.createAuthor(post.getAuthor());
    } else {
      persistedAuthor = post.getAuthor();
    }

    List<Category> persistedCategories = post.getCategories()
        .stream()
        .map(category -> categoryService.createCategory(category))
        .collect(Collectors.toList());

    post.setAuthor(persistedAuthor);
    post.setCategories(persistedCategories);

    return postRepository.create(post);
  }

  @Override
  public List<Post> findByAuthor(Long authorId) {
    return postRepository.findByAuthor(authorId);
  }

  @Override
  public List<Post> findByCategory(Long categoryId) {
    return postRepository.findByCategory(categoryId);
  }

  @Override
  public Post findPostById(Long postId) {
    return postRepository.read(postId);
  }

  @Override
  public Post updatePost(Long postId, Post toUpdate) {
    toUpdate.setKey(postId);

    toUpdate.setAuthor(authorService.updateAuthor(toUpdate.getAuthor().getKey(), toUpdate.getAuthor()));
    toUpdate.setCategories(toUpdate.getCategories().stream().map(
        category -> categoryService.updateCategory(category.getKey(), category)
    ).collect(Collectors.toList()));

    return postRepository.update(toUpdate);
  }

  @Override
  public Post deletePost(Long postId) {
    return postRepository.delete(postId);
  }
}
