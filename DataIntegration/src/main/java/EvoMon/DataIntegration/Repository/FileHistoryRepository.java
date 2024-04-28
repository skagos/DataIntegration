package EvoMon.DataIntegration.Repository;


import EvoMon.DataIntegration.Model.FileHistory;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FileHistoryRepository extends JpaRepository<FileHistory, Integer> {

    FileHistory findByName(String name);

    boolean existsByName(String name);

    @Transactional
    @Modifying
    @Query("UPDATE FileHistory fh SET fh.data = false WHERE fh.name = :name")
    void updateDataFieldToFalseByName(@Param("name") String name);

    @Query("SELECT fh.name FROM FileHistory fh WHERE fh.name = :name AND fh.data = true")
    String findNameWithDataTrue(@Param("name") String name);

}
