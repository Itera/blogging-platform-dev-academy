package no.itera.bloggingplatform.controller;

import no.itera.bloggingplatform.controller.dto.PostDto;
import no.itera.bloggingplatform.controller.mapper.PostMapper;
import no.itera.bloggingplatform.model.Post;
import no.itera.bloggingplatform.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(value = "/posts", produces = APPLICATION_JSON_VALUE)
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService){this.postService = postService; }

    @RequestMapping(method = GET)
    @ResponseStatus(HttpStatus.OK)
    public List<PostDto> listAllPosts() {
        return postService.listAllPosts().stream()
                .map(PostMapper::mapToApi)
                .collect(Collectors.toList());
    }

    @RequestMapping(method = GET, value = "/findByAuthor")
    @ResponseStatus(HttpStatus.OK)
    public List<PostDto> getPostsByAuthorId(@RequestParam("author") Long authorId) {
        return postService.findByAuthor(authorId).stream()
                .map(PostMapper::mapToApi)
                .collect(Collectors.toList());
    }

    @RequestMapping(method = GET, value = "/findByCategory")
    @ResponseStatus(HttpStatus.OK)
    public List<PostDto> getPostsByCategory(@RequestParam("category") Long categoryId) {
        return postService.findByCategory(categoryId).stream()
                .map(PostMapper::mapToApi)
                .collect(Collectors.toList());
    }

    @RequestMapping(method = GET, value = "/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public PostDto getPostById(@PathVariable Long postId) {
        return PostMapper.mapToApi(postService.findPostById(postId));
    }

    @RequestMapping(method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto createPost(@RequestBody @Valid PostDto postDto) {
        return PostMapper.mapToApi(postService.createNewPost(PostMapper.mapToDomain(postDto)));
    }

    @RequestMapping(method = PUT)
    @ResponseStatus(HttpStatus.OK)
    public PostDto updatePost(@RequestBody @Valid PostDto postDto) {
        return PostMapper.mapToApi(postService.updatePost(postDto.getId(), PostMapper.mapToDomain(postDto)));
    }

    @RequestMapping(method = DELETE, value = "/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePostById(@PathVariable Long postId) {
        postService.deletePost(postId);
    }
}
