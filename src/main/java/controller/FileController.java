package controller;

import domain.dto.Error;
import domain.dto.ResponseFile;
import domain.model.Anime;
import domain.model.FileTable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repository.FileRepository;

import java.util.UUID;

@RestController
@RequestMapping("/files")
public class FileController {
    private final FileRepository fileRepository;

    public FileController(FileRepository fileRepository) {this.fileRepository = fileRepository;}

    @GetMapping("/")
    public ResponseFile showFiles(){
        return new ResponseFile(fileRepository.findAll());
    }

    @GetMapping("/{id}")//si es get a /files/NUMERO, NUMERO se llama ID
    public ResponseEntity<byte[]> getFile(@PathVariable UUID id){
        FileTable file = fileRepository.findById(id).orElse(null);

        if (file == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(file.contenttype))
                .contentLength(file.data.length)
                .body(file.data);
    }

    @PostMapping
    public ResponseEntity<?> uploadFile(@RequestParam FileTable file){
        fileRepository.save(file);
        return ResponseEntity.ok().body(file);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnime(@PathVariable UUID id){
        FileTable file = fileRepository.findById(id).orElse(null);

        if (file == null) {return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Error.message("No s'ha trobat l'arxiu amd id '"+id+"'"));}
        //si no ha encontrado

        fileRepository.delete(file);//elimina el anime con ese id
        return ResponseEntity.ok().body("S''ha eliminat l'anime amb id '"+id+"'");//si sale bien (ok) devuelves body
    }


    @DeleteMapping("/")
    public void deleteAllAnime(){
        fileRepository.deleteAll();

    }
}
