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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CategorySelect {
    
    
    public static void Display(int difficulty, Player p, Stage startStage,Scene startScene, Scene prevScene){
        
        BorderPane b = new BorderPane();
        b.setPadding(new Insets(25,25,50,25));
        b.setStyle("-fx-background-color: AliceBlue");
        
        Text catsel = new Text("Select Category!");
        catsel.setFont(Font.font("Verdana",FontWeight.EXTRA_BOLD,18));
        Text idk = new Text("Each of the Algebra, Gemetry and Calculus\nquizzes have 10 questions, while Mixed\ncontains 15 questions, 5 of each type.");
        VBox top = new VBox(20);
        top.getChildren().addAll(catsel,idk);
        top.setAlignment(Pos.CENTER);
        
        GridPane center  = new GridPane();
        Button alg = new Button();
        alg.setMinSize(160, 60);
        Label algl = new Label("Algebra");
        algl.setFont(Font.font("Verdana",FontWeight.EXTRA_BOLD,16));
        alg.setGraphic(algl);
        
        Button trig = new Button();
        //Game mode not yet implemented
        trig.setDisable(true);
        trig.setMinSize(160, 60);
        Text trigl = new Text("Geometry");
        trigl.setFont(Font.font("Verdana",FontWeight.EXTRA_BOLD,16));
        trig.setGraphic(trigl);
        
        StackPane ct = new StackPane();
        Text cst = new Text("Coming Soon");
        cst.setFill(Color.FIREBRICK);
        cst.setFont(Font.font("Elephant",FontWeight.BOLD,17));
        cst.setRotate(-26);
        ct.getChildren().addAll(trig,cst);
        
        
        Button calc = new Button();
        //Game mode not yet implemented
        calc.setDisable(true);
        calc.setMinSize(160, 60);
        
        StackPane cp = new StackPane();
        
        Text csc = new Text("Coming Soon");
        csc.setFill(Color.FIREBRICK);
        csc.setFont(Font.font("Elephant",FontWeight.BOLD,17));
        csc.setRotate(-26);
        
        Text calcl = new Text("Calculus");
        calcl.setFont(Font.font("Verdana",FontWeight.EXTRA_BOLD,16));
        calc.setGraphic(calcl);
        cp.getChildren().addAll(calc,csc);
        
        Button mix  =  new Button();
        // Game mode not yet implemented
        mix.setDisable(true);
        Text mixl = new Text("Mixed");
        mixl.setFont(Font.font("Verdana",FontWeight.EXTRA_BOLD,16));
        mix.setGraphic(mixl);
        mix.setMinSize(160, 60);
        
        StackPane cm = new StackPane();
        
        Text csm = new Text("Coming Soon");
        csm.setFill(Color.FIREBRICK);
        csm.setFont(Font.font("Elephant",FontWeight.BOLD,17));
        csm.setRotate(-26);
        cm.getChildren().addAll(mix,csm);
        
        center.add(alg, 0, 0);
        center.add(ct,0,1);
        center.add(cp,1,0);
        center.add(cm,1,1);
        center.setVgap(15);
        center.setHgap(15);
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
        
        alg.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                AreUSure.Display(difficulty, 1, p, startStage, scene, startScene);
            }
            
        });
       
        
///////////// Other game modes not yet implemented ////////////////////        
        
        trig.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                AreUSure.Display(difficulty, 2, p, startStage, scene, startScene);
            }
            
        });
        calc.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                AreUSure.Display(difficulty, 3, p, startStage, scene, startScene);
            }
            
        });
        mix.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                AreUSure.Display(difficulty, 4, p, startStage, scene, startScene);
            }
            
        });
        
        startStage.setScene(scene);
        startStage.show();
        
        
    }
}
