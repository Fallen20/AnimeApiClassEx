package com.project.controller;

import com.project.domain.dto.Error;
import com.project.domain.dto.ResponseUser;
import com.project.domain.model.Users;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.project.repository.UserRepository;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public ResponseUser showUsers(){
        return new ResponseUser(userRepository.findAll());
    }//devuelve mal, no debe devolver contraseña

    @PostMapping("/")
    public ResponseEntity<?> createAnime(@RequestBody Users users){//requestBody es que quieres que te envie toda la info

        for(Users a :userRepository.findAll()){
            if(users.username.equals(a.username)){return ResponseEntity.status(HttpStatus.FOUND).body(Error.message("Ya existe un usuario con este nombre"));}
        }

        userRepository.save(users);//guarda la cosa que recibe
        return ResponseEntity.ok().body(users);//devuelve mal, no debe devolver id
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> elimiarUser(@PathVariable UUID id){
        Users file = userRepository.findById(id).orElse(null);

        if (file == null) {return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Error.message("No s'ha trobat l'anime amd id '"+id+"'"));}
        //si no ha encontrado

        userRepository.delete(file);//elimina el anime con ese id
        return ResponseEntity.ok().body("S'ha eliminat l'anime amb id '"+id+"'");//si sale bien (ok) devuelves body
    }

    @DeleteMapping("/")
    public void deleteAllAnime(){
        userRepository.deleteAll();
    }
}
