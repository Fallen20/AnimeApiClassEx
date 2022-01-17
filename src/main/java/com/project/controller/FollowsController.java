package com.project.controller;

import com.project.domain.dto.Error;
import com.project.domain.dto.ListResult;
import com.project.domain.dto.RequestFollow;
import com.project.domain.model.FollowsUsers;
import com.project.domain.model.User;
import com.project.domain.model.projection.ProyectionUserFollows;
import com.project.repository.FollowsRepository;
import com.project.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/follows")//lo del final de la url
public class FollowsController {

    private final FollowsRepository followedRepository;
    private final UserRepository userRepository;

    public FollowsController(FollowsRepository followedRepository, UserRepository userRepository) {
        this.followedRepository = followedRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public ResponseEntity<?> showFollowed_Users(Authentication authentication){
        if( authentication.getName() != null) {
            ProyectionUserFollows user = userRepository.findByUsername(authentication.getName(), ProyectionUserFollows.class);//consigues el user entero con ese nombre
            return ResponseEntity.ok().body(new ListResult(user.getFollows()));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Error.message("No autorizado"));
    }

    @PostMapping("/")
    public ResponseEntity<?> followUser(@RequestBody RequestFollow requestFollow, Authentication authentication) {//requestBody es que quieres que te envie toda la info
        boolean encontrado=false;

        if(authentication.getName()!=null){//si esta autenticado

            if(userRepository.findByUsername(requestFollow.username)==null){//mira si el user que pide seguir existe
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Error.message("El usuario a seguir no existe"));
            }
            else{//existe
                User userToFollow=userRepository.findByUsername(requestFollow.username);
                User authenticatedUser = userRepository.findByUsername(authentication.getName());

                for(FollowsUsers a : followedRepository.findAll()){
                    if(a.users_followed.equals(userToFollow.userid)){encontrado=true;}
                }


                if(!encontrado){//sino se seguia de antes
                    FollowsUsers newFollow=new FollowsUsers();
                    newFollow.actual_user=authenticatedUser.userid;
                    newFollow.users_followed=userToFollow.userid;

                    followedRepository.save(newFollow);

                    return ResponseEntity.ok().body(Error.message("Siguiendo a "+userToFollow.username));
                }
                else{return ResponseEntity.ok().body(Error.message("No se puede volver a seguir al usuario "+userToFollow.username));}

            }
        }
        //no esta autenticado
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Error.message("Esta acción solo puede hacerse registrado"));
    }


    @DeleteMapping("/")
    public ResponseEntity<?> unFollow(@RequestBody RequestFollow requestFollow, Authentication authentication){
        boolean encontrado=false;

        if(authentication.getName()!=null){//si esta autenticado

            if(userRepository.findByUsername(requestFollow.username)==null){//mira si el user que pide seguir existe
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Error.message("El usuario no existe"));
            }
            else{//existe
                //mira si ya lo sigue
                User userToFollow=userRepository.findByUsername(requestFollow.username);
                User authenticatedUser = userRepository.findByUsername(authentication.getName());

                for(FollowsUsers a : followedRepository.findAll()){
                    if(a.users_followed.equals(userToFollow.userid)){encontrado=true;}
                }

                if(encontrado){
                    FollowsUsers newFollow=new FollowsUsers();
                    newFollow.actual_user=authenticatedUser.userid;
                    newFollow.users_followed=userToFollow.userid;

                    followedRepository.delete(newFollow);

                    return ResponseEntity.ok().body(Error.message("Se ha dejado de seguir al usuario "+userToFollow.username));
                }
                else{
                    return ResponseEntity.ok().body(Error.message("No se puede dejar de seguir al usuario "+requestFollow.username+" porque no se seguía de antes"));
                }



            }
        }
        //no esta autenticado
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Error.message("Esta acción solo puede hacerse registrado"));



    }
}
