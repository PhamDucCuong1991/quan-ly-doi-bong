package manager.sevice.player_sevice;

import manager.model.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ICoreService <T,E,P,C,I>{
    E listSalary(Long id1,Long id2);
    Page<T> primaryTeam(Pageable pageable);
    Page<T> secondaryTeam(Pageable pageable);
    Optional<T> findById(Long id);
    void save (T t, MultipartFile file);
    void delete(Long id);
    T showIndex(Long id);
    List<E> salary(Long id);
    List<P> finAlPerformancel();
    List<C> finAllIndexPlayer();
    List<I> findALlPosition();
}
