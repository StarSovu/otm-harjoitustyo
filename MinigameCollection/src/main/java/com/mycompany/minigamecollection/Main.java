
package com.mycompany.minigamecollection;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {
    
    @Override
    public void start(Stage stage) {
        // start menu
        
        Label label = new Label("Select Minigame");
        
        GridPane games = new GridPane();
        
        Button game1 = new Button("Collect Food");
        Button game2 = new Button("Protect the Town");
        
        games.add(game1, 0, 0);
        games.add(game2, 1, 0);
        
        VBox menu = new VBox();
        menu.getChildren().add(label);
        menu.getChildren().add(games);
        
        Scene mainMenu = new Scene(menu);
        
        // game 1 instructions 
        VBox game1VBox = new VBox();
        
        Label game1Label = new Label("Choose healthy food, avoid unhealthy");
        
        HBox game1HBox = new HBox();
        game1HBox.setSpacing(40);
        Button back1 = new Button("Back");
        Button start1 = new Button ("Start");
        game1HBox.getChildren().add(back1);
        game1HBox.getChildren().add(start1);
        
        game1VBox.getChildren().add(game1Label);
        game1VBox.getChildren().add(game1HBox);
        
        Scene game1Instructions = new Scene(game1VBox);
        
        // game 2 instructions 
        VBox game2VBox = new VBox();
        
        Label game2Label = new Label("Don't let them destroy all walls");
        Button back2 = new Button("Back");
        game2VBox.getChildren().add(game2Label);
        game2VBox.getChildren().add(back2);
        
        Scene game2Instructions = new Scene(game2VBox);
        
        // select the food
        FoodCollectingScore score = new FoodCollectingScore();
        
        Pane foodCollectScreen = new Pane();
        foodCollectScreen.setPrefSize(480, 360);
        
        Scene foodCollection = new Scene(foodCollectScreen);
        
        
        
        //changing scenes
        game1.setOnAction((event) -> {
            stage.setScene(game1Instructions);
        });
        
        game2.setOnAction((event) -> {
            stage.setScene(game2Instructions);
        });
        
        back1.setOnAction((event) -> {
            stage.setScene(mainMenu);
        });
        
        back2.setOnAction((event) -> {
            stage.setScene(mainMenu);
        });
        
        start1.setOnAction((event) -> {
            stage.setScene(foodCollection);
        });
        
        //start
        stage.setTitle("Minigames");
        stage.setScene(mainMenu);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}