package com.project.controller;

import com.project.domain.dto.ListResult;
import com.project.repository.AuthorRespository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorRespository authorRepository;
    public AuthorController(AuthorRespository authorRepository) {this.authorRepository = authorRepository;}

    @GetMapping("/")
    public ListResult showAnimeJSON(){//devuelve la lista pero en JSON
        return new ListResult(authorRepository.findAll());
    }//get /anime/ > devuelve la lista de animes




}
