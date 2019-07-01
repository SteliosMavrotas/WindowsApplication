/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yeas;

import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Zhelyaz
 */
public class LoginFailedWindow {
    
    public static void Display(int errortype,Stage startStage,Scene startScene){
        Text title = new Text("Error!");
        title.setFill(Color.FIREBRICK);
        title.setFont(Font.font("Verdana",FontWeight.EXTRA_BOLD,20));
        Text errormessage = new Text();
        errormessage.setFill(Color.FIREBRICK);
        errormessage.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        boolean shownewaccbutton = false;
        switch (errortype){
            case 1:
                errormessage.setText("Invalid username format.\n\nPlease make sure you have entered it correctly.");
                shownewaccbutton = false;
                break;
            case 2:
                errormessage.setText("Incorrect password.\n\nTry again or create a new account.");
                shownewaccbutton = true;
                break;
            case 3:
                errormessage.setText("Username does not exist.\n\nTry again or create a new account.");
                shownewaccbutton = true;
                break;
        }
        Button tryagain = new Button("Back");
        tryagain.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                startStage.setScene(startScene);
                startStage.show(); 
            }
            
        });
        Button createnew = new Button("Create New Account");
        createnew.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                AccountCreationWindow.Display(startStage, startScene,StartWindow.data);
            }
            
        });
        createnew.setMinWidth(160);
        tryagain.setMinWidth(160);
        VBox message = new VBox(40);
        message.getChildren().addAll(title,errormessage);
        message.setAlignment(Pos.CENTER);
        VBox Vlayout=new VBox(15);
        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(100,50,50,50));
        if (shownewaccbutton){
            Vlayout.getChildren().addAll(createnew,tryagain);
        }else{
            Vlayout.getChildren().add(tryagain);
        }
        Vlayout.setAlignment(Pos.CENTER);
        layout.setBottom(Vlayout);
        layout.setTop(message);
	layout.setStyle("-fx-background-color: AliceBlue");
        
	Scene myscene=new Scene(layout,500,500);
	startStage.setScene(myscene);
	startStage.show();
        
        
    }
}
