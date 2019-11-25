package hotciv.standard.Broker;

public interface FutureGame {

  /**
   * Return this games unique ID
   * @return ID
   */
  String getId();

  FutureGame createGame(String playerName, int playerNumber);
}
