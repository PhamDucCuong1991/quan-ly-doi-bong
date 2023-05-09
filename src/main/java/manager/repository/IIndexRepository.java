package manager.repository;

import manager.model.player.index_player.IndexPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IIndexRepository extends JpaRepository<IndexPlayer,Long> {
}
