package project.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.tables.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p")
    List<Product> allProducts(Pageable pageable);

    @Query("SELECT COUNT (p) FROM Product p WHERE p.cost BETWEEN :firstNumber AND :secondNumber AND " +
            "p.productType.id IN :productTypeId AND p.productFirm.id IN :productFirmId")
    long countPages(@Param("firstNumber") int firstNumber, @Param("secondNumber") int secondNumber,
                    @Param("productTypeId") List<Long> listTypeId, @Param("productFirmId") List<Long> listFirmId);

    @Query("SELECT p FROM Product p WHERE p.cost BETWEEN :firstNumber AND :secondNumber AND " +
            "p.productType.id IN :productTypeId AND p.productFirm.id IN :productFirmId")
    List<Product> productList(@Param("firstNumber") int firstNumber, @Param("secondNumber") int secondNumber,
                              @Param("productTypeId") List<Long> listTypeId, @Param("productFirmId") List<Long> listFirmId,
                              Pageable pageable);

    @Query("SELECT MAX(p.cost) FROM Product p")
    long maxPrice();
}
