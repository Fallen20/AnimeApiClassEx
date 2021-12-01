package com.project.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;
import java.util.UUID;

public interface ProjectionGenresLimited {
    //solo deben de estar los campos que quieres que salgan
    //pero en forma de getters (literalmente hacer el get en la clase y pegarlo aqui antes de borrarlo)

    UUID getGenreid();
    String getLabel();
    //url no

    @JsonIgnoreProperties("genres")//el nombre es del get del otro
    Set<ProjectionAnimesLimited> getAnimes();

}
