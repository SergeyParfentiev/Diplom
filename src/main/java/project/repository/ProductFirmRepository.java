package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.tables.ProductFirm;

public interface ProductFirmRepository extends JpaRepository<ProductFirm, Long>{
}
