/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yeas;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;



/**
 *
 * @author Zhelyaz
 */



public class DifficultySelect {
    
    public static void Display(Player p, Stage startStage, Scene startScene, Scene prevScene){
        BorderPane b = new BorderPane();
        b.setStyle("-fx-background-color: AliceBlue");
        b.setPadding(new Insets(25,25,50,25));
        
        Text welcome = new Text("Select Difficulty!");
        welcome.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 18));
        Text idk = new Text();
        idk.setText("Choose how difficult the questions should be.\nQuestions in easy mode ask you things like finding the\nroots of a qudratic equation and evaluating simple\nderivatives, whereas in hard mode you will face things\nlike cubic equations and integration by parts.");
        
        VBox top = new VBox(15);
        top.getChildren().addAll(welcome,idk);
        top.setAlignment(Pos.CENTER);
        
        VBox center = new VBox(10);
        Button easy = new Button();
        Label ez = new Label("Easy");
        ez.setFont(Font.font("CambriaMath",FontWeight.EXTRA_BOLD,16));
        easy.setGraphic(ez);
        easy.setMinWidth(160);
        easy.setMinHeight(40);
        Button medium = new Button();
        medium.setMinWidth(160);
        medium.setMinHeight(40);
        Label med = new Label("Medium");
        med.setFont(Font.font("CambriaMath",FontWeight.EXTRA_BOLD,16));
        medium.setGraphic(med);
        Button hard = new Button();
        hard.setMinWidth(160);
        hard.setMinHeight(40);
        Label har = new Label("Hard");
        har.setFont(Font.font("CambriaMath",FontWeight.EXTRA_BOLD,16));
        hard.setGraphic(har);
        center.getChildren().addAll(easy,medium,hard);
        center.setAlignment(Pos.CENTER);
        
        VBox bottom = new VBox(15);
        Button back = new Button("Back");
        back.setMinWidth(160);
        back.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                startStage.setScene(prevScene);
                startStage.show();
            }
            
        });
        Button homescreen = new Button("Log out");
        homescreen.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                startStage.setScene(startScene);
                startStage.show();
            }
            
        });
        homescreen.setMinWidth(160);
        bottom.getChildren().addAll(back,homescreen);
        bottom.setAlignment(Pos.CENTER);
        
        b.setTop(top);
        b.setCenter(center);
        b.setBottom(bottom);
        
        Scene scene = new Scene(b,500,500);
        
        easy.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                CategorySelect.Display(1, p, startStage, startScene, scene);
            }
            
        });
        medium.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                CategorySelect.Display(2, p, startStage, startScene, scene);
                
            }
            
        });
        hard.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                CategorySelect.Display(3, p, startStage, startScene, scene);
            }
            
        });
        startStage.setScene(scene);
        startStage.show();
    }
}
