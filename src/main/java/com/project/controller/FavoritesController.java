package com.project.controller;

import com.project.domain.dto.Error;
import com.project.domain.dto.ListResult;
import com.project.domain.dto.Message;
import com.project.domain.dto.RequestFavorite;
import com.project.domain.model.Anime;
import com.project.domain.model.Favourite;
import com.project.domain.model.User;
import com.project.domain.model.projection.ProjectionGetUserFavs;
import com.project.repository.AnimeRepository;
import com.project.repository.FavoritesRepository;
import com.project.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users/favorites")
public class FavoritesController {
    private final FavoritesRepository favoritesRepository;
    private final AnimeRepository animeRepository;
    private final UserRepository userRepository;

    public FavoritesController(FavoritesRepository favoritesRepository, AnimeRepository animeRepository, UserRepository userRepository) {
        this.favoritesRepository = favoritesRepository;
        this.animeRepository = animeRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public ResponseEntity<?> showFavs(Authentication authentication){
//obtener el id del logeado
        if( authentication.getName() != null) {
            ProjectionGetUserFavs user = userRepository.findByUsername(authentication.getName(), ProjectionGetUserFavs.class);//consigues el user entero con ese nombre
            return ResponseEntity.ok().body(new ListResult(user.getFavourites()));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No autorizado");
    }

    @PostMapping("/")
    public ResponseEntity<?> createFav(@RequestBody RequestFavorite requestFavorite, Authentication authentication){//requestBody es que quieres que te envie toda la info

        boolean encontrado=false;

        for(Anime a : animeRepository.findAll()){//busca si existe
            if(a.animeid.equals(requestFavorite.animeid)){
                encontrado=true;//existe
            }
        }

        if(!encontrado){return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Error.message("El anime con el id "+requestFavorite.animeid+" no existe"));}
        else{
            if(authentication.getName()!=null){
                Favourite fav=new Favourite();
                User authenticatedUser = userRepository.findByUsername(authentication.getName());


                fav.userid=authenticatedUser.userid;
                fav.animeid=requestFavorite.animeid;

                favoritesRepository.save(fav);



                return ResponseEntity.ok().body(fav);
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Error.message("No autorizado"));
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFav(@PathVariable UUID id, Authentication authentication){//pathVariable es lo de {id}

        if(authentication.getName()!=null){
            //mira si el anime existe
            boolean encontrado=false;

            for(Anime a : animeRepository.findAll()){//busca si existe
                if(a.animeid.equals(id)){
                    encontrado=true;//existe
                }
            }

            //si existe
            if(encontrado){
                User authenticatedUser = userRepository.findByUsername(authentication.getName());

                Favourite fav=new Favourite();
                fav.animeid=id;
                fav.userid=authenticatedUser.userid;

                //lo borras de la base de fav
                favoritesRepository.delete(fav);

                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Message.message("Se ha eliminado de los favoritos del usuario "+authenticatedUser.username+" el anime con id "+id));


            }
            //no existe
            else{return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Error.message("Este anime no existe"));}


        }
        else{return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Error.message("No autorizado"));}
    }

}
