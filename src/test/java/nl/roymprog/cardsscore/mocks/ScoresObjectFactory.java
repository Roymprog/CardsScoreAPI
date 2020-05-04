package nl.roymprog.cardsscore.mocks;

import nl.roymprog.cardsscore.models.ChineesPoepen;

import java.util.*;

public class ScoresObjectFactory {
  public static Map<String, List<ChineesPoepen.Score>> getRoundScore() {
    Map<String, List<ChineesPoepen.Score>> scores = new HashMap<>();

    List<ChineesPoepen.Score> scoresList = new ArrayList<>();
    scoresList.add(new ChineesPoepen.Score(0, 0, 5));
    scores.put(PlayersObjectFactory.PLAYER_1, scoresList);
    scoresList = new ArrayList<>();
    scoresList.add(new ChineesPoepen.Score(0, 0, 5));
    scores.put(PlayersObjectFactory.PLAYER_2, scoresList);
    scoresList = new ArrayList<>();
    scoresList.add(new ChineesPoepen.Score(0, 0, 5));
    scores.put(PlayersObjectFactory.PLAYER_3, scoresList);
    scoresList = new ArrayList<>();
    scoresList.add(new ChineesPoepen.Score(0, 1, -1));
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

    ChineesPoepen.Score round1 = new ChineesPoepen.Score(0, 0);
    ChineesPoepen.Score round2 = new ChineesPoepen.Score(1, 1);
    ChineesPoepen.Score round3 = new ChineesPoepen.Score(2, 2);
    ChineesPoepen.Score round4 = new ChineesPoepen.Score(0, 0);
    ChineesPoepen.Score round5 = new ChineesPoepen.Score(1, 0);
    ChineesPoepen.Score round6 = new ChineesPoepen.Score(4, 4);
    ChineesPoepen.Score round7 = new ChineesPoepen.Score(2, 2);
    ChineesPoepen.Score round8 = new ChineesPoepen.Score(3, 2);
    ChineesPoepen.Score round9 = new ChineesPoepen.Score(1, 0);
    ChineesPoepen.Score round10 = new ChineesPoepen.Score(3, 2);
    ChineesPoepen.Score round11 = new ChineesPoepen.Score(2, 2);
    ChineesPoepen.Score round12 = new ChineesPoepen.Score(4, 3);
    ChineesPoepen.Score round13 = new ChineesPoepen.Score(1, 0);
    ChineesPoepen.Score round14 = new ChineesPoepen.Score(0, 0);
    ChineesPoepen.Score round15 = new ChineesPoepen.Score(2, 2);
    ChineesPoepen.Score round16 = new ChineesPoepen.Score(1, 1);
    ChineesPoepen.Score round17 = new ChineesPoepen.Score(0, 0);
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
    round1 = new ChineesPoepen.Score(0, 1);
    round2 = new ChineesPoepen.Score(1, 0);
    round3 = new ChineesPoepen.Score(1, 0);
    round4 = new ChineesPoepen.Score(1, 0);
    round5 = new ChineesPoepen.Score(2, 2);
    round6 = new ChineesPoepen.Score(0, 1);
    round7 = new ChineesPoepen.Score(2, 2);
    round8 = new ChineesPoepen.Score(0, 2);
    round9 = new ChineesPoepen.Score(1, 1);
    round10 = new ChineesPoepen.Score(0, 2);
    round11 = new ChineesPoepen.Score(2, 2);
    round12 = new ChineesPoepen.Score(0, 0);
    round13 = new ChineesPoepen.Score(2, 2);
    round14 = new ChineesPoepen.Score(1, 0);
    round15 = new ChineesPoepen.Score(1, 0);
    round16 = new ChineesPoepen.Score(1, 0);
    round17 = new ChineesPoepen.Score(0, 1);
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
    round1 = new ChineesPoepen.Score(0, 0);
    round2 = new ChineesPoepen.Score(1, 0);
    round3 = new ChineesPoepen.Score(1, 1);
    round4 = new ChineesPoepen.Score(2, 3);
    round5 = new ChineesPoepen.Score(2, 1);
    round6 = new ChineesPoepen.Score(0, 1);
    round7 = new ChineesPoepen.Score(2, 2);
    round8 = new ChineesPoepen.Score(3, 2);
    round9 = new ChineesPoepen.Score(4, 4);
    round10 = new ChineesPoepen.Score(3, 2);
    round11 = new ChineesPoepen.Score(2, 2);
    round12 = new ChineesPoepen.Score(0, 1);
    round13 = new ChineesPoepen.Score(2, 1);
    round14 = new ChineesPoepen.Score(2, 2);
    round15 = new ChineesPoepen.Score(1, 1);
    round16 = new ChineesPoepen.Score(1, 0);
    round17 = new ChineesPoepen.Score(0, 0);
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
    round1 = new ChineesPoepen.Score(0, 0);
    round2 = new ChineesPoepen.Score(0, 1);
    round3 = new ChineesPoepen.Score(0, 0);
    round4 = new ChineesPoepen.Score(2, 1);
    round5 = new ChineesPoepen.Score(2, 2);
    round6 = new ChineesPoepen.Score(0, 0);
    round7 = new ChineesPoepen.Score(2, 1);
    round8 = new ChineesPoepen.Score(3, 2);
    round9 = new ChineesPoepen.Score(4, 4);
    round10 = new ChineesPoepen.Score(3, 2);
    round11 = new ChineesPoepen.Score(2, 1);
    round12 = new ChineesPoepen.Score(0, 2);
    round13 = new ChineesPoepen.Score(2, 2);
    round14 = new ChineesPoepen.Score(2, 2);
    round15 = new ChineesPoepen.Score(0, 0);
    round16 = new ChineesPoepen.Score(0, 1);
    round17 = new ChineesPoepen.Score(0, 0);
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
