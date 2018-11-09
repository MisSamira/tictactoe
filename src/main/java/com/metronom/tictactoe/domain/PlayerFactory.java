package com.metronom.tictactoe.domain;

import com.metronom.tictactoe.config.PlayerConfig;

public class PlayerFactory {

    /**
     * it's a playerFactory that return humanPlayer or computerPlayer based on the isAI argument
     * isAI true if the player is computer otherwise is false
     *
     * @param isAI
     * @param playerConfig
     * @return Human or Computer Player
     */
    public static Player buildPlayer(Boolean isAI,PlayerConfig playerConfig){
        Player player;
        if(isAI){
            player = new SimpleComputerPlayer(Board.getInstance(),playerConfig);
        } else {
            player = new HumanPlayer(playerConfig);
        }
        player.setSymbol(playerConfig);
        return player;
    }
}
