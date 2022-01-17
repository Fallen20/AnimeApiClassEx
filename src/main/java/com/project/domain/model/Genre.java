package com.project.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="genres")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID genreid;

    public String label;
    public String imageurl;


    @ManyToMany(mappedBy = "genres")//el nombre es el set del jointable
    @JsonIgnoreProperties("genres")
    public Set<Anime> animes; //como lo devuelve, el set ha de ser del otro tipo de tabla
    //no puede devolver list porque a veces se buggea y da dobles. Con SET nos ahorramos los duplicados


}
