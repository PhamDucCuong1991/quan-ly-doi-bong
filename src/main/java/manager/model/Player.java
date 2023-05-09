package manager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import manager.model.player.index_player.IndexPlayer;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String date;
    private String address;
    @ManyToOne
    private Position position;
    @ManyToOne
    private Performance performance;
    private Double weight;
    private Double height;
    private String img;
    @Transient
    private MultipartFile multipartFile;
    @ManyToOne
    private IndexPlayer indexPlayer;

    private Long shirtNumber;
    private boolean formation=false;
    private boolean situation=false;
    private boolean status = true;



}
