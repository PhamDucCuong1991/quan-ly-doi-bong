package manager.sevice.player_sevice;

import manager.model.Performance;
import manager.model.Player;
import manager.model.Position;
import manager.model.player.index_player.IndexPlayer;
import manager.model.player.salary.SalaryPlayer;
import manager.repository.IIndexRepository;
import manager.repository.IPerformanceRepository;
import manager.repository.IPlayerRepository;
import manager.repository.IPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
@Service
public class PlayerService implements IPlayerService{
    @Value("${upload.path}")
    private String link;

    @Value("${display.path}")
    private String displayLink;
    @Autowired
    IPlayerRepository iPlayerRepository;
    @Autowired
    IIndexRepository iIndexRepository;
    @Autowired
    IPerformanceRepository iPerformanceRepository;
    @Autowired
    IPositionRepository iPositionRepository;

    @Override
    public SalaryPlayer listSalary(Long id1, Long id2) {
        return iPlayerRepository.listSalary(id1,id2);
    }

    @Override
    public Page<Player> primaryTeam(Pageable pageable) {
        return iPlayerRepository.primaryTeam(pageable);
    }

    @Override
    public Page<Player> secondaryTeam(Pageable pageable) {
        return iPlayerRepository.secondaryTeam(pageable);
    }

    @Override
    public Optional<Player> findById(Long id) {
        return iPlayerRepository.findById(id);
    }

    @Override
    public void save(Player player, MultipartFile file) {
        if (file !=null){
            String fileName = file.getOriginalFilename();
            try {
                FileCopyUtils.copy(file.getBytes(),new File(link +fileName));
            }catch (IOException e){
                e.printStackTrace();
            }
            player.setImg(displayLink+fileName);
        }else {
            player.setImg(displayLink +"Emile-Smith-Rowe.jpg");
        }
        iPlayerRepository.save(player);
    }

    @Override
    public void delete(Long id) {
        iPlayerRepository.deleteById(id);
    }

    @Override
    public Player showIndex(Long id) {
        return iPlayerRepository.showIndexPlayer(id);
    }

    @Override
    public List<SalaryPlayer> salary(Long id) {
        return (List<SalaryPlayer>) iPlayerRepository.listSalaryByOneWeek(id);
    }

    @Override
    public List<Performance> finAlPerformancel() {
        return iPerformanceRepository.findAll();
    }

    @Override
    public List<IndexPlayer> finAllIndexPlayer() {
        return iIndexRepository.findAll();
    }

    @Override
    public List<Position> findALlPosition() {
        return iPositionRepository.findAll();
    }
}
