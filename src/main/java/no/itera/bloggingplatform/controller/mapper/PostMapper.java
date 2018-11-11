package no.itera.bloggingplatform.controller.mapper;

import java.util.List;
import java.util.stream.Collectors;

import no.itera.bloggingplatform.controller.dto.ImmutablePostDto;
import no.itera.bloggingplatform.controller.dto.PostDto;
import no.itera.bloggingplatform.model.Post;

public final class PostMapper {

  private PostMapper() {
  }

  public static PostDto mapToApi(Post post) {
    return ImmutablePostDto.builder()
        .id(post.getKey())
        .title(post.getTitle())
        .perex(post.getPerex())
        .content(post.getContent())
        .author(AuthorMapper.mapToApi(post.getAuthor()))
        .date(post.getDate())
        .categories(CategoryMapper.mapListToApi(post.getCategories()))
        .build();
  }

  public static List<PostDto> mapListToApi(List<Post> post) {
    return post.stream()
        .map(PostMapper::mapToApi)
        .collect(Collectors.toList());
  }

  public static Post mapToDomain(PostDto postDto) {
    Post post = new Post();
    post.setKey(postDto.getId());
    post.setTitle(postDto.getTitle());
    post.setPerex(postDto.getPerex());
    post.setContent(postDto.getContent());
    post.setAuthor(AuthorMapper.mapToDomain(postDto.getAuthor()));
    post.setDate(postDto.getDate());
    post.setCategories(CategoryMapper.mapListToDomain(postDto.getCategories()));
    return post;
  }
}
