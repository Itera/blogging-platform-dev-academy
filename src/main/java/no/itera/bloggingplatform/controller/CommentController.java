package no.itera.bloggingplatform.controller;

import static no.itera.bloggingplatform.controller.mapper.CommentMapper.mapToApi;
import static no.itera.bloggingplatform.controller.mapper.CommentMapper.mapToDomain;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;
import java.util.stream.Collectors;

import no.itera.bloggingplatform.controller.dto.CommentDto;
import no.itera.bloggingplatform.controller.mapper.CommentMapper;
import no.itera.bloggingplatform.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/comments", produces = APPLICATION_JSON_VALUE)
public class CommentController {

  private CommentService commentService;

  @Autowired
  public CommentController(CommentService commentService) {
    this.commentService = commentService;
  }

  @RequestMapping(value = "post/{postId}", method = GET)
  @ResponseStatus(HttpStatus.OK)
  public List<CommentDto> fetchCommentsForPost(@PathVariable Long postId) {
    return commentService.findCommentsForPost(postId).stream()
        .map(CommentMapper::mapToApi)
        .collect(Collectors.toList());
  }

  @RequestMapping(value = "post/{postId}", method = POST)
  @ResponseStatus(HttpStatus.CREATED)
  public CommentDto createCommentForPost(
      @PathVariable Long postId,
      @RequestBody CommentDto comment
  ) {
    return mapToApi(
        commentService.createCommentForPostId(postId, mapToDomain(comment))
    );
  }

  @RequestMapping(value = "/{commentId}", method = GET)
  @ResponseStatus(HttpStatus.OK)
  public CommentDto fetchCommentById(@PathVariable Long commentId) {
    return mapToApi(
        commentService.findCommentById(commentId)
    );
  }

  @RequestMapping(value = "/{commentId}", method = DELETE)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteCommentById(@PathVariable Long commentId) {
    commentService.deleteCommentById(commentId);
  }
}
