package hotciv.standard.Broker;

public interface GameLobbyInterface {

  /**
   * Method which creates the game
   * @param playerName Name of the player
   * @param playerNumber Tells what number the current player has
   */
  public void createGame(String playerName, int playerNumber);
}
