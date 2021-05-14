package game.model;

import lombok.Data;

@Data

public class GameModel {
    private int[][] grid;

    private String p1name;

    private String p2name;

    private int p1steps;

    private int p2steps;

    private String winnerName;

    private int winnerSteps;



    public GameModel() {
        this.grid = new int[][]{
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
        if (grid[row][col] == 0) {
            return true;
        }
        return false;
    }

    public void move(String currentPlayer, int row, int col) {
        if(isEmptyField(row, col)) {
            if (currentPlayer.equals(p1name)) {
                grid[row][col] = 1;
            }
            else {
                grid[row][col] = 2;
            }
        }
    }

}
