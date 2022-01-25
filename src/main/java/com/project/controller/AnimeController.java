package com.project.controller;

import com.project.domain.dto.Error;
import com.project.domain.dto.ListResult;
import com.project.domain.model.Anime;
import com.project.domain.model.projection.ProjectionAnimesAll;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.project.repository.AnimeRepository;

import java.util.UUID;

@RestController
@RequestMapping("/animes")
public class AnimeController {

    private final AnimeRepository animeRepository;
    public AnimeController(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    @GetMapping("/")
    public ResponseEntity<?> showAnimeJSON(){//devuelve la lista pero en JSON
        return ResponseEntity.ok().body(new ListResult(animeRepository.findBy(ProjectionAnimesAll.class)));
    }//get /anime/ > devuelve la lista de animes

    @GetMapping("/{id}")
    public ResponseEntity<?> getIndividualAnime(@PathVariable UUID id){
        Anime file = animeRepository.findById(id).orElse(null);

        if (file == null) {return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Error.message("No s'ha trobat l'anime amd id '"+id+"'"));}
        return ResponseEntity.ok().body(file);//si sale bien (ok) devuelves body
    }

    @PostMapping("/")
    public ResponseEntity<?> createAnime(@RequestBody Anime anime){//requestBody es que quieres que te envie toda la info

        for(Anime a :animeRepository.findAll()){
            if(anime.name.equals(a.name)){return
                    ResponseEntity.status(HttpStatus.FOUND).body(Error.message("Ya existe un anime con el nombre "+anime.name));}
        }

        animeRepository.save(anime);//guarda la cosa que recibe
        return ResponseEntity.ok().body(anime);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnime(@PathVariable UUID id){
        Anime file = animeRepository.findById(id).orElse(null);
//, Authentication authentication

        if (file == null) {return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Error.message("No s'ha trobat l'anime amd id '"+id+"'"));}
       //si no ha encontrado

        animeRepository.delete(file);//elimina el anime con ese id
        return ResponseEntity.ok().body(Error.message("S''ha eliminat l'anime amb id '"+id+"'"));//si sale bien (ok) devuelves body
    }



}
