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
    @JsonIgnoreProperties("user_follows")
    public Set<User> follows;

    @ManyToMany(mappedBy = "follows")
    @JsonIgnoreProperties("follows")
    public Set<User> user_follows;

}
