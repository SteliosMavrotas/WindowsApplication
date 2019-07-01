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
public class PlayerWelcomeWindow {
    
    public static void Display(Player p, Stage startStage, Scene startScene){
        
        BorderPane b = new BorderPane();
        b.setStyle("-fx-background-color: AliceBlue");
        b.setPadding(new Insets(50,25,50,25));
        
        Text welcome = new Text("Welcome, "+p.username+"!");
        welcome.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 20));
        Text idk = new Text("Choose a game mode.");
        idk.setFont(Font.font("Verdana",FontWeight.BOLD,15));
        VBox top = new VBox(30);
        top.getChildren().addAll(welcome,idk);
        top.setAlignment(Pos.CENTER);
        
        VBox center = new VBox(15);
        Button revise = new Button();
        Label rev =  new Label("Revision");
        rev.setFont(Font.font("Verdana",FontWeight.BOLD,13));
        revise.setGraphic(rev);
        revise.setMinWidth(160);
        revise.setMinHeight(40);
        Button pvp = new Button();
        Label vp =  new Label("Challenge a Friend");
        vp.setFont(Font.font("Verdana",FontWeight.BOLD,13));
        pvp.setGraphic(vp);
        pvp.setMinWidth(160);
        pvp.setMinHeight(40);
        Button pvc = new Button();
        Label vc =  new Label("Against Computer");
        vc.setFont(Font.font("Verdana",FontWeight.BOLD,13));
        pvc.setGraphic(vc);
        pvc.setMinWidth(160);
        pvc.setMinHeight(40);
        center.getChildren().addAll(revise,pvp,pvc);
        center.setAlignment(Pos.CENTER);
        
        VBox bottom = new VBox(15);
        Button homescreen = new Button("Log out");
        homescreen.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                startStage.setScene(startScene);
                startStage.show();
            }
            
        });
        homescreen.setMinWidth(160);
        Button scores = new Button("Scores");
        scores.setMinWidth(160);
        bottom.getChildren().addAll(scores,homescreen);
        bottom.setAlignment(Pos.CENTER);
        
        b.setTop(top);
        b.setCenter(center);
        b.setBottom(bottom);
        
        Scene scene = new Scene(b,500,500);
        
        scores.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Scores s =  new Scores(startStage,scene,p);
                s.Display();
            }
            
        });
        revise.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                DifficultySelect.Display(p, startStage, startScene, scene);
            }
            
        });
        pvp.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                ChallengeAFriendClass.Display(startStage, p);
            }
            
        });
        pvc.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                AgainstTheComputer.Display(startStage, p);
            }
            
        });
        
        startStage.setScene(scene);
        startStage.show();
        
        
    }
}
