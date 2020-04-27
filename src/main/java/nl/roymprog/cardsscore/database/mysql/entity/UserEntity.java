package nl.roymprog.cardsscore.database.mysql.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class UserEntity {

    public UserEntity() {
        this.joinedOn = LocalDateTime.now();
    }

    @Id
    @GeneratedValue
    private UUID id;

    private String username;

    private LocalDateTime joinedOn;
}
