package nl.roymprog.cardsscore.models;

import java.time.LocalDateTime;
import java.util.List;

public class Game {
    private final String id;
    private final String hostId;
    List<User> players;
    private final LocalDateTime startTime;
    LocalDateTime endTime;
    State state;

    public Game(String id, String hostId) {
        this.id = id;
        this.hostId = hostId;
        this.startTime = LocalDateTime.now();
        this.state = State.INITIALIZED;
    }

    public String getId() {
        return id;
    }

    public String getHostId() {
        return hostId;
    }


    public List<User> getPlayers() {
        return players;
    }

    public void setPlayers(List<User> players) {
        this.players = players;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

}
