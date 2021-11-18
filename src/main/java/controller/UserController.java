package controller;

import domain.dto.Error;
import domain.dto.ResponseUser;
import domain.model.Anime;
import domain.model.Users;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repository.UserRepository;

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
    }

    @PostMapping("/")
    public ResponseEntity<?> createAnime(@RequestBody Users users){//requestBody es que quieres que te envie toda la info

        for(Users a :userRepository.findAll()){
            if(users.username.equals(a.username)){return ResponseEntity.status(HttpStatus.FOUND).body(Error.message("Ya existe un anime con este nombre"));}
        }

        userRepository.save(users);//guarda la cosa que recibe
        return ResponseEntity.ok().body("{\nuserid:"+users.userid+",\nusername"+users.username);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> elimiarUser(@PathVariable UUID id){
        Users file = userRepository.findById(id).orElse(null);

        if (file == null) {return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Error.message("No s'ha trobat l'anime amd id '"+id+"'"));}
        //si no ha encontrado

        userRepository.delete(file);//elimina el anime con ese id
        return ResponseEntity.ok().body("S''ha eliminat l'anime amb id '"+id+"'");//si sale bien (ok) devuelves body
    }
}
