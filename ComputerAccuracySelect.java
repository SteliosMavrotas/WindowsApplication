/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yeas;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;

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
public class ComputerAccuracySelect {
    
    private static int accuracy = 50;
    
    public static void SelectAcc(Player p, Stage startStage, Scene startScene, int difficulty, int mode){
        
        BorderPane b = new BorderPane();
        b.setPadding(new Insets(40,25,50,25));
        b.setStyle("-fx-background-color: AliceBlue");
        
        VBox top = new VBox(25);
        Text accsel = new Text("Select Computer Accuracy");
        accsel.setFont(Font.font("Verdana",FontWeight.BOLD,19));
        Text descr = new Text("Select how often the computer should guess correctly.");
        Text descr2 = new Text("Toggle the up and down buttons to select accuracy");
        
        top.getChildren().addAll(accsel,new Text(),descr,descr2);
        top.setAlignment(Pos.CENTER);
        
        VBox center = new VBox(15);
        Button higher = new Button("Up");
        higher.setMinWidth(160);
        Button down = new Button("Down");
        down.setMinWidth(160);
        TextField acc = new TextField("50%");
        acc.setMaxWidth(160);
        acc.setEditable(false);
        higher.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                if (accuracy<100){
                    accuracy+=5;
                    acc.setText(accuracy+"%");
                }
            }
            
        });
        down.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                if (accuracy>25){
                    accuracy-=5;
                    acc.setText(accuracy+"%");
                }
            }
            
        });
        
        center.getChildren().addAll(higher,acc,down);
        center.setAlignment(Pos.CENTER);
        
        VBox bottom = new VBox(15);
        Button cont = new Button("Continue");
        cont.setMinWidth(160);
        Button back = new Button("Back");
        back.setMinWidth(160);
        cont.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                if (mode==0){
                    MathsStages ss = new MathsStages();
                    try {
                        ss.MathsStage(difficulty, startStage, 0, startScene, 0, accuracy/(double)100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ComputerAccuracySelect.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(ComputerAccuracySelect.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    KnowledgeStage ks = new KnowledgeStage();
                    try {
                        ks.displayKnowledge(startStage, 0, accuracy/(double)100);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(ComputerAccuracySelect.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                accuracy = 50;
            }
            
        });
        back.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                startStage.setScene(startScene);
                startStage.show();
                accuracy = 50;
            }
            
        });
        bottom.getChildren().addAll(cont,back);
        bottom.setAlignment(Pos.CENTER);
        
        b.setTop(top);
        b.setCenter(center);
        b.setBottom(bottom);
        
        Scene scene =  new Scene(b,500,500);
        
        startStage.setScene(scene);
        startStage.show();
        
        
    }
}
