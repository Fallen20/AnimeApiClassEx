package com.project.repository;

import com.project.domain.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorRespository extends JpaRepository<Author, UUID> {
}
