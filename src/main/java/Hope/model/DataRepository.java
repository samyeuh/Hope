package Hope.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataRepository extends JpaRepository<DataHope,Integer> {

    @Query(value = "SELECT * FROM hopeDB.HOPE", nativeQuery = true)
    List<DataHope> findAll();

    @Query(value = "SELECT * FROM HOPE d WHERE " +
            "LOWER(Titre) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(Domaine) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(Description_simple) LIKE LOWER(CONCAT('%', :query, '%'))", nativeQuery = true)
    List<DataHope> search(@Param("query") String query);
}
