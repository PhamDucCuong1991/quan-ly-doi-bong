package manager.sevice.player_sevice;

import manager.model.Performance;
import manager.model.Player;
import manager.model.Position;
import manager.model.player.index_player.IndexPlayer;
import manager.model.player.salary.SalaryPlayer;

public interface IPlayerService extends ICoreService<Player, SalaryPlayer, Performance, IndexPlayer, Position> {
}
