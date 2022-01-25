package com.project.controller;

import com.project.domain.dto.Error;
import com.project.domain.dto.ListResult;
import com.project.domain.model.Anime;
import com.project.domain.model.User;
import com.project.domain.model.projection.ProjectionAnimeUserComments;
import com.project.domain.model.projection.ProjectionIndividualUserComments;
import com.project.domain.model.projection.ProjectionUserComments;
import com.project.repository.AnimeRepository;
import com.project.repository.CommentRepository;
import com.project.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/")
public class CommentController {
    private final CommentRepository commentRepository;
    private final AnimeRepository animeRepository;
    private final UserRepository userRepository;

    public CommentController(CommentRepository commentRepository, AnimeRepository animeRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.animeRepository = animeRepository;
        this.userRepository = userRepository;
    }

    //GET
    //comentarios de TODOS los usuario
    //users/comments
    @GetMapping("/users/comments/")
    public ResponseEntity<?> showAllUsersComments(){

        return ResponseEntity.ok().body(new ListResult(userRepository.findBy(ProjectionUserComments.class)));
        //findAll te saca la contra tambien
    }


    //comentarios de UN usuario
    //{userid}/comments
    @GetMapping("/{userid}/comments/")
    public ResponseEntity<?> showEspecificUsersComments(@PathVariable UUID userid, Authentication authentication){

        if( authentication.getName() != null) {
            User usuario = userRepository.findById(userid).orElse(null);

            if (usuario != null) {return ResponseEntity.ok().body(userRepository.findByUserid(userid, ProjectionIndividualUserComments.class));}
            else{return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Error.message("No se ha encontrado el usuario con id '"+userid+"'"));}
        }
        else{return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Error.message("Se ha de estar autorizado para hacer esta operación"));}

    }

    //comentarios de un anime
    //{animeid}/comments

    @GetMapping("/comments/{animeid}/")
    public ResponseEntity<?> showOneAnimeComments(@PathVariable UUID animeid){
        Anime anime=animeRepository.findById(animeid).orElse(null);
        if(anime==null){return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Error.message("No se ha encontrado el anime con id '"+animeid+"'"));}
        else{return ResponseEntity.ok().body(new ListResult(animeRepository.findByAnimeid(animeid,ProjectionAnimeUserComments.class)));}

    }


    //comentarios de TODOS los animes
    //animes/comments
    @GetMapping("/comments/animes/")
    public ResponseEntity<?> showAllAnimeComments(){
        return ResponseEntity.ok().body(new ListResult(animeRepository.findBy(ProjectionAnimeUserComments.class)));
    }

    //POST
    //usuario pone un comentrio a un anime
    //{animeid}/comment; autenticado




    //DEL
    //usuario quiere borrar un comentario suyo de un anime
    //{commentid}

    //usuario quiere borrar TODOS sus comentario de un anime
    //{animeid}/comments

    //usuario quiere borrar TODOD sus comentarios
    //comments/

}
