package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.tables.ProductType;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long>{
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM ProductType p WHERE p.name = :name")
    boolean findByName(@Param("name") String name);
}
