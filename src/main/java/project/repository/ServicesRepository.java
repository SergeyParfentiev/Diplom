package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.tables.Services;

public interface ServicesRepository extends JpaRepository<Services, Long> {
    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Services s WHERE s.name = :name")
    boolean findByName(@Param("name") String name);
}
