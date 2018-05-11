
package com.mycompany.minigamecollection;

import java.nio.file.Files;
import java.nio.file.Paths;
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
    
    Score score;
    Label scoreLabel;
    Label winnerLabel;
    
    Label highscorelist;

    @Override
    public void init() throws Exception {
        highscorelist = new Label();
        highscorelist.setText("Highscores: \n");
        
        Files.lines(Paths.get("highscore.txt")).forEach(row -> {
        String[] pieces = row.split(";");
            highscorelist.setText(highscorelist.getText() + "\n" + pieces[0] + " " + pieces[1]);
        });
    }
    

    
    @Override
    public void start(Stage stage) {
        // start menu
        
        Label label = new Label("Select Number of Players");
        
        HBox games = new HBox();
        games.setSpacing(115);
        
        Button singlePlayer = new Button("1");
        Button multiPlayer = new Button("2");
        
        games.getChildren().add(singlePlayer);
        games.getChildren().add(multiPlayer);
        
        VBox menu = new VBox();
        menu.getChildren().add(label);
        menu.getChildren().add(games);
        
        Scene mainMenu = new Scene(menu);
        
        // single player instructions 
        VBox singlePlayerVBox = new VBox();
        
        Label singlePlayerLabel = new Label("Make the snake eat the oranges so it grows. If the snake hits itself, the game is over. Use arrow keys to control.");
        
        HBox singlePlayerHBox = new HBox();
        singlePlayerHBox.setSpacing(40);
        
        Button back1 = new Button("Back");
        Button start1 = new Button("Start");
        Button highscoresButton = new Button("Highscores");
        
        singlePlayerHBox.getChildren().add(back1);
        singlePlayerHBox.getChildren().add(start1);
        singlePlayerHBox.getChildren().add(highscoresButton);
        
        singlePlayerVBox.getChildren().add(singlePlayerLabel);
        singlePlayerVBox.getChildren().add(singlePlayerHBox);
        
        Scene singlePlayerInstructions = new Scene(singlePlayerVBox);
        
        // multiplayer instructions 
        VBox multiPlayerVBox = new VBox();
        
        Label multiPlayerLabel = new Label("Try to defeat the other snake. Use arrows to control green and WASD to control blue.");
        
        HBox multiPlayerHBox = new HBox();
        multiPlayerHBox.setSpacing(40);
        Button back2 = new Button("Back");
        Button start2 = new Button("Start");
        multiPlayerHBox.getChildren().add(back2);
        multiPlayerHBox.getChildren().add(start2);
        
        multiPlayerVBox.getChildren().add(multiPlayerLabel);
        multiPlayerVBox.getChildren().add(multiPlayerHBox);
        
        Scene multiPlayerInstructions = new Scene(multiPlayerVBox);
        
        // single player end
        VBox singlePlayerEndVBox = new VBox();
        
        scoreLabel = new Label("Your score: ");
        
        HBox singlePlayerEndHBox = new HBox();
        singlePlayerEndHBox.setSpacing(40);
        Button back1end = new Button("Back");
        Button start1end = new Button("Play Again");
        singlePlayerEndHBox.getChildren().add(back1end);
        singlePlayerEndHBox.getChildren().add(start1end);
        
        singlePlayerEndVBox.getChildren().add(scoreLabel);
        singlePlayerEndVBox.getChildren().add(singlePlayerEndHBox);
        
        Scene singlePlayerEnd = new Scene(singlePlayerEndVBox);

        // multiplayer end
        VBox multiPlayerEndVBox = new VBox();
        
        winnerLabel = new Label("Winner: ");
        
        HBox multiPlayerEndHBox = new HBox();
        multiPlayerEndHBox.setSpacing(40);
        Button back2end = new Button("Back");
        Button start2end = new Button("Play Again");
        multiPlayerEndHBox.getChildren().add(back2end);
        multiPlayerEndHBox.getChildren().add(start2end);
        
        multiPlayerEndVBox.getChildren().add(winnerLabel);
        multiPlayerEndVBox.getChildren().add(multiPlayerEndHBox);
        
        Scene multiPlayerEnd = new Scene(multiPlayerEndVBox);
        
        //highscore view
        Scene highscoreView = new Scene(highscorelist);
        
        
        //creation of score
        score = new Score();

        // changing scenes
        singlePlayer.setOnAction((event) -> {
            stage.setScene(singlePlayerInstructions);
        });
        
        multiPlayer.setOnAction((event) -> {
            stage.setScene(multiPlayerInstructions);
        });
        
        back1.setOnAction((event) -> {
            stage.setScene(mainMenu);
        });
        
        back2.setOnAction((event) -> {
            stage.setScene(mainMenu);
        });
        
        back1end.setOnAction((event) -> {
            stage.setScene(mainMenu);
        });
        
        back2end.setOnAction((event) -> {
            stage.setScene(mainMenu);
        });
        
        start1.setOnAction((event) -> {
            snake(stage, singlePlayerEnd, false);
        });
        
        start2.setOnAction((event) -> {
            snake(stage, multiPlayerEnd, true);
        });
        
        start1end.setOnAction((event) -> {
            snake(stage, singlePlayerEnd, false);
        });
        
        start2end.setOnAction((event) -> {
            snake(stage, multiPlayerEnd, true);
        });
        
        highscoresButton.setOnAction(event -> {
            stage.setScene(highscoreView);
        });
        
        //start
        stage.setTitle("Snake");
        stage.setScene(mainMenu);
        stage.show();
    }
    
    public void snake(Stage stage, Scene mainMenu, boolean multiplayer) {
        Canvas canvas = new Canvas(600, 600);
        GraphicsContext drawer = canvas.getGraphicsContext2D();
        
        BorderPane settingSnake = new BorderPane();
        settingSnake.setCenter(canvas);

        Scene snake = new Scene(settingSnake);
        
        stage.setScene(snake);

        SnakeGame snakeGame = new SnakeGame(30, 30, score, multiplayer);
        
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
                
                if (multiplayer) {
                    drawer.setFill(Color.BLUE);

                    if (snakeGame.end()) {
                        drawer.setFill(Color.DARKBLUE);
                    }

                    snakeGame.getSnake2().getPieces().stream().forEach(piece -> {
                        drawer.fillRect(piece.getX() * 20, piece.getY() * 20, 20, 20);
                    });
                }
                
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
                    if (multiplayer) {
                        int winner = snakeGame.getWinner();
                        
                        switch (winner) {
                            case 1:
                                winnerLabel.setText("Winner: Green");
                                break;
                            case 2:
                                winnerLabel.setText("Winner: Blue");
                                break;
                            case 3:
                                winnerLabel.setText("Tie");
                                break;
                            default:
                                break;
                        }
                        
                    } else {
                        scoreLabel.setText("Your score: " + score.getScore());
                        score.resetScore();
                    }
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
                case W:
                    if (multiplayer) {
                        snakeGame.getSnake2().setDirection(Direction.UP);
                    }
                    break;
                case S:
                    if (multiplayer) {
                        snakeGame.getSnake2().setDirection(Direction.DOWN);
                    }
                    break;
                case D:
                    if (multiplayer) {
                        snakeGame.getSnake2().setDirection(Direction.RIGHT);
                    }
                    break;
                case A:
                    if (multiplayer) {
                        snakeGame.getSnake2().setDirection(Direction.LEFT);
                    }
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