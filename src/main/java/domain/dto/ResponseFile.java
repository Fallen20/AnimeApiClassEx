package domain.dto;

import domain.model.FileTable;

import java.util.List;

public class ResponseFile {
    public List<FileTable> result;

    public ResponseFile(List<FileTable> result) {
        this.result = result;
    }
}
