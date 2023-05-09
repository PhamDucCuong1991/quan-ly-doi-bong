package manager.repository;

import manager.model.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPerformanceRepository extends JpaRepository<Performance,Long> {
}
