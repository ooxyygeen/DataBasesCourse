package com.dbs.lab4.controller;

import com.dbs.lab4.data.GenreNodePath;
import com.dbs.lab4.db.GameRepository;
import com.dbs.lab4.db.GenreRepository;
import com.dbs.lab4.entity.Genre;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {
    public static final List<Genre> genres = List.of(
            new Genre("Adventure"),
            new Genre("RPG"),
            new Genre("Sandbox"),
            new Genre("Online"),
            new Genre("Shooter"),
            new Genre("Battle royal")
    );

    private GenreRepository genreRepository;
    private GameRepository gameRepository;

    public GenreController(GenreRepository genreRepository, GameRepository gameRepository) {
        this.genreRepository = genreRepository;
        this.gameRepository = gameRepository;
    }

    @PostMapping("/connect")
    public void connect(){
        gameRepository.saveAll(GameController.games);
        genreRepository.saveAll(genres);

        genreRepository.connect(GameController.games.get(0),genres.get(3));
        genreRepository.connect(GameController.games.get(0),genres.get(4));
        genreRepository.connect(GameController.games.get(1),genres.get(2));
        genreRepository.connect(GameController.games.get(1),genres.get(3));
        genreRepository.connect(GameController.games.get(2),genres.get(0));
        genreRepository.connect(GameController.games.get(2),genres.get(1));
        genreRepository.connect(GameController.games.get(2),genres.get(2));
        genreRepository.connect(GameController.games.get(2),genres.get(3));
        genreRepository.connect(GameController.games.get(3),genres.get(0));
        genreRepository.connect(GameController.games.get(3),genres.get(1));
        genreRepository.connect(GameController.games.get(4),genres.get(2));
        genreRepository.connect(GameController.games.get(4),genres.get(3));
        genreRepository.connect(GameController.games.get(4),genres.get(4));
        genreRepository.connect(GameController.games.get(5),genres.get(3));
        genreRepository.connect(GameController.games.get(5),genres.get(4));
        genreRepository.connect(GameController.games.get(5),genres.get(5));
        genreRepository.connect(GameController.games.get(6),genres.get(1));
        genreRepository.connect(GameController.games.get(6),genres.get(4));
    }

    @GetMapping("/match")
    public List<Genre> match(){
        return genreRepository.match();
    }

    @GetMapping("/pathLength")
    public List<GenreNodePath> pathLength() {
        List<GenreNodePath> genreNodePathList = new ArrayList<>();
        for(Genre genre1 : genres) {
            for(Genre genre2: genres) {
                if(!genre1.equals(genre2)) {
                    Integer pathLength = genreRepository.shortestPath(genre1, genre2);
                    if(pathLength == null) {
                        continue;
                    }
                    GenreNodePath genreNodePath = new GenreNodePath();
                    genreNodePath.setPathLength(pathLength);
                    genreNodePath.setGenre1(genre1);
                    genreNodePath.setGenre2(genre2);
                    genreNodePathList.add(genreNodePath);
                }
            }
        }
        genreNodePathList.sort(new Comparator<GenreNodePath>() {
            @Override
            public int compare(GenreNodePath o1, GenreNodePath o2) {
                if(o1.getPathLength() > o2.getPathLength()) {
                    return 1;
                } else if(o1.getPathLength() < o2.getPathLength()) {
                    return  -1;
                } else {
                    return 0;
                }
            }
        });
        return genreNodePathList;
    }
}
