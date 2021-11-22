package com.project.repository;

import com.project.domain.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

//consultas
public interface UserRepository extends JpaRepository<Users, UUID> {
    @Query("select fileid from FileTable")
    List<String> getFileIds();
}
