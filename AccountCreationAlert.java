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
public class AccountCreationAlert {
    
    public static void Display(Stage startStage,Scene startScene,Scene accCreationScene, int errortype){
        
        BorderPane b = new BorderPane();
        b.setPadding(new Insets(100,50,50,50));
        b.setStyle("-fx-background-color: AliceBlue");
        
        Text title = new Text("Error!");
        title.setFill(Color.FIREBRICK);
        title.setFont(Font.font("Verdana",FontWeight.EXTRA_BOLD,20));
        Text errormessage = new Text();
        errormessage.setFill(Color.FIREBRICK);
        errormessage.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        boolean showtryagain = true;
        switch(errortype){
            case 0:
                title.setFill(Color.GREEN);
                title.setText("Success!");
                errormessage.setText("Account created successfully!\n\n\n            Log in to play!");
                errormessage.setFill(Color.GREEN);
                showtryagain = false;
                b.setPadding(new Insets(100,50,95,50));
                break;
            case 1:
                errormessage.setText("Username is too long or contains illegal characters.\n\nPlease choose a different one.");
                break;
            case 2:
                errormessage.setText("Username must be at least 3 characters long.\n\nPlease choose a different one");
                break;
            case 3:
                errormessage.setText("Username is already taken.\n\nPlease choose a different one.");
                break;
            case 4:
                errormessage.setText("Password must be at least 8 characters long.\n\nPlease choose a different one. ");
                break;
            case 5:
                errormessage.setText("Passwords do not match!");
                break;
            case 6:
                errormessage.setText("Password is too long or conatins illegal characters.\n\nPlease choose a different one.");
                break;
            case 7:
                errormessage.setText("An error occured while saving your data.\n\nPlease try again later.");
                showtryagain=false;
                break;
        }
         
         VBox error = new VBox(40);
         error.getChildren().addAll(title,errormessage);
         error.setAlignment(Pos.CENTER);
         
         Button home = new Button("Home");
         home.setMinWidth(160);
         Button tryagain = new Button("Back");
         tryagain.setMinWidth(160);
         
         home.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                startStage.setScene(startScene);
                startStage.show();
            }
             
         });
         
         tryagain.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                startStage.setScene(accCreationScene);
                startStage.show();
            }
             
         });
         
         VBox buttons = new VBox(15);
         if (showtryagain){
             buttons.getChildren().addAll(tryagain,home);
         }else{
             buttons.getChildren().add(home);
         }
         
         buttons.setAlignment(Pos.CENTER);
         
         b.setTop(error);
         b.setBottom(buttons);
         
         Scene scene = new Scene(b,500,500);
         startStage.setScene(scene);
         startStage.show();
        
    }
}
