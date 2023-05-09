package manager.model.player.index_player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IndexPlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int acceleration; // Tốc Độ
    private int shotPower; // Sút
    private int pass;// Chuyền
    private int dribbling;// Rê Bóng
    private int marking;// kem nguoi
    private int physical;// thể Lực


}