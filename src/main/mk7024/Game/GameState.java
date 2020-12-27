package main.mk7024.Game;

public enum GameState {
    INLOBBY,INGAME;

    public static boolean canJoin(GameState gameState){
        return gameState.equals(INLOBBY);
    }
}
