package com.project.repository;

import com.project.domain.model.Favourite;
import com.project.domain.model.projection.ProjectionAuthorNoGenres;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

//consultas
public interface FavoritesRepository extends JpaRepository<Favourite, UUID> {
    List<Favourite> findBy();

    <T> T findByUserid(UUID userid, Class<T> type);

}
