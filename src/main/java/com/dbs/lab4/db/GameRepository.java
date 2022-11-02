package com.dbs.lab4.db;


import com.dbs.lab4.entity.Game;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends Neo4jRepository<Game, Long> {
    @Query("match(g1:Game)-[:refers_to]->(gt:Genre)<-[:refers_to]-(g2:Game) return g2")
    List<Game> match();

    @Query("match(g1:Game {name : :#{#game1.name}}), (g2:Game {name: :#{#game2.name}}), path = shortestPath((g1)-[:refers_to*]-(g2)) return length(path)")
    Integer shortestPath(@Param("game1") Game game1, @Param("game2") Game game2);
}
