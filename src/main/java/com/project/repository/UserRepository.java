package com.project.repository;

import com.project.domain.dto.UserDetails;
import com.project.domain.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

//consultas
public interface UserRepository extends JpaRepository<Users, UUID> {

   // @Query("select new com.project.domain.dto.UserDetails(userid, username) from Users")
    List<UserDetails> findBy();//en funcion del retorno devuelve una cosa u otra

    Users findByUsername(String username);
}
