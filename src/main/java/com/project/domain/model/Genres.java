package com.project.domain.model;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="genres")
public class Genres {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID genreid;

    public String label;
    public String imageurl;


    @ManyToMany(mappedBy = "genres")//el nombre es el set del jointable
    public Set<Anime> animes; //como lo devuelve, el set ha de ser del otro tipo de tabla
    //no puede devolver list porque a veces se buggea y da dobles. Con SET nos ahorramos los duplicados


}
