package com.project.repository;

import com.project.domain.model.Genres;
import com.project.domain.model.projection.ProjectionGenresLimited;
import com.project.domain.model.projection.ProjectionGenresLimitedId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface GenresRespository extends JpaRepository<Genres, UUID> {
    List<ProjectionGenresLimited> findBy();

    <T> T findByGenreid(UUID genreid, Class<T> type);//le pasas la id y la clase que quieras que lo adapte
    <T> List<T> findBy(Class<T> type);//devuelve una list de la clase que le digas entre ()
    //la clase se pide a la hora de llamar el metodo
    //T es una clase generica, se dapta a todos


    //List<ProjectionGenresLimitedId> findBy();
}
