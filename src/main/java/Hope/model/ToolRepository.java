package Hope.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToolRepository extends JpaRepository<Tool,Integer> {


    @Query(value = "SELECT * FROM tool d WHERE Visible = 1 AND" +
            "(LOWER(Titre) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(Domaine) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(Description_simple) LIKE LOWER(CONCAT('%', :query, '%')))", nativeQuery = true)
    List<Tool> search(@Param("query") String query);

    @Query(value = "SELECT * FROM tool WHERE VISIBLE = ?", nativeQuery = true)
    List<Tool> findAllByVisible(boolean visible);

}
