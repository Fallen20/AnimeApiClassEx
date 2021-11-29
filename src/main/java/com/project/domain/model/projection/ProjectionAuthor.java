package com.project.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.domain.model.Anime;

import java.util.Set;
import java.util.UUID;

public interface ProjectionAuthor {
    //solo deben de estar los campos que quieres que salgan
    //pero en forma de getters (literalmente hacer el get en la clase y pegarlo aqui antes de borrarlo)

    UUID getAuthorId();
    String getName();

    @JsonIgnoreProperties("authors")//el nombre es del get del otro
    Set<ProjectionAnimes> getAnimes();
}
