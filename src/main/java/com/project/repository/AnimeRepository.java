package com.project.repository;

import com.project.domain.model.Anime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AnimeRepository extends JpaRepository<Anime, UUID> {
    //List<ProjectionAnimes> findBy();

    <T> List<T> findBy(Class<T> type);
    <T> List<T> findByAnimeid(UUID id,Class<T> type);

    //<T> List<T> findAll(Class<T> type);
}
