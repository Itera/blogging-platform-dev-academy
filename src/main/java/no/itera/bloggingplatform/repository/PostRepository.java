package no.itera.bloggingplatform.repository;

import no.itera.bloggingplatform.model.Post;

import java.util.List;

public interface PostRepository extends BasicRepository<Post> {

    /**
     * Find all posts that were written by same author
     *
     * @param authorId key of author that represents author in storage
     * @return all posts belonging to same author
     */
    List<Post> findByAuthor(Long authorId);

    /**
     * Find all posts that have at least one of the requested category assigned. One post can have many categories.
     *
     * @param categoryId key of category that represents category in storage
     * @return all posts belonging to same category
     */
    List<Post> findByCategory(Long categoryId);

}
