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

    public boolean isGameOver() {
        if (redWinCheck() || blueWinCheck()) {
            return true;
        }
        return false;
    }

    public boolean redWinCheck() {
        int count = 0;
        for(int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == 2) {
                    count++;
                }
            }
            if (count == 11) {
                System.out.println("RED WON");
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
        for(int  row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[col][row] == 1) {
                    count++;
                }
            }
            if (count == 11) {
                System.out.println("BLUE WON");
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
