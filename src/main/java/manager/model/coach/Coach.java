package manager.model.coach;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String  date;
    private String address;
    @ManyToOne
    private WorkPosition position;

    private String imagePath;
    @Transient
    private MultipartFile image;
    private Long sumHardSalary;
    private Long sumBonusSalary;
    private boolean typicalCoach = false;
    private boolean status = true;
}
