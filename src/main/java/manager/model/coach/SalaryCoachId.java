package manager.model.coach;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class SalaryCoachId implements Serializable {
    @Column(name = "coach_id")
    private Long coachId;
    @Column(name = "week_id")
    private Long weekId;
}
