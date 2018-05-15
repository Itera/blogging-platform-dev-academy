package no.itera.bloggingplatform.controller;

import static java.util.Collections.singletonList;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Date;
import java.util.List;

import no.itera.bloggingplatform.controller.dto.CommentDto;
import no.itera.bloggingplatform.controller.dto.ImmutableCommentDto;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/comment", produces = APPLICATION_JSON_VALUE)
public class CommentController {

  @RequestMapping(value = "post/{postId}", method = GET)
  @ResponseStatus(HttpStatus.OK)
  public List<CommentDto> fetchCommentsForPost(@PathVariable Long postId) {
    return singletonList(
        ImmutableCommentDto.of(
            123L,
            new Date(),
            "Joey",
            "Content of mock comment, fetched for postId:" + postId,
            12L
        )
    );
  }

  @RequestMapping(value = "post/{postId}", method = POST)
  @ResponseStatus(HttpStatus.CREATED)
  public CommentDto createCommentForPost(
      @PathVariable Long postId,
      @RequestBody CommentDto comment
  ) {
    return ImmutableCommentDto.copyOf(comment).withPostId(postId).withId(124L);
  }

  @RequestMapping(value = "/{commentId}", method = GET)
  @ResponseStatus(HttpStatus.OK)
  public CommentDto fetchCommentById(@PathVariable Long commentId) {
    return ImmutableCommentDto.of(
        123L,
        new Date(),
        "Joey",
        "Content of mock comment",
        12L
    );
  }

  @RequestMapping(value = "/{commentId}", method = DELETE)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteCommentById(@PathVariable Long commentId) {
  }
}
