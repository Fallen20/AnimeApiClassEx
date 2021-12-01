package com.project.controller;

import com.project.domain.dto.Error;
import com.project.domain.dto.ListResult;
import com.project.domain.model.Author;
import com.project.domain.model.Genres;
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
    public ListResult showGenreJSON(){//devuelve la lista pero en JSON
        return new ListResult(genresRepository.findBy());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getIndividualGenre(@PathVariable UUID id){
        Genres file = genresRepository.findById(id).orElse(null);

        if (file == null) {return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Error.message("No s'ha trobat el genere amd id '"+id+"'"));}
        return ResponseEntity.ok().body(file);
    }


}
