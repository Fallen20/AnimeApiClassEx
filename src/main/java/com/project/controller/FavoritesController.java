package com.project.controller;

import com.project.domain.dto.ListResult;
import com.project.repository.FavoritesRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/favorites")
public class FavoritesController {
    private final FavoritesRepository favoritesRepository;

    public FavoritesController(FavoritesRepository favoritesRepository) {
        this.favoritesRepository = favoritesRepository;
    }

    @GetMapping("/")
    public ResponseEntity<?> showFavs(){//devuelve la lista pero en JSON
        return ResponseEntity.ok().body(new ListResult(favoritesRepository.findBy()));
    }//get /anime/ > devuelve la lista de autores

}
