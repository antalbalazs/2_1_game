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


    /**
     * This function represents the game field.
      */
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

    /**
     * This function checks if a selected field is empty or not.
     * @param row the rows of the field
     * @param col the columns of the field
     * @return true if empty false if not empty
     * @throws IndexOutOfBoundsException when
     */
    public boolean isEmptyField(int row, int col) throws IndexOutOfBoundsException {
        if (field[row][col] == 0) {
            return true;
        }
        log.info("The selected ({},{})field is not empty.",col+1, row+1);
        return false;
    }

    /**
     * This function paints the selected field according to the currentPlayer.
     * @param currentPlayer the current player
     * @param row amount of row
     * @param col amunt of col
     */
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

    /**
     * This function checks if the game has ended.
     * @return true if a player won false if not
     */
    public boolean isGameOver() {
        if (redWinCheck() || blueWinCheck()) {
            log.info("{} has won the game in {} steps.",winnerName,winnerSteps);
            return true;
        }
        return false;
    }

    /**
     * This function checks if the red player won the game.
     * @return true if red won false if not
     */
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

    /**
     * This function checks if the blue player won the game.
     * @return true if blue won false if not
     */
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
