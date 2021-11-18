package controller;

import domain.dto.Error;
import domain.dto.ResponseAnime;
import domain.model.Anime;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import repository.AnimeRepository;

import java.util.UUID;

@RestController
@RequestMapping("/animes")
public class AnimeController {

    private final AnimeRepository animeRepository;
    public AnimeController(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    @GetMapping("/")
    public ResponseAnime showAnimeJSON(){//devuelve la lista pero en JSON
        return new ResponseAnime(animeRepository.findAll());
    }//get /anime/ > devuelve la lista de animes

    @GetMapping("/{id}")
    public ResponseEntity<?> getIndividualAnime(@PathVariable UUID id){
        Anime file = animeRepository.findById(id).orElse(null);

        if (file == null) {return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Error.message("No s'ha trobat l'anime amd id '"+id+"'"));}
        return ResponseEntity.ok().body(file);//si sale bien (ok) devuelves body
    }//preguntar

    @PostMapping("/")
    public ResponseEntity<?> createAnime(@RequestBody Anime anime){//requestBody es que quieres que te envie toda la info

        for(Anime a :animeRepository.findAll()){
            if(anime.name.equals(animeRepository)){return
                    ResponseEntity.status(HttpStatus.NO_CONTENT).body(Error.message("Ya existe un anime con este nombre"));}
        }

        animeRepository.save(anime);//guarda la cosa que recibe
        return ResponseEntity.ok().body(anime);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnime(@PathVariable UUID id){
        Anime file = animeRepository.findById(id).orElse(null);

        if (file == null) {return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Error.message("No s'ha trobat l'anime amd id '"+id+"'"));}
       //si no ha encontrado

        animeRepository.delete(file);//elimina el anime con ese id
        return ResponseEntity.ok().body("S''ha eliminat l'anime amb id '"+id+"'");//si sale bien (ok) devuelves body
    }

}
