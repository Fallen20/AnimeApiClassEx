package com.project.repository;

import com.project.domain.model.Genres;
import com.project.domain.model.projection.ProjectionGenresLimited;
import com.project.domain.model.projection.ProjectionGenresLimitedId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface GenresRespository extends JpaRepository<Genres, UUID> {
    List<ProjectionGenresLimited> findBy();

    <T> T findByGenreid(UUID genreid, Class<T> type);
    <T> List<T> findBy(Class<T> type);


    //List<ProjectionGenresLimitedId> findBy();
    //lo hace bien pero no se devolverlo
}
