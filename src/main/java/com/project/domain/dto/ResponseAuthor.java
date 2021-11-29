package com.project.domain.dto;


import com.project.domain.model.Author;
import com.project.domain.model.Users;

import java.util.List;

public class ResponseAuthor {
    public List<Author> result;

    public ResponseAuthor(List<Author> result) {this.result = result;}


}
