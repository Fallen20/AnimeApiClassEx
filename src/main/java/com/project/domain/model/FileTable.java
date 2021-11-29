package com.project.domain.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="files")
public class FileTable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID fileid;

    public String contenttype;

    @Lob //large object
    @Type(type="org.hibernate.type.BinaryType")
    public byte[] data;

}

