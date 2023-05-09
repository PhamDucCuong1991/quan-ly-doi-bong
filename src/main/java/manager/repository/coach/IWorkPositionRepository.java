package manager.repository.coach;

import manager.model.coach.WorkPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface IWorkPositionRepository extends JpaRepository<WorkPosition,Long> {
}
