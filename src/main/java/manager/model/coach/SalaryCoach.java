package manager.model.coach;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class  SalaryCoach {
    @EmbeddedId
    private SalaryCoachId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("coachId")
    private Coach coach;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("weekId")
    private WeekCoach weekCoach;

    private Long hardSalary;
    private Long bonusSalary;
    private boolean status = true;


}
