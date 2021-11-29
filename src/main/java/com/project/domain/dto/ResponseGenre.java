package com.project.domain.dto;


import com.project.domain.model.Genres;

import java.util.List;

public class ResponseGenre {
    public List<Genres> result;

    public ResponseGenre(List<Genres> result) {this.result = result;}


}
