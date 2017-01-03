package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.tables.ProductFirm;

public interface ProductFirmRepository extends JpaRepository<ProductFirm, Long>{
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM ProductFirm p WHERE p.name = :name")
    boolean findByName(@Param("name") String name);
}
