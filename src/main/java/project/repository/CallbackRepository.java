package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.tables.Callback;

public interface CallbackRepository extends JpaRepository<Callback, Long> {
}
