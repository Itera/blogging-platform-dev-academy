package no.itera.bloggingplatform.model;

import java.util.Date;
import java.util.Objects;

public class Comment implements Persistable {

    private Long id;
    private Date date;
    private String authorName;
    private String content;
    private Long postId;

    @Override
    public Long getKey() {
        return id;
    }

    @Override
    public void setKey(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) &&
                Objects.equals(date, comment.date) &&
                Objects.equals(authorName, comment.authorName) &&
                Objects.equals(content, comment.content) &&
                Objects.equals(postId, comment.postId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, authorName, content, postId);
    }
}
