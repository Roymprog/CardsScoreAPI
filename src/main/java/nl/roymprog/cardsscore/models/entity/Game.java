//package nl.roymprog.cardsscore.models.entity;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//import java.util.HashSet;
//import java.util.Set;
//
//@Data
//@AllArgsConstructor
//@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//public class Game {
//
//    @Id
//    @GeneratedValue
//    private java.util.UUID id;
//
//    @ManyToOne
//    @JoinColumn(name = "host_id", nullable = false)
//    private HostEntity hostEntity;
//
//    @ManyToMany(mappedBy = "games")
//    private Set<UUID> players = new HashSet<>();
//
//    public void addPlayer(UUID p) {
//        this.players.add(p);
//        p.getGames().add(this);
//    }
//
//    public void removePlayer(UUID p) {
//        this.players.remove(p);
//        p.getGames().remove(this);
//    }
//
//    private LocalDateTime startTime;
//    private LocalDateTime endTime;
//
//    private String gameState;
//
//}
