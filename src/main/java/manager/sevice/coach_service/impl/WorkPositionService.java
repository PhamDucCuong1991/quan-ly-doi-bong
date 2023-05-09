package manager.sevice.coach_service.impl;

import manager.model.coach.WorkPosition;
import manager.repository.coach.IWorkPositionRepository;
import manager.sevice.coach_service.my_interface.IWorkPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkPositionService implements IWorkPositionService {
    @Autowired
    private IWorkPositionRepository workPositionRepository;
    @Override
    public List<WorkPosition> findAll() {
        return workPositionRepository.findAll();
    }

    @Override
    public Page<WorkPosition> displayAll(Pageable pageable) {
        return null;
    }

    @Override
    public WorkPosition save(WorkPosition workPosition) {
        return null;
    }

    @Override
    public WorkPosition findById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
