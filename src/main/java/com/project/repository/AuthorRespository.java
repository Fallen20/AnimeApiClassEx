package com.project.repository;

import com.project.domain.model.Author;
import com.project.domain.model.projection.ProjectionAuthorNoGenres;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AuthorRespository extends JpaRepository<Author, UUID> {
    List<ProjectionAuthorNoGenres> findBy();
}
