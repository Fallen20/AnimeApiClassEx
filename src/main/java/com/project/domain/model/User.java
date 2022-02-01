package com.project.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID userid;

    public String username;
    public String password;

    @ManyToMany(mappedBy = "favouritedBy")
    @JsonIgnoreProperties("favouritedBy")
    public Set<Anime> favourites;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="user_follows", joinColumns = @JoinColumn(name="actual_user"), inverseJoinColumns = @JoinColumn(name = "users_followed"))
    //@JsonIgnoreProperties("followsUser")
    public Set<User> follows;

    @ManyToMany(mappedBy = "commentedUser")
    @JsonIgnoreProperties("commentedUser")
    public Set<Comment> userComments;

    @ManyToMany(mappedBy = "miembros")
    @JsonIgnoreProperties("miembros")
    public Set<Grupos> grupo;



}
