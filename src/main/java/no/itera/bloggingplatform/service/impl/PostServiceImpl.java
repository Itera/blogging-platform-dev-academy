package no.itera.bloggingplatform.service.impl;

import no.itera.bloggingplatform.model.Post;
import no.itera.bloggingplatform.repository.PostRepository;
import no.itera.bloggingplatform.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> listAllPosts() {
       return postRepository.readAll();
    }

    @Override
    public Post createNewPost(Post post) {
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
        return postRepository.update(postId, toUpdate);
    }

    @Override
    public Post deletePost(Long postId) {
        return postRepository.delete(postId);
    }
}
