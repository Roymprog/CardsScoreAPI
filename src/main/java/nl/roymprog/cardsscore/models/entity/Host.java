package nl.roymprog.cardsscore.models.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Data
public class Host extends User {

    @OneToMany(mappedBy = "host")
    Set<Game> games;
}
