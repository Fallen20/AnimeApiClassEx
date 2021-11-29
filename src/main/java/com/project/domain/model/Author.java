package com.project.domain.model;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID authorid;

    public String name;
    public String imageurl;


    @ManyToMany(mappedBy = "authors")//el nombre es el set del jointable
    //mappedby es que ya esta hecha la relacion y no hace falta repetir todo
    public Set<Anime> animes; //como lo devuelve, el set ha de ser del otro tipo de tabla
    //con esto obtenemos los animes del autor

    //no puede devolver list porque a veces se buggea y da dobles. Con SET nos ahorramos los duplicados


}
