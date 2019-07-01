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
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Zhelyaz
 */
public class AccountCreationWindow {
    
    public static void Display(Stage startStage,Scene startScene,PlayerDatabase pp){
        
        BorderPane b =  new BorderPane();
        b.setPadding(new Insets(25,20,50,20));
        b.setStyle("-fx-background-color: AliceBlue");
        
        Text title = new Text("Create New Account");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        Text instructions = new Text("Choose a username and a password.\nThey must be no more than 20 characters long, and the\npassword has to be more than 8 characters long.\nYou can use letters (uppercase and lowercase), numbers, and\nany of the following characters: ! ? @ # $ % ^ & * ( ) _ + - =.\nOther special characters are not allowed.");
        VBox top =  new VBox(25);
        top.getChildren().addAll(title,instructions);
        top.setAlignment(Pos.CENTER);
        
        GridPane center =  new GridPane();
        center.setVgap(10);
        center.setHgap(10);
        Label uname = new Label("Username");
        uname.setFont(Font.font("Verdana",FontWeight.BOLD,14));
        Label pass = new Label("Password");
        pass.setFont(Font.font("Verdana",FontWeight.BOLD,14));
        Label repeatpass = new Label("Repeat Password");
        repeatpass.setFont(Font.font("Verdana",FontWeight.BOLD,14));
        TextField Uname= new TextField();
        TextField Pass = new TextField();
        TextField RepPass = new TextField();
        Uname.setMinWidth(216);
        Pass.setMinWidth(216);
        RepPass.setMinWidth(216);
        center.add(uname,0,0);
        center.add(pass,0,1);
        center.add(repeatpass,0,2);
        center.add(Uname,2,0);
        center.add(Pass,2,1);
        center.add(RepPass,2,2);
        center.setAlignment(Pos.CENTER);
        
        Text errormessage = new Text();
        errormessage.setText("Fields cannot be left empty.");
        errormessage.setFont(Font.font("Verdana",FontWeight.BOLD,13));
        errormessage.setFill(Color.ALICEBLUE);
        Text whitespace = new Text();
        
        Button createacc = new Button("Create Account");
        createacc.setMinWidth(160);
        Button home = new Button("Home");
        home.setMinWidth(160);
        VBox bottom = new VBox(15);
        bottom.getChildren().addAll(errormessage,createacc,home);
        bottom.setAlignment(Pos.CENTER);
        
        home.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                startStage.setScene(startScene);
                startStage.show();
            }
        });
        
        
        
        b.setCenter(center);
        b.setTop(top);
        b.setBottom(bottom);
        
        Scene scene = new Scene(b,500,500);
        
        createacc.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                String un = Uname.getText();
                String pas1 = Pass.getText();
                String pas2 = RepPass.getText();
                if (un.length()==0||pas1.length()==0||pas2.length()==0){
                    errormessage.setFill(Color.FIREBRICK);
                }else{
                    AccountCreationAlert.Display(startStage, startScene, scene, pp.createNewUser(un, pas1, pas2));
                }
            }
            
        });
        startStage.setScene(scene);
        startStage.show();
    }
    
}
