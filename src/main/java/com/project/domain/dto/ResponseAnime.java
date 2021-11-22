package com.project.domain.dto;

import com.project.domain.model.Anime;

import java.util.List;

public class ResponseAnime {
    public List<Anime> result;

    public ResponseAnime(List<Anime> result) {
        this.result = result;
    }
}
