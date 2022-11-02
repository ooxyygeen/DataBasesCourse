package com.dbs.lab4.controller;

import com.dbs.lab4.data.GameNodePath;
import com.dbs.lab4.db.GameRepository;
import com.dbs.lab4.entity.Game;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    public static final List<Game> games = List.of(
            new Game("CS:GO", "2012"),
            new Game("Minecraft", "2011"),
            new Game("Terraria", "2011"),
            new Game("The Witcher 3", "2015"),
            new Game("Rust", "2013"),
            new Game("PUBG", "2017"),
            new Game("BioShock", "2007")
    );

    private GameRepository gameRepository;

    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @GetMapping("/match")
    public List<Game> match(){
        return gameRepository.match();
    }

    @GetMapping("/pathLength")
    public List<GameNodePath> pathLength() {
        List<GameNodePath> gameNodePathList = new ArrayList<>();
        for(Game game1 : games) {
            for(Game game2: games) {
                if(!game1.equals(game2)) {
                    Integer pathLength = gameRepository.shortestPath(game1, game2);
                    if(pathLength == null) {
                        continue;
                    }
                    GameNodePath gameNodePath = new GameNodePath();
                    gameNodePath.setPathLength(pathLength);
                    gameNodePath.setGame1(game1);
                    gameNodePath.setGame2(game2);
                    gameNodePathList.add(gameNodePath);
                }
            }
        }
        gameNodePathList.sort(new Comparator<GameNodePath>() {
            @Override
            public int compare(GameNodePath o1, GameNodePath o2) {
                if(o1.getPathLength() > o2.getPathLength()) {
                    return 1;
                } else if(o1.getPathLength() < o2.getPathLength()) {
                    return  -1;
                } else {
                    return 0;
                }
            }
        });
        return gameNodePathList;
    }
}
