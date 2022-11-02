package com.dbs.lab4.db;


import com.dbs.lab4.entity.Game;
import com.dbs.lab4.entity.Genre;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends Neo4jRepository<Genre, Long> {

    @Query("match(g:Game{name: :#{#game.name}}),(gt:Genre{name: :#{#genre.name}}) create(g)-[r:refers_to]->(gt)")
    void connect(@Param("game") Game game, @Param("genre") Genre genre);

    @Query("match(gt1:Genre)<-[:refers_to]-(g:Game)-[:refers_to]->(gt2:Genre) return gt2")
    List<Genre> match();

    @Query("match(gt1:Genre {name : :#{#genre1.name}}), (gt2:Genre {name: :#{#genre2.name}}), path = shortestPath((gt1)-[:refers_to*]-(gt2)) return length(path)")
    Integer shortestPath(@Param("genre1") Genre genre1, @Param("genre2") Genre genre2);

}