package manager.model.player.salary;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class SalaryId implements Serializable {
    @Column
    private Long playerId;

    @Column
    private Long weekId;
}
