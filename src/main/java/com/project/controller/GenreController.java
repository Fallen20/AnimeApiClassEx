package com.project.controller;

import com.project.domain.dto.ListResult;
import com.project.repository.GenresRespository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
public class GenreController {

    private final GenresRespository genresRepository;
    public GenreController(GenresRespository genresRepository) {this.genresRepository = genresRepository;}

    @GetMapping("/")
    public ListResult showAnimeJSON(){//devuelve la lista pero en JSON
        return new ListResult(genresRepository.findAll());
    }//get /anime/ > devuelve la lista de generos




}
