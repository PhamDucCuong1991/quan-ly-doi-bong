package manager.repository.coach;

import manager.model.coach.WeekCoach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface IWeekCoachRepository extends JpaRepository<WeekCoach,Long> {
}
