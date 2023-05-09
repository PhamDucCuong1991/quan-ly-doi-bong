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
public class IndexGK {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int diving;// đổ người (DIV)
    private int handling; // bắt bóng (HAN)
    private int kicking; // phát bóng (KIC)
    private int reflexes; // phản xạ (REF)
    private int speed; //Tốc độ (SPD)
    private int positioning; //chọn vị trí (POS)
}
