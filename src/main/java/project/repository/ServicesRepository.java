package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.tables.Services;

public interface ServicesRepository extends JpaRepository<Services, Long> {
}
