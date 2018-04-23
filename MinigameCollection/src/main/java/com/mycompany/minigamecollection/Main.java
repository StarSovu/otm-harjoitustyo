
package com.mycompany.minigamecollection;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {
    
    Score snakeScore;
    Label scoreLabel;
    
    @Override
    public void start(Stage stage) {
        // start menu
        
        Label label = new Label("Select Minigame");
        
        GridPane games = new GridPane();
        
        Button singlePlayer = new Button("Snake");
        
        games.add(singlePlayer, 2, 0);
        
        VBox menu = new VBox();
        menu.getChildren().add(label);
        menu.getChildren().add(games);
        
        Scene mainMenu = new Scene(menu);
        
        // game 3 instructions 
        VBox game3VBox = new VBox();
        
        Label game3Label = new Label("Make the snake eat the oranges so it grows. If the snake hits itself, the game is over.");
        
        HBox game3HBox = new HBox();
        game3HBox.setSpacing(40);
        Button back3 = new Button("Back");
        Button start3 = new Button ("Start");
        game3HBox.getChildren().add(back3);
        game3HBox.getChildren().add(start3);
        
        game3VBox.getChildren().add(game3Label);
        game3VBox.getChildren().add(game3HBox);
        
        Scene game3Instructions = new Scene(game3VBox);
        
        // game 3 end
        VBox game3EndVBox = new VBox();
        
        scoreLabel = new Label("Your score: ");
        
        HBox game3EndHBox = new HBox();
        game3EndHBox.setSpacing(40);
        Button back3End = new Button("Back");
        Button start3End = new Button ("Play Again");
        game3EndHBox.getChildren().add(back3End);
        game3EndHBox.getChildren().add(start3End);
        
        game3EndVBox.getChildren().add(scoreLabel);
        game3EndVBox.getChildren().add(game3EndHBox);
        
        Scene game3End = new Scene(game3EndVBox);        
        
        
        //snake
        snakeScore = new Score();

        
        singlePlayer.setOnAction((event) -> {
            stage.setScene(game3Instructions);
        });
        
        back3.setOnAction((event) -> {
            stage.setScene(mainMenu);
        });
        
        back3End.setOnAction((event) -> {
            stage.setScene(mainMenu);
        });
        
        start3.setOnAction((event) -> {
            snake(stage, game3End, snakeScore);
        });
        
        start3End.setOnAction((event) -> {
            snake(stage, game3End, snakeScore);
        });
        
        //start
        stage.setTitle("Minigames");
        stage.setScene(mainMenu);
        stage.show();
    }
    
    public void snake(Stage stage, Scene mainMenu, Score score) {
        Canvas canvas = new Canvas(600, 600);
        GraphicsContext drawer = canvas.getGraphicsContext2D();
        
        BorderPane settingSnake = new BorderPane();
        settingSnake.setCenter(canvas);

        Scene snake = new Scene(settingSnake);
        
        stage.setScene(snake);

        SnakeGame snakeGame = new SnakeGame(30, 30, score);
        
        // draws 30 times a second
        new AnimationTimer() {
            private long previous;

            @Override
            public void handle(long presentTime) {
                if (presentTime - previous < 1_000_000_000 / 30) {
                    return;
                }
                previous = presentTime;

                drawer.setFill(Color.BLACK);
                drawer.fillRect(0, 0, 600, 600);

                drawer.setFill(Color.GREEN);

                if (snakeGame.end()) {
                    drawer.setFill(Color.DARKGREEN);
                }

                snakeGame.getSnake().getPieces().stream().forEach(piece -> {
                    drawer.fillRect(piece.getX() * 20, piece.getY() * 20, 20, 20);
                });

                drawer.setFill(Color.ORANGE);
                Fruit orange = snakeGame.getOrange();
                drawer.fillRect(orange.getX() * 20, orange.getY() * 20, 20, 20);
                
                drawer.setFill(Color.YELLOW);
                Fruit lemon = snakeGame.getLemon();
                drawer.fillRect(lemon.getX() * 20, lemon.getY() * 20, 20, 20);

            }
        }.start();

        // updating the game (5 times a second)
        new AnimationTimer() {
            private long previous;

            @Override
            public void handle(long presentTime) {
                if (presentTime - previous < 1_000_000_000 / 5) {
                    return;
                }
                previous = presentTime;

                snakeGame.update();

                if (snakeGame.end()) {
                    scoreLabel.setText("Your score: " + score.getScore());
                    score.resetScore();
                    stop();
                    stage.setScene(mainMenu);
                }
            }
        }.start();

        // handling the events (keys pressed)
        snake.setOnKeyPressed((event) -> {
            switch (event.getCode()) {
                case UP:
                    snakeGame.getSnake().setDirection(Direction.UP);
                    break;
                case DOWN:
                    snakeGame.getSnake().setDirection(Direction.DOWN);
                    break;
                case RIGHT:
                    snakeGame.getSnake().setDirection(Direction.RIGHT);
                    break;
                case LEFT:
                    snakeGame.getSnake().setDirection(Direction.LEFT);
                    break;
                default:
                    break;
            }
        });
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}