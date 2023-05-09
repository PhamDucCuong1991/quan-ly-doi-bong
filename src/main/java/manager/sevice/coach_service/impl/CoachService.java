package manager.sevice.coach_service.impl;

import manager.model.coach.Coach;
import manager.repository.coach.ICoachRepository;
import manager.sevice.coach_service.my_interface.ICoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class CoachService implements ICoachService {
    @Autowired
    private ICoachRepository coachRepository;


    @Override
    public List<Coach> findAll() {
        return coachRepository.displayListAllCoach();
    }

    @Override
    public Page<Coach> displayAll(Pageable pageable) {
        return coachRepository.displayAllCoach(pageable);
    }

    @Override
    public Coach save(Coach coach) {
        return null;
    }

    @Override
    public Coach findById(Long id) {
        return coachRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        coachRepository.deleteCoach(id);
    }
    @Value("${upload.path}")
    private String link;

    @Value("${display.path}")
    private String displayLink;


    @Override
    public List<Coach> displayTypicalCoach() {
        return coachRepository.displayTypicalCoach();
    }

    @Override
    public Coach saveCoach(MultipartFile file, Coach coach) {
        if (file != null) {
            String fileName = file.getOriginalFilename();
            try {
                FileCopyUtils.copy(file.getBytes(), new File(link + fileName));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            coach.setImagePath(displayLink + fileName);
        }else {
           coach.setImagePath(displayLink + "default.jpg");
        }
        return coachRepository.save(coach);
    }

}
