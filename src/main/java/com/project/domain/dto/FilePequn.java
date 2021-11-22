package com.project.domain.dto;

import java.util.UUID;

public class FilePequn {
    public UUID fileid;
    public String contenttype;

    public FilePequn(UUID fileid, String contenttype) {
        this.fileid = fileid;
        this.contenttype = contenttype;
    }
}
