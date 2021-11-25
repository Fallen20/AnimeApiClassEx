package com.project.controller;

import com.project.domain.dto.Error;
import com.project.domain.dto.FileResult;
import com.project.domain.dto.ListResult;
import com.project.domain.model.FileTable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.project.repository.FileRepository;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/files")
public class FileController {
    private final FileRepository fileRepository;

    public FileController(FileRepository fileRepository) {this.fileRepository = fileRepository;}

    @GetMapping("/")
    public ListResult showFiles(){
        return new ListResult(fileRepository.findBy());
    }//bien

    @GetMapping("/{id}")//si es get a /files/NUMERO, NUMERO se llama ID
    public ResponseEntity<?> getFile(@PathVariable UUID id){
        FileTable file = fileRepository.findById(id).orElse(null);

        if (file == null){return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Error.message("No s'ha trobat l'arxiu amd id '"+id+"'"));}//no sale

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(file.contenttype))
                .contentLength(file.data.length)
                .body(file.data);
    }//bien

    @PostMapping
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile uploadedFile) {
        try {
            FileTable file = new FileTable();
            file.contenttype = uploadedFile.getContentType();
            file.data = uploadedFile.getBytes();

            fileRepository.save(file);//lo guardas

            FileResult fileResult = new FileResult(file.fileid, file.contenttype);

            return ResponseEntity.ok().body(fileResult);

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
        return ResponseEntity.ok().body(Error.message("S'ha eliminat l'anime amb id '"+id+"'"));//si sale bien (ok) devuelves body
    }//ok


    @DeleteMapping("/")
    public void deleteAllFiles(){
        fileRepository.deleteAll();

    }
}
