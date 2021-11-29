package com.project.repository;

import com.project.domain.model.Author;
import com.project.domain.model.Genres;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GenresRespository extends JpaRepository<Genres, UUID> {
}
