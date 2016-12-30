package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.tables.ProductType;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long>{
}
