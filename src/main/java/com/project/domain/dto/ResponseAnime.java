package com.project.domain.dto;GET

import com.project.domain.model.Anime;

import java.util.List;

public class ResponseAnime {
    public List<Anime> result;

    public ResponseAnime(List<Anime> result) {
        this.result = result;
    }
}
