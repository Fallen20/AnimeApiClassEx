package com.project.repository;

import com.project.domain.dto.FileDetails;
import com.project.domain.model.FileTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;
//consulta=select a from
public interface FileRepository extends JpaRepository<FileTable, UUID> {//quien le da la informacion de la tabla. Es quien hace las consultas
    // la tabla y el tipo de dato de la clave primaria
    //con interface genera la clase por cada consulta

    //@Query("select new com.project.domain.dto.FileDetails(fileid, contenttype) from FileTable")
    List<FileDetails> findBy();

    //List<ProjectionEx> findBy();
    //si sin when>findBy
    //tras hacerlo aqui te vas al controler y en vez de findAll pones findBy (el nombre del metodo)
    //el que lo use ha de devolver o la clase de la lista o un ResponseEntity<?>
        //si es el primero hace [{campos}]
        //si es con el segundo hace { result:[{campos}]}

}
