package com.dbs.lab4.data;

import com.dbs.lab4.entity.Genre;

public class GenreNodePath {
    private Genre genre1;

    private Genre genre2;
    private Integer pathLength;

    public Genre getGenre1() {
        return genre1;
    }

    public void setGenre1(Genre genre1) {
        this.genre1 = genre1;
    }

    public Genre getGenre2() {
        return genre2;
    }

    public void setGenre2(Genre genre2) {
        this.genre2 = genre2;
    }

    public Integer getPathLength() {
        return pathLength;
    }

    public void setPathLength(Integer pathLength) {
        this.pathLength = pathLength;
    }
}
