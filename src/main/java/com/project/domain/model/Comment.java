package com.project.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID commentid;//esto no se va a repetir

    public String comentario;

    @ManyToMany()
    @JoinTable(name="comment_by_user",joinColumns = @JoinColumn(name="commentid"), inverseJoinColumns = @JoinColumn(name = "userid"))
    @JsonIgnoreProperties("userComments")
    public Set<User> commentedUser;

    @ManyToMany()
    @JoinTable(name="commented_in_anime",joinColumns = @JoinColumn(name="commentid"), inverseJoinColumns = @JoinColumn(name = "animeid"))
    @JsonIgnoreProperties("comments")
    public Set<Anime> commentedAnime;


}
