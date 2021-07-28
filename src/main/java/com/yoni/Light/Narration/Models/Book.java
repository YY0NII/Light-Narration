package com.yoni.Light.Narration.Models;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500)
    private String title;

    @Column(length = 500)
    private String authors;

    @Column(length = 500)
    private String coverImageLink;

    @Column(length = 10000)
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getCoverImageLink() {
        return coverImageLink;
    }

    public void setCoverImageLink(String coverImageLink) {
        this.coverImageLink = coverImageLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                ", coverImageLink='" + coverImageLink + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
