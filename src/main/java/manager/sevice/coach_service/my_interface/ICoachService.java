package manager.sevice.coach_service.my_interface;

import manager.model.coach.Coach;
import manager.sevice.coach_service.core.ICrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ICoachService extends ICrudService<Coach> {
    List<Coach> displayTypicalCoach();
    Coach saveCoach(MultipartFile file, Coach coach);


}
