package main.mk7024.Game;

public enum GameState {
    INLOBBY,INGAME;

    public static boolean canJoin(Game game){
        return game.getGameState().equals(INLOBBY);
    }
}
