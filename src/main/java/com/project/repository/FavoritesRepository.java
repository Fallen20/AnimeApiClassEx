package com.project.repository;

import com.project.domain.model.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

//consultas
public interface FavoritesRepository extends JpaRepository<Favourite, UUID> {


}
