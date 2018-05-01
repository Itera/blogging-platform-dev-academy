package no.itera.bloggingplatform.service;

import no.itera.bloggingplatform.model.Post;

import java.util.List;

public interface PostService {
    List<Post> listAllPosts();

    Post createNewPost(Post post);

    List<Post> findByAuthor(Long authorId);

    List<Post> findByCategory(Long categoryId);

    Post findPostById(Long postId);

    Post updatePost(Long postId, Post toUpdate);

    Post deletePost(Long postId);
}
