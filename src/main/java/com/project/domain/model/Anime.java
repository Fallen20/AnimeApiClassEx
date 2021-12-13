package com.project.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="anime")
public class Anime {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID animeid;

    public String name;
    public String description;
    public String type;
    public int year;
    public String image;


    @ManyToMany//esto es para la relacion
    @JoinTable(name="author_movie_relation", joinColumns = @JoinColumn(name="animeid"), inverseJoinColumns = @JoinColumn(name = "authorid"))
    //1> el nombre de la tabla que une tablas
    //2>el campo que usa de esta tabla (cuando pide los animes del author)
    //3>el campo que usa de la otra tabla (cuando pide los author de los animes)

    @JsonIgnoreProperties("animes")
    public Set<Author> authors; //el set ha de ser del otro tipo de tabla
    //con esto obtenemos los autores de los animes

    @ManyToMany
    @JoinTable(name="genres_movie_relation", joinColumns = @JoinColumn(name="animeid"), inverseJoinColumns = @JoinColumn(name = "genreid"))
    @JsonIgnoreProperties("animes")
    public Set<Genres> genres;


    @ManyToMany
    @JoinTable(name="fav_animes_from_user", joinColumns = @JoinColumn(name="animeid"), inverseJoinColumns = @JoinColumn(name = "userid"))
    @JsonIgnoreProperties("favourites")
    public Set<Users> favouritedBy;//si le pones solo user te confundes porque pueden haber +1

}

