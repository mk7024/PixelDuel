package main.mk7024.Player;


public enum PlayerState {
    INLOBBY,INGAME;


    public static boolean canJoin(PlayerState playerState){
        return playerState.equals(INLOBBY);
    }
}
