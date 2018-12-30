package nl.roymprog.cardsscore.models;

import java.time.LocalDateTime;
import java.util.List;

public class User {

    String id;
    LocalDateTime joinedOn;
    List<Game> games;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getJoinedOn() {
        return joinedOn;
    }

    public void setJoinedOn(LocalDateTime joinedOn) {
        this.joinedOn = joinedOn;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
}
