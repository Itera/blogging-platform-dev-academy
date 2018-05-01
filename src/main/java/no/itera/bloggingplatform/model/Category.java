package no.itera.bloggingplatform.model;

import java.util.Objects;

public class Category implements Persistable {

    private Long id;
    private String name;

    @Override
    public Long getKey() {
        return id;
    }

    @Override
    public void setKey(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id) &&
                Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
