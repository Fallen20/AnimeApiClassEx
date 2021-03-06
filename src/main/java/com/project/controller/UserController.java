package com.project.controller;

import com.project.domain.dto.Error;
import com.project.domain.dto.ListResult;
import com.project.domain.model.User;
import com.project.domain.model.projection.ProjectionCreateUser;
import com.project.domain.model.projection.ProyectionUserDetail;
import com.project.repository.FavoritesRepository;
import com.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final FavoritesRepository favoritesRepository;
    @Autowired //si le pones esto no hace falta constructor
    private BCryptPasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, FavoritesRepository favoritesRepository) {
        this.userRepository = userRepository;
        this.favoritesRepository = favoritesRepository;
    }

    @GetMapping("/")
    public ResponseEntity<?> showUsers(){
        return ResponseEntity.ok().body(new ListResult(userRepository.findBy()));
        //ahora devuelve tambien la lista de favs
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> showIndividualUsers(@PathVariable UUID id){
        return ResponseEntity.ok().body(new ListResult(userRepository.findByUserid(id, ProyectionUserDetail.class)));
    }

    @PostMapping("/")
    public ResponseEntity<?> createAUser(@RequestBody User users){//requestBody es que quieres que te envie toda la info

        for(User a :userRepository.findAll()){
            if(users.username.equals(a.username)){return ResponseEntity.status(HttpStatus.FOUND).body(Error.message("Ya existe un usuario con este nombre"));}
        }

        if (userRepository.findByUsername(users.username) == null) {
            User userNuevo = new User();
            userNuevo.username = users.username;
            userNuevo.password = passwordEncoder.encode(users.password);
            userRepository.save(userNuevo);

            return ResponseEntity.ok().body(userRepository.findByUserid(userNuevo.userid, ProjectionCreateUser.class));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR");

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUser(@PathVariable UUID id){
        User file = userRepository.findById(id).orElse(null);

        if (file == null) {return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Error.message("No s'ha trobat l'usuari amd id '"+id+"'"));}
        //si no ha encontrado

        userRepository.delete(file);//elimina el anime con ese id
        return ResponseEntity.ok().body(Error.message("S'ha eliminat l'usuari amb id '"+id+"'"));//si sale bien (ok) devuelves body
    }

    @DeleteMapping("/")
    public void deleteAllUsers(){
        userRepository.deleteAll();
    }



    //a??adir favoritos
    //el endpoint es importante
    //se ha de pasar el id y el id del anime a hacer fav

    /*@PostMapping("/{id}/favorites")
    public ResponseEntity<?> addFav(@RequestBody Favourite favourite, Authentication authentication){
        //pasar un obj fav que contenga el user y el anime id
        //tenemos que crear un repositorio +tabla para guardarlo
        //authentication.getName();//ahora tienes los datos del usuario logeado


        //como puede pasar el id del user que quieras, tenemos que pedir la autenticacion
        //autenticacion==id del json

        if(userRepository.findByUsername(authentication.getName()).userid.equals(favourite.userid)){


            favoritesRepository.save(favourite);//esto solo lo guarda en el repositorio, no en el user
            return ResponseEntity.ok().body("El anime se ha guardado en los favoritos del user");
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Error.message("El usuario logeado no concuerda con el usuario dado"));
        }



    }*/
}
