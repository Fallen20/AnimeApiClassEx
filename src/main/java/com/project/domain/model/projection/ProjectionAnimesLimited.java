package com.project.domain.model.projection;//si no le pones esto, entra el set de abajo. Y vuelve a pedir animes. Y vuelve aqui.
        //y es un circulo.
        //para pararlo le pones que ignore animes (getAnimes): pilla animes>va a author>hemos dicho que ignore anime asiq ue
        //pide una vez anime aqui y author alli y para

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;
import java.util.UUID;

public interface ProjectionAnimesLimited {
    //solo deben de estar los campos que quieres que salgan
    //pero en forma de getters (literalmente hacer el get en la clase y pegarlo aqui antes de borrarlo)

    //esto es lo que saca
    UUID getAnimeid();
    String getName();
    String getType();
    String getImage();

    @JsonIgnoreProperties("animes")//si no le pones esto, entra el set de abajo. Y vuelve a pedir animes. Y vuelve aqui.
    //y es un circulo.
    //para pararlo le pones que ignore animes (getAnimes): pilla animes>va a author>hemos dicho que ignore anime asiq ue
    //pide una vez anime aqui y author alli y para
    Set<ProjectionAuthorLimited> getAuthors();

}
