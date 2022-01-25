package com.project.domain.model;

import com.project.domain.model.keys.AnimeCommentsId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name="commented_in_anime")
@IdClass(AnimeCommentsId.class)
public class CommentAnime {

    @Id
    public UUID commentid;
    @Id
    public UUID animeid;

}
