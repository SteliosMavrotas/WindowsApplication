package yeas;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class StartWindow extends Application{
        static PlayerDatabase data;
	

	
        @Override
	public void start(Stage primaryStage) throws Exception {
            
            data = new PlayerDatabase();
            
            Stage window=new Stage();
            window.setTitle("Quiz App");
            window.setMaxHeight(600);
            window.setMaxWidth(500);

            BorderPane p = new BorderPane();
            p.setPadding(new Insets(65,20,50,20));
            p.setStyle("-fx-background-color: AliceBlue");
            if (!data.dataOK()){
                VBox top = new VBox();
                Text Description=new  Text("Welcome");
                Text Description2=new Text("-------------------------------------------------------------------");
                Text Description3 = new Text("This application was made by Zhelyaz and Stelios, UCL BENG");
                Text somewhitespace = new Text();
                Text somemorewhitespace = new Text("");
                Description.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD, 28));
                top.getChildren().addAll(Description,somewhitespace,Description2,Description3,somemorewhitespace);
                top.setAlignment(Pos.CENTER);
                
                VBox center = new VBox(10);
                Text error1 = new Text("Sorry, there was an error in acessing the player database.");
                Text error2 = new Text("Log in unavailable at the moment.");
                Text yes = new Text("You can play in revision mode, but your score will not be saved.");
                Text yes2 = new Text("Play without logging in?");
                center.getChildren().addAll(error1,error2,yes,yes2);
                center.setAlignment(Pos.CENTER);
                
                VBox bottom = new VBox(15);
                Button play = new Button("Play");
                play.setMinWidth(160);
                Button quit = new Button("Quit");
                quit.setMinWidth(160);
                bottom.getChildren().addAll(play,quit);
                bottom.setAlignment(Pos.CENTER);
                p.setTop(top);
                p.setCenter(center);
                p.setBottom(bottom);
                
                
                Scene idk = new Scene(p,500,500);
                
                play.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event) {
                        RevisionQuiz rev = new RevisionQuiz(null,window,idk,1,1,10,new boolean[] {false,false});
                        rev.nextQuestion(idk, play);
                    }
                    
                });
                quit.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event) {
                        window.close();
                    }
                    
                });
                window.setScene(idk);
                window.show();
                
            }else {
                VBox top = new VBox();
                Text Description=new  Text("Welcome");
                Text Description2=new Text("----------------------------------------------------------");
                Description2.setFont(Font.font("Verdana",FontWeight.BOLD,14));
                Text Description3 = new Text("This app was made by Zhelyaz and Stelios, UCL BENG");
                Description3.setFont(Font.font("Verdana",FontWeight.BOLD,13));
                Text somewhitespace = new Text();
                Text somemorewhitespace = new Text("");
                Description.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD, 28));
                top.getChildren().addAll(Description,somewhitespace,Description2,Description3,somemorewhitespace);
                top.setAlignment(Pos.CENTER);

                GridPane center =  new GridPane();
                center.setVgap(10);
                center.setHgap(10);
                center.setAlignment(Pos.CENTER);
                Label uname = new Label("Username");
                uname.setFont(Font.font("Verdana",FontWeight.BOLD,14));
                Label pass = new Label("Password");
                pass.setFont(Font.font("Verdana",FontWeight.BOLD,14));
                TextField Uname= new TextField();
                PasswordField Pass= new PasswordField();
                Uname.setMinWidth(225);
                Pass.setMinWidth(225);
                center.add(uname, 0, 1);
                center.add(Uname,1,1);
                center.add(pass,0,2);
                center.add(Pass,1,2);


                VBox bottom=new VBox(15);
                bottom.setPrefWidth(160);
                Button LoginButton = new Button("Log in");
                Button NewAccButton = new Button("Create New Account");
                LoginButton.setMinWidth(bottom.getPrefWidth());
                NewAccButton.setMinWidth(bottom.getPrefWidth());
                Text errormessage = new Text();
                errormessage.setText("Fields cannot be left empty.");
                errormessage.setFont(Font.font("Verdana",FontWeight.BOLD,13));
                errormessage.setFill(Color.ALICEBLUE);
                Text whitespace = new Text();
                bottom.getChildren().addAll(errormessage,whitespace,NewAccButton,LoginButton);
                bottom.setAlignment(Pos.TOP_CENTER);



                p.setCenter(center);
                p.setTop(top);
                p.setBottom(bottom);

                Scene StartScene=new Scene(p,500,500);
                LoginButton.setOnAction(new EventHandler <ActionEvent>(){
                        @Override
                        public void handle(ActionEvent event){
                            String Username=Uname.getText();
                            String Password=Pass.getText();
                            if (Username.length()==0||Password.length()==0){
                                errormessage.setFill(Color.FIREBRICK);
                            }else if (data.login(Username, Password)!=0){
                                errormessage.setFill(Color.ALICEBLUE);
                                LoginFailedWindow.Display(data.login(Username, Password),window,StartScene);
                            }else{
                                errormessage.setFill(Color.ALICEBLUE);
                                Player player = new Player(Username,data);
                                Uname.setText("");
                                Pass.setText("");
                                if(data.scoreOK()){
                                    PlayerWelcomeWindow.Display(player, window, StartScene);
                                }else{
                                    ScoresError.Display(player, window, StartScene);
                                }
                            }

                        }
                });

                NewAccButton.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event) {
                        errormessage.setFill(Color.ALICEBLUE);
                        AccountCreationWindow.Display(window,StartScene,data);
                    }

                });

                window.setScene(StartScene);
                window.show();
            }	
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}

}
