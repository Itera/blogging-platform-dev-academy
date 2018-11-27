package no.itera.bloggingplatform.controller;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

import static no.itera.bloggingplatform.controller.dto.ImmutablePostDto.copyOf;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.Date;
import java.util.List;

import no.itera.bloggingplatform.controller.dto.ImmutableAuthorDto;
import no.itera.bloggingplatform.controller.dto.ImmutableCategoryDto;
import no.itera.bloggingplatform.controller.dto.ImmutablePostDto;
import no.itera.bloggingplatform.controller.dto.PostDto;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/posts", produces = APPLICATION_JSON_VALUE)
public class PostController {

  @RequestMapping(method = GET)
  @ResponseStatus(HttpStatus.OK)
  public List<PostDto> fetchAllPosts() {
    return singletonList(getMockPost());
  }

  @RequestMapping(method = POST, consumes = APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public PostDto createPost(@RequestBody PostDto post) {
    return copyOf(post).withId(23L).withTitle("Mock title - just created");
  }

  @RequestMapping(method = PUT)
  @ResponseStatus(HttpStatus.OK)
  public PostDto updatePost(@RequestBody PostDto post) {
    return post;
  }

  @RequestMapping(value = "/findByAuthor", method = GET)
  @ResponseStatus(HttpStatus.OK)
  public List<PostDto> fetchPostsByAuthor(@RequestParam("author") Long[] authorIds) {
    return singletonList(copyOf(getMockPost()).withTitle("Mock title - from /findByAuthor"));
  }

  @RequestMapping(value = "/findByCategory", method = GET)
  @ResponseStatus(HttpStatus.OK)
  public List<PostDto> fetchPostsByCategories(@RequestParam("category") Long[] categories) {
    return singletonList(copyOf(getMockPost()).withTitle("Mock title - from /findByCategory"));
  }

  @RequestMapping(value = "/{postId}", method = GET)
  @ResponseStatus(HttpStatus.OK)
  public PostDto fetchPostById(@PathVariable Long postId) {
    return copyOf(getMockPost()).withId(postId);
  }

  @RequestMapping(value = "/{postId}", method = DELETE)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deletePostById(@PathVariable Long postId) {
  }

  private static PostDto getMockPost() {
    return ImmutablePostDto.builder()
        .id(22L)
        .title("Mock title")
        .perex("mock perex")
        .content("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut .. ")
        .author(ImmutableAuthorDto.builder()
            .id(1L)
            .userName("updated")
            .firstName("John")
            .lastName("Doe")
            .email("")
            .phone("")
            .build())
        .date(new Date())
        .categories(asList(
            ImmutableCategoryDto.of(1L, "sport"),
            ImmutableCategoryDto.of(2L, "movies")
        ))
        .build();
  }
}
