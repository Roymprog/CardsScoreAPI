package nl.roymprog.cardsscore.models.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
public class Player extends User{

    public Player(UUID id) {
        super(id);
    }

    @ManyToMany
    private Set<Game> games = new HashSet<>();
}
