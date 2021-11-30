package com.project.controller;

import com.project.domain.dto.Error;
import com.project.domain.dto.ListResult;
import com.project.domain.model.Author;
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
    public ListResult showAuthorJSON(){//devuelve la lista pero en JSON
        return new ListResult(authorRepository.findAll());
    }//get /anime/ > devuelve la lista de autores

    @GetMapping("/{id}")
    public ResponseEntity<?> getIndividualAnime(@PathVariable UUID id){
        Author file = authorRepository.findById(id).orElse(null);

        if (file == null) {return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Error.message("No s'ha trobat l'autor amd id '"+id+"'"));}
        return ResponseEntity.ok().body(file);//si sale bien (ok) devuelves body
    }


}
