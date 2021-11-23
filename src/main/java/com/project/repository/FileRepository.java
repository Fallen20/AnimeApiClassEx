package com.project.repository;

import com.project.domain.dto.FileDetails;
import com.project.domain.model.FileTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;
//consulta=select a from
public interface FileRepository extends JpaRepository<FileTable, UUID> {//quien le da la informacion de la tabla. Es quien hace las consultas
    // la tabla y el tipo de dato de la clave primaria
    //con interface genera la clase por cada consulta

    @Query("select new com.project.domain.dto.FileDetails(fileid, contenttype) from FileTable")
    List<FileDetails> getAll();

}
