package manager.sevice.coach_service.my_interface;

import manager.model.coach.SalaryCoach;
import manager.sevice.coach_service.core.ICrudService;
import org.springframework.data.domain.Page;

public interface ISalaryCoachService extends ICrudService<SalaryCoach> {
    SalaryCoach findSalary(Long idCoach, Long idWeak);
    void deleteSalary(Long idCoach, Long idWeak);

    Long sumHardSalaryCoach(Long idCoach);
    Long sumBonusSalaryCoach(Long idCoach);

    void updateSumHardSalary(Long idCoach);

    void updateSumBonusSalary(Long idCoach);

    void updateSalaryCoach(SalaryCoach salaryCoach,Long idCoach, Long idWeak);
    void deleteSalaryCoachDoubleId(Long idCoach, Long idWeak);
}
