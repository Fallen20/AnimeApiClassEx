package com.project.controller;

import com.project.domain.dto.Error;
import com.project.domain.dto.ListResult;
import com.project.domain.dto.UserResult;
import com.project.domain.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.project.repository.UserRepository;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public ListResult showUsers(){
        return new ListResult(userRepository.findBy());
    }//devuelve mal, no debe devolver contraseña

    @PostMapping("/")
    public ResponseEntity<?> createAUser(@RequestBody Users users){//requestBody es que quieres que te envie toda la info

        for(Users a :userRepository.findAll()){
            if(users.username.equals(a.username)){return ResponseEntity.status(HttpStatus.FOUND).body(Error.message("Ya existe un usuario con este nombre"));}
        }

        if (userRepository.findByUsername(users.username) == null) {
            Users userNuevo = new Users();
            userNuevo.username = users.username;
            userNuevo.password = passwordEncoder.encode(users.password);
            userRepository.save(userNuevo);

            UserResult userResult=new UserResult(userNuevo.userid,userNuevo.username);

            return ResponseEntity.ok().body(userResult);//OK
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR");

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUser(@PathVariable UUID id){
        Users file = userRepository.findById(id).orElse(null);

        if (file == null) {return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Error.message("No s'ha trobat l'usuari amd id '"+id+"'"));}
        //si no ha encontrado

        userRepository.delete(file);//elimina el anime con ese id
        return ResponseEntity.ok().body(Error.message("S'ha eliminat l'usuari amb id '"+id+"'"));//si sale bien (ok) devuelves body
    }

    @DeleteMapping("/")
    public void deleteAllUsers(){
        userRepository.deleteAll();
    }
}
