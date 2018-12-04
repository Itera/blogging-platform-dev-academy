package no.itera.bloggingplatform.controller.mapper;

import no.itera.bloggingplatform.controller.dto.ImmutableCommentDto;
import no.itera.bloggingplatform.controller.dto.ImmutablePostDto;
import no.itera.bloggingplatform.controller.dto.PostDto;
import no.itera.bloggingplatform.model.Category;
import no.itera.bloggingplatform.model.Post;

import javax.validation.Valid;
import java.util.stream.Collectors;

public class PostMapper {

    private PostMapper(){

    }

    public static PostDto mapToApi(Post post){
        return ImmutablePostDto.builder()
                .id(post.getKey())
                .title(post.getTitle())
                .date(post.getDate())
                .perex(post.getPerex())
                .content(post.getContent())
                .author(AuthorMapper.mapToApi(post.getAuthor()))
                .categories(post.getCategories().stream().map(CategoryMapper::mapToApi).collect(Collectors.toList()))
                .build();
    }

    public static Post mapToDomain(@Valid PostDto postDto){
        Post post = new Post();
        post.setKey(postDto.getId());
        post.setTitle(postDto.getTitle());
        post.setDate(postDto.getDate());
        post.setPerex(postDto.getPerex());
        post.setContent(postDto.getContent());
        post.setAuthor(AuthorMapper.mapToDomain(postDto.getAuthor()));
        post.setCategories(postDto.getCategories().stream().map(CategoryMapper::mapToDomain).collect(Collectors.toList()));
        return post;
    }
}
