package com.project.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;
import java.util.UUID;

public interface ProjectionAuthorLimitedId {
    //solo deben de estar los campos que quieres que salgan
    //pero en forma de getters (literalmente hacer el get en la clase y pegarlo aqui antes de borrarlo)

    UUID getAuthorid();
    String getName();
    String getImageurl();
    //url no

    @JsonIgnoreProperties("authors")//el nombre es del get del otro
    Set<ProjectionAnimesLimitedId> getAnimes();

}
