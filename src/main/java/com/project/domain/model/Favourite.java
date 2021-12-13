package com.project.domain.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name="fav_animes_from_user")
public class Favourite {
    @Id
    public UUID userid;
    public UUID animeid;
}
