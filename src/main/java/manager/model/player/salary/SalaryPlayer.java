package manager.model.player.salary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import manager.model.Player;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class SalaryPlayer {
    @EmbeddedId
    private SalaryId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("playerId")
    private Player player;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("weekId")
    private Week week;

    private Double hardSalary ; // lươngcứng
    private Double salaryBonus ; // thưởng
    private int workingHours;// số giờ làm việc
    private Double capacitySalary; //  lương năng lực * workingHours




}