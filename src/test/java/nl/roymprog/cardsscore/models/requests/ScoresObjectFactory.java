package nl.roymprog.cardsscore.models.requests;

import nl.roymprog.cardsscore.models.ChineesPoepen;

import java.util.*;

public class ScoresObjectFactory {
  public static Map<String, List<ChineesPoepen.Score>> getRoundScore() {
    Map<String, List<ChineesPoepen.Score>> scores = new HashMap<>();

    List<ChineesPoepen.Score> scoresList = new ArrayList<>();
    scoresList.add(new ChineesPoepen.Score(0, 0, 0));
    scores.put(PlayersObjectFactory.PLAYER_1, scoresList);
    scoresList = new ArrayList<>();
    scoresList.add(new ChineesPoepen.Score(0, 0, 0));
    scores.put(PlayersObjectFactory.PLAYER_2, scoresList);
    scoresList = new ArrayList<>();
    scoresList.add(new ChineesPoepen.Score(0, 0, 0));
    scores.put(PlayersObjectFactory.PLAYER_3, scoresList);
    scoresList = new ArrayList<>();
    scoresList.add(new ChineesPoepen.Score(0, 1, 0));
    scores.put(PlayersObjectFactory.PLAYER_4, scoresList);

    return scores;
  }

  public static Map<String, List<ChineesPoepen.Score>> getInvalidScoredScore() {
    Map<String, List<ChineesPoepen.Score>> scores = new HashMap<>();
    Set<String> players = PlayersObjectFactory.getPlayers();
    Iterator<String> iterator = players.iterator();

    List<ChineesPoepen.Score> scoresList = new ArrayList<>();
    scoresList.add(new ChineesPoepen.Score(0, 0, 0));
    scores.put(iterator.next(), scoresList);
    scoresList = new ArrayList<>();
    scoresList.add(new ChineesPoepen.Score(0, 0, 0));
    scores.put(iterator.next(), scoresList);
    scoresList = new ArrayList<>();
    scoresList.add(new ChineesPoepen.Score(0, 0, 0));
    scores.put(iterator.next(), scoresList);
    scoresList = new ArrayList<>();
    scoresList.add(new ChineesPoepen.Score(0, 0, 0));
    scores.put(iterator.next(), scoresList);

    return scores;
  }

  public static Map<String, List<ChineesPoepen.Score>> getInvalidCalledScore() {
    Map<String, List<ChineesPoepen.Score>> scores = new HashMap<>();
    Set<String> players = PlayersObjectFactory.getPlayers();
    Iterator<String> iterator = players.iterator();

    List<ChineesPoepen.Score> scoresList = new ArrayList<>();
    scoresList.add(new ChineesPoepen.Score(0, 0, 0));
    scores.put(iterator.next(), scoresList);
    scoresList = new ArrayList<>();
    scoresList.add(new ChineesPoepen.Score(0, 0, 0));
    scores.put(iterator.next(), scoresList);
    scoresList = new ArrayList<>();
    scoresList.add(new ChineesPoepen.Score(0, 0, 0));
    scores.put(iterator.next(), scoresList);
    scoresList = new ArrayList<>();
    scoresList.add(new ChineesPoepen.Score(1, 0, 0));
    scores.put(iterator.next(), scoresList);

    return scores;
  }

  public static Map<String, List<ChineesPoepen.Score>> getFullGame() {
    Map<String, List<ChineesPoepen.Score>> scores = new HashMap<>();
    Set<String> players = PlayersObjectFactory.getPlayers();

    ChineesPoepen.Score round1 = new ChineesPoepen.Score(0, 0, 1);
    ChineesPoepen.Score round2 = new ChineesPoepen.Score(1, 1, 2);
    ChineesPoepen.Score round3 = new ChineesPoepen.Score(2, 2, 3);
    ChineesPoepen.Score round4 = new ChineesPoepen.Score(0, 0, 4);
    ChineesPoepen.Score round5 = new ChineesPoepen.Score(1, 0, 5);
    ChineesPoepen.Score round6 = new ChineesPoepen.Score(4, 4, 6);
    ChineesPoepen.Score round7 = new ChineesPoepen.Score(2, 2, 7);
    ChineesPoepen.Score round8 = new ChineesPoepen.Score(3, 2, 8);
    ChineesPoepen.Score round9 = new ChineesPoepen.Score(1, 0, 9);
    ChineesPoepen.Score round17 = new ChineesPoepen.Score(0, 0, 17);
    ChineesPoepen.Score round16 = new ChineesPoepen.Score(1, 1, 16);
    ChineesPoepen.Score round15 = new ChineesPoepen.Score(2, 2, 15);
    ChineesPoepen.Score round14 = new ChineesPoepen.Score(0, 0, 14);
    ChineesPoepen.Score round13 = new ChineesPoepen.Score(1, 0, 13);
    ChineesPoepen.Score round12 = new ChineesPoepen.Score(4, 3, 12);
    ChineesPoepen.Score round11 = new ChineesPoepen.Score(2, 2, 11);
    ChineesPoepen.Score round10 = new ChineesPoepen.Score(3, 2, 10);
    List<ChineesPoepen.Score> scoresList = new ArrayList<>();
    scoresList.add(round1);
    scoresList.add(round2);
    scoresList.add(round3);
    scoresList.add(round4);
    scoresList.add(round5);
    scoresList.add(round6);
    scoresList.add(round7);
    scoresList.add(round8);
    scoresList.add(round9);
    scoresList.add(round10);
    scoresList.add(round11);
    scoresList.add(round12);
    scoresList.add(round13);
    scoresList.add(round14);
    scoresList.add(round15);
    scoresList.add(round16);
    scoresList.add(round17);
    scores.put(PlayersObjectFactory.PLAYER_1, scoresList);
    round1 = new ChineesPoepen.Score(0, 1, 1);
    round2 = new ChineesPoepen.Score(1, 0, 2);
    round3 = new ChineesPoepen.Score(1, 0, 3);
    round4 = new ChineesPoepen.Score(1, 0, 4);
    round5 = new ChineesPoepen.Score(2, 2, 5);
    round6 = new ChineesPoepen.Score(0, 1, 6);
    round7 = new ChineesPoepen.Score(2, 2, 7);
    round8 = new ChineesPoepen.Score(0, 2, 8);
    round9 = new ChineesPoepen.Score(1, 1, 9);
    round17 = new ChineesPoepen.Score(0, 1, 17);
    round16 = new ChineesPoepen.Score(1, 0, 16);
    round15 = new ChineesPoepen.Score(1, 0, 15);
    round14 = new ChineesPoepen.Score(1, 0, 14);
    round13 = new ChineesPoepen.Score(2, 2, 13);
    round12 = new ChineesPoepen.Score(0, 0, 12);
    round11 = new ChineesPoepen.Score(2, 2, 11);
    round10 = new ChineesPoepen.Score(0, 2, 10);
    scoresList = new ArrayList<>();
    new ChineesPoepen.Score(1, 1, 9);
    scoresList.add(round1);
    scoresList.add(round2);
    scoresList.add(round3);
    scoresList.add(round4);
    scoresList.add(round5);
    scoresList.add(round6);
    scoresList.add(round7);
    scoresList.add(round8);
    scoresList.add(round9);
    scoresList.add(round10);
    scoresList.add(round11);
    scoresList.add(round12);
    scoresList.add(round13);
    scoresList.add(round14);
    scoresList.add(round15);
    scoresList.add(round16);
    scoresList.add(round17);
    scores.put(PlayersObjectFactory.PLAYER_2, scoresList);
    round1 = new ChineesPoepen.Score(0, 0, 1);
    round2 = new ChineesPoepen.Score(1, 0, 2);
    round3 = new ChineesPoepen.Score(1, 1, 3);
    round4 = new ChineesPoepen.Score(2, 3, 4);
    round5 = new ChineesPoepen.Score(2, 1, 5);
    round6 = new ChineesPoepen.Score(0, 1, 6);
    round7 = new ChineesPoepen.Score(2, 2, 7);
    round8 = new ChineesPoepen.Score(3, 2, 8);
    round9 = new ChineesPoepen.Score(4, 4, 9);
    round17 = new ChineesPoepen.Score(0, 0, 17);
    round16 = new ChineesPoepen.Score(1, 0, 16);
    round15 = new ChineesPoepen.Score(1, 1, 15);
    round14 = new ChineesPoepen.Score(2, 2, 14);
    round13 = new ChineesPoepen.Score(2, 1, 13);
    round12 = new ChineesPoepen.Score(0, 1, 12);
    round11 = new ChineesPoepen.Score(2, 2, 11);
    round10 = new ChineesPoepen.Score(3, 2, 10);
    scoresList = new ArrayList<>();
    scoresList.add(round1);
    scoresList.add(round2);
    scoresList.add(round3);
    scoresList.add(round4);
    scoresList.add(round5);
    scoresList.add(round6);
    scoresList.add(round7);
    scoresList.add(round8);
    scoresList.add(round9);
    scoresList.add(round10);
    scoresList.add(round11);
    scoresList.add(round12);
    scoresList.add(round13);
    scoresList.add(round14);
    scoresList.add(round15);
    scoresList.add(round16);
    scoresList.add(round17);
    scores.put(PlayersObjectFactory.PLAYER_3, scoresList);
    round1 = new ChineesPoepen.Score(0, 0, 1);
    round2 = new ChineesPoepen.Score(0, 1, 2);
    round3 = new ChineesPoepen.Score(0, 0, 3);
    round4 = new ChineesPoepen.Score(2, 1, 4);
    round5 = new ChineesPoepen.Score(2, 2, 5);
    round6 = new ChineesPoepen.Score(0, 0, 6);
    round7 = new ChineesPoepen.Score(2, 1, 7);
    round8 = new ChineesPoepen.Score(3, 2, 8);
    round9 = new ChineesPoepen.Score(4, 4, 9);
    round17 = new ChineesPoepen.Score(0, 0, 17);
    round16 = new ChineesPoepen.Score(0, 1, 16);
    round15 = new ChineesPoepen.Score(0, 0, 15);
    round14 = new ChineesPoepen.Score(2, 2, 14);
    round13 = new ChineesPoepen.Score(2, 2, 13);
    round12 = new ChineesPoepen.Score(0, 2, 12);
    round11 = new ChineesPoepen.Score(2, 1, 11);
    round10 = new ChineesPoepen.Score(3, 2, 10);
    scoresList = new ArrayList<>();
    scoresList.add(round1);
    scoresList.add(round2);
    scoresList.add(round3);
    scoresList.add(round4);
    scoresList.add(round5);
    scoresList.add(round6);
    scoresList.add(round7);
    scoresList.add(round8);
    scoresList.add(round9);
    scoresList.add(round10);
    scoresList.add(round11);
    scoresList.add(round12);
    scoresList.add(round13);
    scoresList.add(round14);
    scoresList.add(round15);
    scoresList.add(round16);
    scoresList.add(round17);
    scores.put(PlayersObjectFactory.PLAYER_4, scoresList);

    return scores;
  }
}
