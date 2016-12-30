package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.tables.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
}
