package com.project.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="grupos")
public class Grupos {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID groupid;

    public String nombre_grupo;
    public String descripcion;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="miembros_grupo", joinColumns = @JoinColumn(name="groupid"), inverseJoinColumns = @JoinColumn(name = "userid"))
    @JsonIgnoreProperties("grupo")
    public Set<User> miembros;
}

