package manager.sevice.coach_service.impl;

import manager.model.coach.WeekCoach;
import manager.repository.coach.IWeekCoachRepository;
import manager.sevice.coach_service.my_interface.IWeekCoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeekCoachService implements IWeekCoachService {
    @Autowired
    private IWeekCoachRepository weekCoachRepository;
    @Override
    public List<WeekCoach> findAll() {
        return weekCoachRepository.findAll();
    }

    @Override
    public Page<WeekCoach> displayAll(Pageable pageable) {
        return null;
    }

    @Override
    public WeekCoach save(WeekCoach weekCoach) {
        return null;
    }

    @Override
    public WeekCoach findById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
