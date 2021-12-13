package com.project.repository;

import com.project.domain.model.Users;
import com.project.domain.model.projection.ProyectionUser;
import com.project.domain.model.projection.ProyectionUserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

//consultas
public interface UserRepository extends JpaRepository<Users, UUID> {

   // @Query("select new com.project.domain.dto.UserDetails(userid, username) from Users")
    List<ProyectionUser> findBy();//en funcion del retorno devuelve una cosa u otra

    <T> List<T> findByUserid(UUID id, Class<T> type);//este metodo ya existe por defecto tienes que hacerlo generico para poder usarlo y devolver como quieres
    Users findByUsername(String username);
}
