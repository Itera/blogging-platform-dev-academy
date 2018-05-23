package no.itera.bloggingplatform.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Post implements Persistable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Date date;
    private String perex;
    private String content;
    @OneToOne
    @JoinColumn
    private Author author;
    @OneToMany
    @JoinColumn
    private List<Category> categories;

    @Override
    public Long getKey() {
        return id;
    }

    @Override
    public void setKey(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPerex() {
        return perex;
    }

    public void setPerex(String perex) {
        this.perex = perex;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) &&
                Objects.equals(title, post.title) &&
                Objects.equals(date, post.date) &&
                Objects.equals(perex, post.perex) &&
                Objects.equals(content, post.content) &&
                Objects.equals(author, post.author) &&
                Objects.equals(categories, post.categories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, date, perex, content, author, categories);
    }
}
