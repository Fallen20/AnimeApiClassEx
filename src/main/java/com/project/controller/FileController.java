package com.project.controller;

import com.project.domain.dto.Error;
import com.project.domain.dto.FilePequn;
import com.project.domain.dto.ResponseFile;
import com.project.domain.model.FileTable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.project.repository.FileRepository;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/files")
public class FileController {
    private final FileRepository fileRepository;

    public FileController(FileRepository fileRepository) {this.fileRepository = fileRepository;}

    @GetMapping("/")
    public ResponseEntity<?> showFiles(){
        return ResponseEntity.ok().body(fileRepository.getFilePequns());
    }

    @GetMapping("/{id}")//si es get a /files/NUMERO, NUMERO se llama ID
    public ResponseEntity<?> getFile(@PathVariable UUID id){
        FileTable file = fileRepository.findById(id).orElse(null);

        if (file == null){return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No s'ha trobat l'arxiu amd id '"+id+"'");}//no sale

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(file.contenttype))
                .contentLength(file.data.length)
                .body(file.data);
    }

    @PostMapping
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile uploadedFile) {
        try {
            FileTable file = new FileTable();
            file.contenttype = uploadedFile.getContentType();
            file.data = uploadedFile.getBytes();
            fileRepository.save(file);//lo guardas
            return ResponseEntity.ok().body("{\n\t\"fileid\":\""+file.fileid+"\",\n\t\"contenttype\":\""+file.contenttype+"\"\n}");
            //esto no puede ser asi de cutre

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFile(@PathVariable UUID id){
        FileTable file = fileRepository.findById(id).orElse(null);

        if (file == null) {return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Error.message("No s'ha trobat l'arxiu amd id '"+id+"'"));}
        //si no ha encontrado

        fileRepository.delete(file);//elimina el anime con ese id
        return ResponseEntity.ok().body("S''ha eliminat l'anime amb id '"+id+"'");//si sale bien (ok) devuelves body
    }


    @DeleteMapping("/")
    public void deleteAllUsers(){
        fileRepository.deleteAll();

    }
}