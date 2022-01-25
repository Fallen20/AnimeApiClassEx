package com.project.controller;

import com.project.domain.dto.Error;
import com.project.domain.dto.ListResult;
import com.project.domain.dto.RequestComment;
import com.project.domain.model.*;
import com.project.domain.model.projection.ProjectionAnimeUserComments;
import com.project.domain.model.projection.ProjectionIndividualUserComments;
import com.project.domain.model.projection.ProjectionUserComments;
import com.project.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/comments/")
public class CommentController {
    private final CommentRepository commentRepository;
    private final AnimeRepository animeRepository;
    private final UserRepository userRepository;
    private final AnimeCommentRepository animeCommentRepository;
    private final UserCommentRepository userCommentRepository;

    public CommentController(CommentRepository commentRepository, AnimeRepository animeRepository, UserRepository userRepository, AnimeCommentRepository animeCommentRepository, UserCommentRepository userCommentRepository) {
        this.commentRepository = commentRepository;
        this.animeRepository = animeRepository;
        this.userRepository = userRepository;
        this.animeCommentRepository = animeCommentRepository;
        this.userCommentRepository = userCommentRepository;
    }

    //GET
    //comentarios de TODOS los usuario
    //users/comments
    @GetMapping("/users/")
    public ResponseEntity<?> showAllUsersComments(){

        return ResponseEntity.ok().body(new ListResult(userRepository.findBy(ProjectionUserComments.class)));
        //findAll te saca la contra tambien
    }


    //comentarios de UN usuario
    //{userid}/comments
    @GetMapping("/users/{userid}")
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

    @GetMapping("/anime/{animeid}/")
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
    @PostMapping("/{animeid}/comment/")
    public ResponseEntity<?> addComment(@PathVariable UUID animeid, @RequestBody RequestComment requestComment, Authentication authentication){
        //miramos si esta autenticado
        if(authentication.getName()!=null){
            //miramos si existe el anime

            boolean existeAnime=false;
            for(Anime a : animeRepository.findAll()){
                if (a.animeid.equals(animeid)){existeAnime=true;}
            }

            if(existeAnime==true){

                //nuevo comentario
                Comment comment=new Comment();
                comment.comentario= requestComment.comentario;

                commentRepository.save(comment);//sino lo guardas antes de hacer lo demas no genera el id

                //recuperamos user y anime
                User authenticatedUser = userRepository.findByUsername(authentication.getName());
                Anime anime=animeRepository.findByAnimeid(animeid);


                //el comentario y el usuario
                CommentUser commentUser=new CommentUser();
                commentUser.commentid=comment.commentid;
                commentUser.userid=authenticatedUser.userid;


                //el comentario y el anime
                CommentAnime commentAnime=new CommentAnime();
                commentAnime.animeid=anime.animeid;
                commentAnime.commentid=comment.commentid;

                //guardamos
                //guardas el comentario
                userCommentRepository.save(commentUser);
                animeCommentRepository.save(commentAnime);

                return ResponseEntity.status(HttpStatus.OK).body(Error.message("Creado con exito"));
            }
            else{return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Error.message("El anime con id "+animeid+" no existe"));}


        }
        else{return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Error.message("Debes estar logeado para hacer esta accion"));}
    }


    //DEL
    //usuario quiere borrar un comentario suyo de un anime
    //{commentid}
    @DeleteMapping("/{commentid}/comment/")
    public ResponseEntity<?> deleteOneComment(@PathVariable UUID commentid, Authentication authentication){
        //mira esta logeado
        if(authentication.getName()!=null){
            boolean existeComment=false;
            //busca si existe ese comentario
            for(Comment a: commentRepository.findAll()){
                if(a.commentid.equals(commentid)){existeComment=true;}
            }

            if(existeComment){
                Comment commentEncontrado=commentRepository.findByCommentid(commentid);
                User authenticatedUser = userRepository.findByUsername(authentication.getName());
                Anime anime=null;

                for(Anime a :animeRepository.findAll()){
                    for (Comment comment : a.comments) {
                        if(comment.commentid.equals(commentid)){anime=a;}
                    }

                }

                CommentUser commentUser=new CommentUser();
                commentUser.commentid=commentEncontrado.commentid;
                commentUser.userid=authenticatedUser.userid;


                //el comentario y el anime
                CommentAnime commentAnime=new CommentAnime();
                commentAnime.animeid=anime.animeid;
                commentAnime.commentid=commentEncontrado.commentid;


                commentRepository.delete(commentEncontrado);
                animeCommentRepository.delete(commentAnime);
                userCommentRepository.delete(commentUser);

                return ResponseEntity.status(HttpStatus.OK).body(Error.message("Borrado con exito"));

            }
            else{return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Error.message("El comentario con id "+commentid+" no existe"));}


        }
        else{return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Error.message("Debes estar logeado para hacer esta accion"));}


    }

    //usuario quiere borrar TODOS sus comentario de un anime
    //{animeid}/comments

    //usuario quiere borrar TODOD sus comentarios
    //comments/

}
