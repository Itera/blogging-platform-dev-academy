package no.itera.bloggingplatform.controller;

import static no.itera.bloggingplatform.controller.mapper.PostMapper.mapListToApi;
import static no.itera.bloggingplatform.controller.mapper.PostMapper.mapToApi;
import static no.itera.bloggingplatform.controller.mapper.PostMapper.mapToDomain;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import no.itera.bloggingplatform.controller.dto.PostDto;
import no.itera.bloggingplatform.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/post", produces = APPLICATION_JSON_VALUE)
public class PostController {

  private PostService postService;

  @Autowired
  public PostController(PostService postService) {
    this.postService = postService;
  }

  @RequestMapping(method = GET)
  @ResponseStatus(HttpStatus.OK)
  public List<PostDto> fetchAllPosts() {
    return mapListToApi(
        postService.listAllPosts()
    );
  }

  @RequestMapping(method = POST)
  @ResponseStatus(HttpStatus.CREATED)
  public PostDto createPost(@RequestBody PostDto post) {
    return mapToApi(
        postService.createNewPost(mapToDomain(post))
    );
  }

  @RequestMapping(method = PUT)
  @ResponseStatus(HttpStatus.OK)
  public PostDto updatePost(@RequestBody PostDto post) {
    return mapToApi(
        postService.updatePost(post.getId(), mapToDomain(post))
    );
  }

  @RequestMapping(value = "/findByAuthor", method = GET)
  @ResponseStatus(HttpStatus.OK)
  public List<PostDto> fetchPostsByAuthor(@RequestParam("author") Long[] authorIds) {
    return mapListToApi(
        Arrays.stream(authorIds)
            .map(authorId -> postService.findByAuthor(authorId))
            .flatMap(List::stream)
            .collect(Collectors.toList())
    );
  }

  @RequestMapping(value = "/findByCategory", method = GET)
  @ResponseStatus(HttpStatus.OK)
  public List<PostDto> fetchPostsByCategories(@RequestParam("category") Long[] categories) {
    return mapListToApi(
        Arrays.stream(categories)
            .map(categoryId -> postService.findByCategory(categoryId))
            .flatMap(List::stream)
            .collect(Collectors.toList())
    );
  }

  @RequestMapping(value = "/{postId}", method = GET)
  @ResponseStatus(HttpStatus.OK)
  public PostDto fetchPostById(@PathVariable Long postId) {
    return mapToApi(
        postService.findPostById(postId)
    );
  }

  @RequestMapping(value = "/{postId}", method = DELETE)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deletePostById(@PathVariable Long postId) {
    postService.deletePost(postId);
  }
}
