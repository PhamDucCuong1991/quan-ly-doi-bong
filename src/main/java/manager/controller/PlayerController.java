package manager.controller;

import manager.model.Performance;
import manager.model.Player;
import manager.model.Position;
import manager.model.player.index_player.IndexPlayer;
import manager.model.player.salary.SalaryPlayer;
import manager.repository.IPlayerRepository;
import manager.sevice.player_sevice.IPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class PlayerController {

    @Autowired
    private IPlayerService iPlayerService;

    @GetMapping("/show-save")
    public ResponseEntity<Map<String, Object>> showSave() {
        Map<String, Object> hasMap = new HashMap<>();
        List<Position> positionList = iPlayerService.findALlPosition();
        List<Performance> performanceList = iPlayerService.finAlPerformancel();
        List<IndexPlayer> indexPlayerList = iPlayerService.finAllIndexPlayer();
        hasMap.put("positionList", positionList);
        hasMap.put("performanceList", performanceList);
        hasMap.put("indexPlayerList", indexPlayerList);
        return new ResponseEntity<>(hasMap, HttpStatus.OK);

    }

    @PostMapping("/save")
    private ResponseEntity<Player> savePlayer(@RequestPart(value = "file", required = false) MultipartFile file,
                                              @RequestPart("player") Player player) {
        iPlayerService.save(player, file);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/player-primary-teams")
    public ResponseEntity<Page<Player>> primaryTeam(@PageableDefault(size = 11) Pageable pageable) {
        return new ResponseEntity<>(iPlayerService.primaryTeam(pageable), HttpStatus.OK);
    }

    @GetMapping("/player-secondary-teams")
    public ResponseEntity<Page<Player>> secondaryTeam(@PageableDefault(size = 11) Pageable pageable) {
        return new ResponseEntity<>(iPlayerService.secondaryTeam(pageable), HttpStatus.OK);
    }

    @GetMapping("/players/{id}")
    public ResponseEntity<Optional<Player>> findById(@PathVariable Long id) {
        Optional<Player> player = iPlayerService.findById(id);
        if (player.isPresent()) {
            return new ResponseEntity<>(player, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/salaries")
    public ResponseEntity<SalaryPlayer> findSalary(@RequestParam Long id1,
                                                   @RequestParam Long id2) {
        SalaryPlayer list = iPlayerService.listSalary(id1, id2);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/index")
    public ResponseEntity<Player> showIndex(@RequestParam Long id) {
        Player player = iPlayerService.showIndex(id);
        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    @GetMapping("/one-week-by-salaries")
    public ResponseEntity<List<SalaryPlayer>> findSalaryByOneWeek(@RequestParam Long id) {
        List<SalaryPlayer> salaryPlayer = iPlayerService.salary(id);
        return new ResponseEntity<>(salaryPlayer, HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteProduct(@RequestParam Long id){
        iPlayerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
