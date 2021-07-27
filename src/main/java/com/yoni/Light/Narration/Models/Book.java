package com.yoni.Light.Narration.Models;

import java.net.URL;
import java.util.List;

public class Book {
    private Long id;
    private String title;
    private Long authorId; //TODO: this will be an issue for light novels that have multiple authors
    private URL coverImageLink;
    private List<String> genres;

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

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public URL getCoverImageLink() {
        return coverImageLink;
    }

    public void setCoverImageLink(URL coverImageLink) {
        this.coverImageLink = coverImageLink;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }
}
