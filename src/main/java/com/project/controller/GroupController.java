package com.project.controller;

import com.project.domain.dto.Error;
import com.project.domain.dto.ListResult;
import com.project.domain.dto.RequestCreateGroup;
import com.project.domain.dto.RequestMemberDelete;
import com.project.domain.model.Grupos;
import com.project.domain.model.Member;
import com.project.domain.model.User;
import com.project.domain.model.projection.ProyectionGroup1;
import com.project.repository.GroupRepository;
import com.project.repository.MemberRepository;
import com.project.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/group")
public class GroupController {
    private final GroupRepository groupRepository;
    private final MemberRepository memberRepository;
    private UserRepository userRepository;

    public GroupController(GroupRepository groupRepository, MemberRepository memberRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.memberRepository = memberRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllGroups() {
        return ResponseEntity.ok().body(new ListResult(groupRepository.findBy(ProyectionGroup1.class)));
    }

    @GetMapping("/{id}/")
    public ResponseEntity<?> getSpecificGroups(@PathVariable UUID id) {
        Grupos grupos = groupRepository.findByGroupid(id);

        if (grupos != null) {
            return ResponseEntity.ok().body(new ListResult(groupRepository.findByGroupid(id, ProyectionGroup1.class)));

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Error.message("No existe ese grupo"));
        }
    }

    @PostMapping("/{id}/")
    public ResponseEntity<?> enterGroup(@PathVariable UUID id, Authentication authentication) {
        if (authentication.getName() != null) {
            if (groupRepository.findByGroupid(id) != null) {
                User usuario = userRepository.findByUsername(authentication.getName());
                Grupos grupo = groupRepository.findByGroupid(id);

                Member miembro = new Member();
                miembro.groupid = grupo.groupid;
                miembro.userid = usuario.userid;

                memberRepository.save(miembro);

                return ResponseEntity.ok().body(Error.message("Se ha unido al grupo " + id + " con exito"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Error.message("No existe ese grupo"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Error.message("Debes estar logeado para hacer esta accion"));

        }
    }

    @PostMapping("/")
    public ResponseEntity<?> createGroup(@RequestBody RequestCreateGroup requestGroup, Authentication authentication) {
        if (authentication.getName() != null) {
            User usuario = userRepository.findByUsername(authentication.getName());

            Grupos grupo = new Grupos();
            grupo.nombre_grupo = requestGroup.nombre_grupo;
            grupo.descripcion = requestGroup.descripcion;
            groupRepository.save(grupo);

            Member miembro = new Member();
            miembro.groupid = grupo.groupid;
            miembro.userid = usuario.userid;

            memberRepository.save(miembro);
            return ResponseEntity.ok().body(Error.message("Creado el grupo '" + requestGroup.nombre_grupo + "' con exito"));

        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Error.message("Debes estar logeado para hacer esta accion"));
        }
    }

    @DeleteMapping("/{grupoid}/")
    public ResponseEntity<?> borrarMiembroGrupo(@PathVariable UUID grupoid, @RequestBody RequestMemberDelete requestMemberDelete, Authentication authentication) {

        if (authentication.getName() != null) {
            if (groupRepository.findByGroupid(grupoid) != null) {
                User usuario = userRepository.findByUsername(authentication.getName());
                Grupos grupo = groupRepository.findByGroupid(grupoid);

                if (grupo.miembros.contains(usuario)) {

                    User usuarioBorrar=userRepository.findByUserid(requestMemberDelete.userid);

                    if(grupo.miembros.contains(usuarioBorrar)){
                        Member member = new Member();
                        member.userid = usuarioBorrar.userid;
                        member.groupid = grupo.groupid;

                        memberRepository.delete(member);

                    }
                    else {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Error.message("El usuario con id " + requestMemberDelete.userid + " no está en el grupo"));
                    }

                    return ResponseEntity.ok().body(Error.message("Borrado al usuario con exito"));
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Error.message("Debes ser parte del grupo para hacer esta acción"));
                }
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Error.message("No existe ese grupo"));
            }


        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Error.message("Has de estar logeado para hacer esta acción"));
        }

    }
}



