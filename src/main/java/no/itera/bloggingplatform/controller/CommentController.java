package no.itera.bloggingplatform.controller;

import no.itera.bloggingplatform.controller.dto.CommentDto;
import no.itera.bloggingplatform.controller.mapper.CategoryMapper;
import no.itera.bloggingplatform.controller.mapper.CommentMapper;
import no.itera.bloggingplatform.model.Comment;
import no.itera.bloggingplatform.service.CategoryService;
import no.itera.bloggingplatform.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(value = "/comments", produces = APPLICATION_JSON_VALUE)
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService){this.commentService = commentService; }

    @RequestMapping(method = GET, value = "/post/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDto> fetchCommentsForPost(@PathVariable Long postId) {
        return commentService.findCommentsForPost(postId).stream()
                .map(CommentMapper::mapToApi)
                .collect(Collectors.toList());
    }

    @RequestMapping(method = GET, value = "/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public CommentDto getCommentById(@PathVariable Long commentId) {
        return CommentMapper.mapToApi(commentService.findCommentById(commentId));
    }

    @RequestMapping(method = POST, value = "/post/{postId}")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto createCommentForPost(@RequestBody @Valid CommentDto commentDto, @PathVariable Long postId) {
        return CommentMapper.mapToApi(commentService.createCommentForPostId(postId, CommentMapper.mapToDomain(commentDto)));
    }

    @RequestMapping(method = DELETE, value = "/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCommentById(@PathVariable Long commentId) {
        commentService.deleteCommentById(commentId);
    }
}
