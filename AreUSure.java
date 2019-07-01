/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yeas;

/**
 *
 * @author Zhelyaz
 */
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

public class AreUSure {
    
    public static void Display(int difficulty, int category, Player p, Stage startStage, Scene prevScene, Scene startScene){
        BorderPane b = new BorderPane();
        b.setStyle("-fx-background-color: AliceBlue");
        b.setPadding(new Insets(50,25,50,25));
        
        Text welcome = new Text("Are You Ready?");
        welcome.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 18));
        Text idk = new Text();
        String textt = "Revise ";
        switch (category){
            case 1:
                textt+="Algebra ";
                break;
            case 2:
                textt+="Geometry ";
                break;
            case 3:
                textt+="Calculus ";
                break;
            case 4:
                textt+="a little bit of everything ";
                break;
        }
        switch (difficulty){
            case 1:
                textt+="on Easy difficulty. ";
                break;
            case 2:
                textt+="on Medium difficulty. ";
                break;
            case 3:
                textt+="on Hard difficulty. ";
                break;
        }
        idk.setText(textt);
        Text text2  =new Text("Press 'Start' to begin quiz or go\nback and select a new setting.");
        VBox top = new VBox(20);
        top.getChildren().addAll(welcome,idk,text2);
        top.setAlignment(Pos.CENTER);
        
        VBox center = new VBox(18);
        Button startt = new Button();
        Label s = new Label("Start");
        s.setFont(Font.font("Verdana",FontWeight.EXTRA_BOLD,15));
        startt.setGraphic(s);
        startt.setMinWidth(160);
        startt.setMinHeight(50);
        center.getChildren().addAll(startt);
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
        Button homescreen = new Button("Home");
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
        
        startt.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                RevisionQuiz quiz = new RevisionQuiz(p,startStage,startScene,difficulty,category,(category==4)?15:10,new boolean[]{true,true});
                quiz.nextQuestion(prevScene, startt);
            }
            
        });
        
        startStage.setScene(scene);
        startStage.show();
    }
}
