package repository;

import domain.model.FileTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

//consultas
public interface UserRepository extends JpaRepository<FileTable, UUID> {
    @Query("select fileid from FileTable")
    List<String> getFileIds();
}