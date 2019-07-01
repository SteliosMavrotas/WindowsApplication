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
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class ScoresError {
    
    public static void Display(Player p,Stage startStage, Scene startScene){
        
        BorderPane b = new BorderPane();
        b.setPadding(new Insets(65,20,50,20));
        b.setStyle("-fx-background-color: AliceBlue");
        
        VBox top = new VBox();
        Text hey = new Text("Hey there, "+p.username+"!");
        hey.setFont(Font.font("Verdana",FontWeight.EXTRA_BOLD,20));
        top.getChildren().add(hey);
        top.setAlignment(Pos.CENTER);
        
        VBox center =  new VBox(10);
        Text text1 =  new Text("Unfortunately, an error occurred while retreiving your scores.");
        Text text2 = new Text("All game modes are still functional, though.");
        Text text3 = new Text("You can play, but your scores will not be saved.");
        Text text4 = new Text("Your previous scores are also unavailable.");
        center.getChildren().addAll(text1,text2,text3,text4);
        center.setAlignment(Pos.CENTER);
        
        VBox bottom = new VBox(15);
        Button ok = new Button("OK");
        ok.setMinWidth(160);
        Button quit = new Button("Quit");
        quit.setMinWidth(160);
        
        bottom.getChildren().addAll(ok,quit);
        bottom.setAlignment(Pos.CENTER);
        
        ok.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                PlayerWelcomeWindow.Display(p, startStage, startScene);
            }
            
        });
        quit.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                startStage.close();
            }
            
        });
        b.setTop(top);
        b.setCenter(center);
        b.setBottom(bottom);
        
        Scene scene = new Scene(b,500,500);
        startStage.setScene(scene);
        startStage.show();
        
        
        
       
    }
}
