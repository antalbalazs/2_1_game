package game.fxml.Controller;

import game.model.GameModel;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class GameController {
    @FXML
    private Pane Pane;
    private GameModel gameModel;

    public void displaygrid(){
        for (int i = 0; i < 550; i+=550/11) {
            for (int j = 0; j < 550; j+=550/11) {
                Rectangle r = new Rectangle(i, j, 550/11, 550/11);
                switch (gameModel.getGrid()[j/50][i/50]) {
                    case 1:
                        r.setFill(Color.BLUE);
                        break;
                    case 2:
                        r.setFill(Color.RED);
                        break;
                    default:
                        r.setFill(Color.WHITE);
                }

                Pane.getChildren().addAll(r);
            }
        }
    }
    public void resetgame(){
        gameModel = new GameModel();
    }
    public void initialize(){
        resetgame();
        displaygrid();
    }
}
