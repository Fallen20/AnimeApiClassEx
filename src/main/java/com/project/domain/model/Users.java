package com.project.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID userid;

    public String username;
    public String password;

    @ManyToMany(mappedBy = "favouritedBy")
    @JsonIgnoreProperties("favouritedBy")
    public Set<Anime> favourites;
}
