package game.javafx.Controller;

import game.model.GameModel;
import game.result.GameResult;
import game.result.GameResultDao;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.apache.commons.lang3.time.DurationFormatUtils;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;


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
    private String currentPlayer;
    private boolean gameOver;

    @FXML
    private Label winnerlabel;

    @FXML
    private Label stopWatchLabel;

    private GameResultDao gameResultDao;
    private Instant startTime;
    private Timeline stopWatchTimeline;


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

    public void resetgame(){
        startTime=Instant.now();
        createStopWatch();
        gameOver = false;
        winnerlabel.setText("");
        gameModel = new GameModel();
        p1steps.setText(0+"");
        p2steps.setText(0+"");
        gameModel.setP1name(p1name.getText());
        gameModel.setP2name(p2name.getText());
        currentPlayer = gameModel.getP1name();
        gameModel.setP1steps(Integer.valueOf(p1steps.getText()));
        gameModel.setP2steps(Integer.valueOf(p2steps.getText()));
        displaygrid();
    }

    public void displaygrid(){
        Pane.setStyle("-fx-border-color: black");
        for (int i = 1; i < 550; i+=550/11) {
            for (int j = 1; j < 550; j+=550/11) {
                Rectangle r = new Rectangle(i, j, 550/11, 550/11);
                switch (gameModel.getField()[j/50][i/50]) {
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
                r.setOnMousePressed(mouseEvent -> mousePressed(mouseEvent));
            }
        }
    }

    private void mousePressed(MouseEvent mouseEvent) {
        if (!gameOver) {
            if (gameModel.isEmptyField((int) mouseEvent.getY() / 50, (int) mouseEvent.getX() / 50)) {
                gameModel.move(currentPlayer, (int) mouseEvent.getY() / 50, (int) mouseEvent.getX() / 50);
                increasePlayerSteps(currentPlayer);
                switchCurrentPlayer(currentPlayer);
                if (gameModel.isGameOver()) {
                    stopWatchTimeline.stop();
                    gameResultDao = new GameResultDao();
                    gameResultDao.persist(CreateResult());
                    gameOver = true;
                    winnerlabel.setText(gameModel.getWinnerName() + " Won the game in " + gameModel.getWinnerSteps() + " steps.");
                }
            }
            displaygrid();
        }
    }

    private void switchCurrentPlayer(String currentPlayer) {
        if (currentPlayer.equals(gameModel.getP1name())) {
            this.currentPlayer = gameModel.getP2name();
        }
        else {
            this.currentPlayer = gameModel.getP1name();
        }
    }

    private void increasePlayerSteps(String currentPlayer) {
        if (currentPlayer.equals(gameModel.getP1name())) {
            gameModel.setP1steps(gameModel.getP1steps()+1);
            p1steps.setText(gameModel.getP1steps()+"");
        }
        else {
            gameModel.setP2steps(gameModel.getP2steps()+1);
            p2steps.setText(gameModel.getP2steps()+"");
        }
    }


    public void setPlayersName(String p1name, String p2name) {
        this.p1nameString = p1name;
        this.p2nameString = p2name;
    }

    public void exitGame(ActionEvent actionEvent){
        Platform.exit();
    }

    private GameResult CreateResult(){
        return GameResult.builder().name(gameModel.getWinnerName()).steps(gameModel.getWinnerSteps()).duration(Duration.between(startTime,Instant.now())).build();

    }

    private void createStopWatch() {
        stopWatchTimeline = new Timeline(new KeyFrame(javafx.util.Duration.ZERO, e -> {
            long millisElapsed = startTime.until(Instant.now(), ChronoUnit.MILLIS);
            stopWatchLabel.setText(DurationFormatUtils.formatDuration(millisElapsed, "HH:mm:ss"));
        }), new KeyFrame(javafx.util.Duration.seconds(1)));
        stopWatchTimeline.setCycleCount(Animation.INDEFINITE);
        stopWatchTimeline.play();
    }
}
