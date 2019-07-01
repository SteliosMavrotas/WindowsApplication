package yeas;


import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

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

public class DifficultySelectForAlgebraic{
	static Scene scene;
	public static void Display(Player p, Stage startStage,int SceneSelection,int PvPC){
        BorderPane b = new BorderPane();
        MathsStages ms=new MathsStages();
        b.setStyle("-fx-background-color: AliceBlue");
        b.setPadding(new Insets(25,25,50,25));
        
        Text welcome = new Text("Select Difficulty!");
        welcome.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 18));
        VBox top = new VBox(15);
        top.getChildren().addAll(welcome);
        top.setAlignment(Pos.CENTER);
        
        VBox center = new VBox(10);
        Button easy = new Button();
        easy.setOnAction(new EventHandler<ActionEvent>(){
        	public void handle(ActionEvent e){
                    if (PvPC==1){
                        try {
                            ms.MathsStage(1,startStage,0,scene,PvPC,0);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(DifficultySelectForAlgebraic.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(DifficultySelectForAlgebraic.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else{
                        ComputerAccuracySelect.SelectAcc(p, startStage, scene, 1,0);
                    }
        	}
        });
        Label ez = new Label("Easy");
        ez.setFont(Font.font("CambriaMath",FontWeight.EXTRA_BOLD,16));
        easy.setGraphic(ez);
        easy.setMinWidth(160);
        easy.setMinHeight(40);
        Button medium = new Button();
        medium.setOnAction(new EventHandler<ActionEvent>(){
        	public void handle(ActionEvent e){
                    if (PvPC==1){
                        try {
                            ms.MathsStage(2,startStage,0,scene,PvPC,0);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(DifficultySelectForAlgebraic.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(DifficultySelectForAlgebraic.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else{
                        ComputerAccuracySelect.SelectAcc(p, startStage, scene, 2,0);
                    }
        	}
        });
        medium.setMinWidth(160);
        medium.setMinHeight(40);
        Label med = new Label("Medium");
        med.setFont(Font.font("CambriaMath",FontWeight.EXTRA_BOLD,16));
        medium.setGraphic(med);
        Button hard = new Button();
        hard.setOnAction(new EventHandler<ActionEvent>(){
        	public void handle(ActionEvent e){
                    if (PvPC==1){
                        try {
                            ms.MathsStage(3,startStage,0,scene,PvPC,0);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(DifficultySelectForAlgebraic.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(DifficultySelectForAlgebraic.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else{
                        ComputerAccuracySelect.SelectAcc(p, startStage, scene, 3 ,0);
                    }
        	}
        });
        hard.setMinWidth(160);
        hard.setMinHeight(40);
        Label har = new Label("Hard");
        har.setFont(Font.font("CambriaMath",FontWeight.EXTRA_BOLD,16));
        hard.setGraphic(har);
        center.getChildren().addAll(easy,medium,hard);
        center.setAlignment(Pos.CENTER);
        
        VBox bottom = new VBox(15);
        Button back = new Button();
        Label Back=new Label("Back");
        back.setGraphic(Back);
        back.setOnAction(new EventHandler<ActionEvent>(){
        	public void handle(ActionEvent e){
        		if (SceneSelection==1){
        		ChallengeAFriendClass.Display(startStage, p);
        		}else{
        		AgainstTheComputer.Display(startStage, p);
        		}
        	}
        });
        back.setMinWidth(160);
        
        Button LogOut=new Button();
        LogOut.setOnAction(new EventHandler<ActionEvent>(){
        	public void handle(ActionEvent e){
        		StartWindow st=new StartWindow();
        		try {
					st.start(startStage);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
        	}
        });
        Label lgo=new Label("Log Out");
        LogOut.setGraphic(lgo);
        LogOut.setMinWidth(160);
        
        bottom.getChildren().addAll(back,LogOut);
        bottom.setAlignment(Pos.CENTER);
        b.setBottom(bottom);
        b.setTop(top);
        b.setCenter(center);
        scene=new Scene(b,500,500);
        startStage.setScene(scene);
        startStage.show();
   
	}
}