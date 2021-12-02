package com.project.controller;

import com.project.domain.dto.Error;
import com.project.domain.dto.ListResult;
import com.project.domain.model.Genres;
import com.project.domain.model.projection.ProjectionGenresLimited;
import com.project.domain.model.projection.ProjectionGenresLimitedId;
import com.project.repository.GenresRespository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/genres")
public class GenreController {

    private final GenresRespository genresRepository;
    public GenreController(GenresRespository genresRepository) {this.genresRepository = genresRepository;}

    @GetMapping("/")
    public ResponseEntity<?> showGenreJSON(){//devuelve la lista pero en JSON
        return ResponseEntity.ok().body(new ListResult(genresRepository.findBy(ProjectionGenresLimited.class)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getIndividualGenre(@PathVariable UUID id){
        ProjectionGenresLimitedId genre = genresRepository.findByGenreid(id, ProjectionGenresLimitedId.class);

        if (genre == null) {return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Error.message("No s'ha trobat el genere amd id '"+id+"'"));}
        return ResponseEntity.ok().body(genre);
    }


}
