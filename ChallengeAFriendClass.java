package yeas;

import java.io.FileNotFoundException;
import java.nio.file.attribute.PosixFilePermission;
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

public class ChallengeAFriendClass {
	static Label Description;
	static Label Mathematics;
	static Label Questions;
	static Button Maths;
	static Button Quests;
	static VBox Top;
	static VBox Center;
	static VBox Bottom;
	static BorderPane b;
	static Label Descri;
	static Scene ChallengeScene;
	static Button GoBack;
	static Label Back;
	static Label Category;
	
	public static void Display(Stage startStage,Player p){
		b=new BorderPane();
		b.setStyle("-fx-background-color: AliceBlue");
		b.setPadding(new Insets(50,25,50,25));
		
		Category=new Label("Select Category!");
		Category.setFont(Font.font("Vardana",FontWeight.EXTRA_BOLD,13));
		Back=new Label("Back");
		GoBack=new Button();
		GoBack.setMinHeight(40);
		GoBack.setMinWidth(10);
		GoBack.setGraphic(Back);
		GoBack.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent a){
				PlayerWelcomeWindow.Display(p, startStage, ChallengeScene);
			}
		});
		
		ChallengeScene=new Scene(b,500,500);
		
		Mathematics=new Label("Mathematics");
		Questions=new Label("Questions");
		Mathematics.setFont(Font.font("Verdana",FontWeight.BOLD,13));
		Questions.setFont(Font.font("Verdana",FontWeight.BOLD,13));
		
		Maths=new Button();
		Maths.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				DifficultySelectForAlgebraic.Display(p, startStage,1,1);
			}
		});
		Quests=new Button();
		Quests.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				KnowledgeStage ks=new KnowledgeStage();
                            try {
                                ks.displayKnowledge(startStage,1,0);
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(ChallengeAFriendClass.class.getName()).log(Level.SEVERE, null, ex);
                            }
				
			}
		});
		Maths.setGraphic(Mathematics);
		Quests.setGraphic(Questions);
		Maths.setMinHeight(40);
		Maths.setMinWidth(160);
		Quests.setMinHeight(40);
		Quests.setMinWidth(160);
		
		Descri=new Label("When the game starts the players have to start and the computer will display which player needs to play. Follow the instructions and get the Score in the end. Enjoy!");
		Descri.setWrapText(true);
		
		Center=new VBox(30);
		Top=new VBox(30);
		Bottom= new VBox(30);
		
		Description=new Label();
		Description.setText("By pressing Mathematics or Questions you will enter another window, where you will have a type of question, and a box to answer.Max time allowed per question:20s. Your results will be displayed in a bar graph in the end of the game");
		Description.setWrapText(true);
		
		Top.getChildren().addAll(Category,Description);
		Center.getChildren().addAll(Maths,Quests);
		Bottom.getChildren().addAll(Descri,GoBack);
		Top.setAlignment(Pos.CENTER);
		Bottom.setAlignment(Pos.CENTER);
		Center.setAlignment(Pos.CENTER);
		
		
		b.setTop(Top);
		b.setCenter(Center);
		b.setBottom(Bottom);
		
		startStage.setScene(ChallengeScene);
		startStage.show();
		
		}

}
