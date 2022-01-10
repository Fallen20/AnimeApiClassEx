package com.project.domain.model;

import com.project.domain.model.keys.FavouriteIdClass;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name="fav_animes_from_user")
@IdClass(FavouriteIdClass.class)
public class Favourite {
    @Id
    public UUID userid;
    @Id
    public UUID animeid;

    public String image;
}
