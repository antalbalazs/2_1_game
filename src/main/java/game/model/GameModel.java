package game.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data

@Slf4j

public class GameModel {
    private int[][] field;

    private String p1name;

    private String p2name;

    private int p1steps;

    private int p2steps;

    private String winnerName;

    private int winnerSteps;



    public GameModel() {
        this.field = new int[][]{
                {0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
                {2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2},
                {0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
                {2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2},
                {0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
                {2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2},
                {0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
                {2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2},
                {0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
                {2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2},
                {0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0}
        };
    }

    public boolean isEmptyField(int row, int col) throws IndexOutOfBoundsException {
        if (field[row][col] == 0) {
            return true;
        }
        log.info("The selected ({},{})field is not empty.",col+1, row+1);
        return false;
    }

    public void move(String currentPlayer, int row, int col) {
        if(isEmptyField(row, col)) {
            if (currentPlayer.equals(p1name)) {
                field[row][col] = 1;
                log.info("{} has painted the selected ({},{})field blue.",currentPlayer,col+1,row+1);
            }
            else {
                field[row][col] = 2;
                log.info("{} has painted the selected ({},{})field red.",currentPlayer,col+1,row+1);
            }
        }
    }

    public boolean isGameOver() {
        if (redWinCheck() || blueWinCheck()) {
            log.info("{} has won the game in {} steps.",winnerName,winnerSteps);
            return true;
        }
        return false;
    }

    public boolean redWinCheck() {
        int count = 0;
        for(int row = 0; row < field.length; row++) {
            for (int col = 0; col < field[row].length; col++) {
                if (field[row][col] == 2) {
                    count++;
                }
            }
            if (count == 11) {
                winnerName = p2name;
                winnerSteps = p2steps;
                return true;
            }
            else {
                count = 0;
            }
        }
        return false;
    }

    public boolean blueWinCheck() {
        int count = 0;
        for(int row = 0; row < field.length; row++) {
            for (int col = 0; col < field[row].length; col++) {
                if (field[col][row] == 1) {
                    count++;
                }
            }
            if (count == 11) {
                winnerName = p1name;
                winnerSteps = p1steps;
                return true;
            }
            else {
                count = 0;
            }
        }
        return false;
    }

}
