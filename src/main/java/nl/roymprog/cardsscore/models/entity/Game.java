package nl.roymprog.cardsscore.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.roymprog.cardsscore.configuration.chineespoepensm.ChineesPoepenStates;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "GAME_TYPE")
public class Game {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "host_id", nullable = false)
    private Host host;

    @ManyToMany(mappedBy = "games")
    private Set<Player> players = new HashSet<>();

    public void addPlayers(Player p) {
        this.players.add(p);
        p.getGames().add(this);
    }

    public void removePlayers(Player p) {
        this.players.remove(p);
        p.getGames().remove(this);
    }

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private String gameState;

    public ChineesPoepenStates getGameState() {
        return ChineesPoepenStates.valueOf(this.gameState);
    }

    public void setGameState(ChineesPoepenStates state) {
        this.gameState = state.name();
    }
}
