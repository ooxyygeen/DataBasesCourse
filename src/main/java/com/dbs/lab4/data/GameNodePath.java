package com.dbs.lab4.data;

import com.dbs.lab4.entity.Game;

public class GameNodePath {
    private Game game1;
    private Game game2;
    private Integer pathLength;

    public Game getGame1() {
        return game1;
    }

    public void setGame1(Game game1) {
        this.game1 = game1;
    }

    public Game getGame2() {
        return game2;
    }

    public void setGame2(Game user2) {
        this.game2 = game2;
    }

    public Integer getPathLength() {
        return pathLength;
    }

    public void setPathLength(Integer pathLength) {
        this.pathLength = pathLength;
    }
}

