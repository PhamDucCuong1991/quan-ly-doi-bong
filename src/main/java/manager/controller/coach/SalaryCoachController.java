package manager.controller.coach;

import manager.model.coach.Coach;
import manager.model.coach.SalaryCoach;
import manager.sevice.coach_service.my_interface.ISalaryCoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin/salaries")
@PropertySource("classpath:application.properties")
public class SalaryCoachController {
    @Autowired
    private ISalaryCoachService salaryCoachService;

    @GetMapping
    public ResponseEntity<Page<SalaryCoach>> displayAllSalary(@PageableDefault(size = 7) Pageable pageable) {
        Page<SalaryCoach> salaryCoaches = salaryCoachService.displayAll(pageable);
        if (salaryCoaches.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(salaryCoaches, HttpStatus.OK);
    }

    @GetMapping("/{idCoach}/{idWeek}")
    public ResponseEntity<SalaryCoach> findById(@PathVariable("idCoach") Long idCoach,
                                                @PathVariable("idWeek") Long idWeek) {
        SalaryCoach salaryCoach = salaryCoachService.findSalary(idCoach, idWeek);
        if (salaryCoach != null) {
            return new ResponseEntity<>(salaryCoach, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/save")
    public ResponseEntity<SalaryCoach> saveSalaryCoach(@RequestBody SalaryCoach salaryCoach) {
        salaryCoachService.updateSumHardSalary(salaryCoach.getCoach().getId());
        salaryCoachService.updateSumBonusSalary(salaryCoach.getCoach().getId());
        return new ResponseEntity<>(salaryCoachService.save(salaryCoach), HttpStatus.OK);
    }

    @PostMapping("/update/{idCoach}/{idWeek}")
    public ResponseEntity<Void> updateSalaryCoach(@RequestBody SalaryCoach salaryCoach,
                                                  @PathVariable("idCoach") Long idCoach,
                                                  @PathVariable("idWeek") Long idWeek) {
        salaryCoachService.updateSumHardSalary(salaryCoach.getCoach().getId());
        salaryCoachService.updateSumBonusSalary(salaryCoach.getCoach().getId());
        salaryCoachService.updateSalaryCoach(salaryCoach, idCoach, idWeek);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/delete/{idCoach}/{idWeek}")
    public ResponseEntity<SalaryCoach> deleteSalaryCoach(@PathVariable("idCoach") Long idCoach,@PathVariable("idWeek") Long idWeek){
        SalaryCoach salaryCoach = salaryCoachService.findSalary(idCoach,idWeek);
        if (salaryCoach!=null){
            salaryCoachService.deleteSalary(idCoach,idWeek);
            return new ResponseEntity<>(salaryCoach,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
