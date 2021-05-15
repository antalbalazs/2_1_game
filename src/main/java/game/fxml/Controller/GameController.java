package game.fxml.Controller;

import game.model.GameModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public class GameController {
    @FXML
    private Pane Pane;
    private GameModel gameModel;
    @FXML
    private Text p1name;

    @FXML
    private Text p2name;

    @FXML
    private Text p1steps;

    @FXML
    private Text p2steps;

    private String p1nameString;

    private String p2nameString;

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
        displaygrid();
    }
    public void initialize(){
        Platform.runLater(() -> {
            p1name.setText(p1nameString);
            p2name.setText(p2nameString);
            gameModel.setP1name(p1nameString);
            gameModel.setP2name(p2nameString);
            resetgame();
        });
        resetgame();

    }

    public void setPlayersName(String p1name, String p2name) {
        this.p1nameString = p1name;
        this.p2nameString = p2name;
    }
}
