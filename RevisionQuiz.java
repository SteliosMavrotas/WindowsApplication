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

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RevisionQuiz {
    
    private final int totquestions;
    private double score;
    private int qnumber;
    private final QuestionBank qb;
    private ArrayList<Scene> previous;
    private final Player p;
    private Stage stage;
    private Scene homescene;
    private int diff;
    private int cat;
    private boolean skiponce;
    private final boolean[] mode;
    
    public RevisionQuiz(Player player,Stage startStage, Scene startScene,int difficulty, int category, int numquestions,boolean[] mode){
        p = player;
        totquestions = numquestions;
        diff = difficulty;
        cat = category;
        score = 0;
        qnumber = 0;
        qb = new QuestionBank(numquestions,category,difficulty);
        stage = startStage;
        homescene = startScene;
        skiponce =true;
        this.mode = mode;
    }
    
    
    public void nextQuestion(Scene prevScene,Button prevnext){
        
        qnumber +=1;
        Question q = qb.next();
        
        BorderPane b = new BorderPane();
        b.setPadding(new Insets(50,25,50,25));
        b.setStyle("-fx-background-color: AliceBlue");
        
        VBox top  = new VBox(qnumber==1?15:25);
        Text qnn = new Text("Question "+qnumber);
        qnn.setFont(Font.font("Verdana",FontWeight.EXTRA_BOLD,19));
        Text qq = new Text(q.toString());
        qq.setFont(Font.font("CambriaMath",FontWeight.BOLD,16));
        top.getChildren().addAll(qnn,qq);
        top.setAlignment(Pos.CENTER);
        
        VBox center = new VBox(10);
        Text instr = new Text((qnumber==1)?"Enter the roots below, separated by commas (e.g. 5,3)":"");
        Text instr2 = new Text(qnumber==1?"In case of a repeated root, enter it twice (e.g. 2,2).":"");
        Text instr3 = new Text(qnumber==1?"You can skip questions, but you will not get points for them.":"");
        GridPane gg =  new GridPane();
        Text a = new Text("Answer:");
        TextField anss = new TextField();
        Text placeholder = new Text();
        gg.add(placeholder,0,0);
        gg.add(a,0,1);
        gg.add(anss, 1, 1);
        if (qnumber==1){
            center.getChildren().addAll(instr,instr2,instr3,gg);
        }else{
            center.getChildren().addAll(instr,gg);
        }
        gg.setVgap(10);
        gg.setHgap(10);
        gg.setAlignment(Pos.CENTER);
        center.setAlignment(Pos.CENTER);

        VBox bottom  = new VBox(15);
        Button check = new Button("Check");
        check.setMinWidth(160);
        Button cont = new Button("Continue");
        cont.setMinWidth(160);
        Button skip = new Button("Skip");
        skip.setMinWidth(160);
        Button quit = new Button("Quit");
        quit.setMinWidth(160);
        Text prompt = new Text();
        prompt.setFont(Font.font("Verdana",FontWeight.BOLD,14));
        Button prev = new Button("Previous");
        prev.setMinWidth(103);
        Button next = new Button("Next");
        next.setMinWidth(105);
        bottom.setAlignment(Pos.CENTER);
        
        check.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                String ans = anss.getText();
                if (ans.equals("")){
                    prompt.setText("Field empty.");
                    prompt.setFill(Color.FIREBRICK);
                    prompt.setFont(Font.font("Verdana",FontWeight.BOLD,14));
                }else{
                    double pts = q.answerCorrect(ans.split(","));
                    anss.setEditable(false);
                    if (pts==1){
                        prompt.setText("Correct! 1/1");
                        prompt.setFill(Color.GREEN);
                        prompt.setFont(Font.font("Verdana",FontWeight.BOLD,14));
                        VBox bot2 = new VBox(15);
                        bot2.getChildren().addAll(prompt,cont,new Text(),quit);
                        b.setBottom(bot2);
                        bot2.setAlignment(Pos.CENTER);
                        score++;
                        skiponce = true;
                    }else{
                        
                        prompt.setText((pts==0?"Wrong!":"Almost!")+" The answer was "+q.getAnswer()+".  "+(pts==0?"0":pts)+"/1");
                        prompt.setFill(pts==0?Color.FIREBRICK:Color.GOLDENROD);
                        score += pts;
                        VBox bot2 = new VBox(15);
                        bot2.getChildren().addAll(prompt,cont,new Text(),quit);
                        prompt.setFont(Font.font("Verdana",FontWeight.BOLD,14));
                        bot2.setAlignment(Pos.CENTER);
                        b.setBottom(bot2);
                        skiponce = true;
                    }
                }
            }
        });
        
        
        HBox bott = new HBox(30);
        
        Text wtf1 = new Text();
        Text wtf2 = new Text("                         ");
        b.setTop(top);
        b.setCenter(center);
        
        if (qnumber!=1){
            bott.getChildren().addAll(prev,quit,wtf2);
        }else{
            bott.getChildren().add(quit);
        }
        bott.setAlignment(Pos.CENTER);
        bottom.getChildren().addAll(prompt,check,skip,bott);
        b.setBottom(bottom);
        
        prev.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(prevScene);
                stage.show();
            }
            
        });
        
        Scene scene = new Scene(b,500,500);
        
        prevnext.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {
                stage.setScene(scene);
            }   
        });
        
        
        
        quit.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                BorderPane bq = new BorderPane();
                bq.setPadding(new Insets(125,25,125,25));
                bq.setStyle("-fx-background-color: AliceBlue");
                
                Text rly = new Text("Are you sure you want to quit?\n\nYour score will not be saved.");
                rly.setFont(Font.font("Verdana",FontWeight.EXTRA_BOLD,18));
                VBox e = new VBox();
                e.getChildren().add(rly);
                e.setAlignment(Pos.CENTER);
                bq.setTop(e);
                
                
                HBox yn = new HBox(25);
                Button y = new Button("Yes");
                Button n = new Button("No");
                y.setMinWidth(100);
                n.setMinWidth(100);
                y.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event) {
                        stage.setScene(homescene);
                        stage.show();
                    }
                    
                });
                n.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event) {
                        prompt.setFill(Color.ALICEBLUE);
                        stage.setScene(scene);
                        stage.show();
                    }
                });
                yn.setAlignment(Pos.CENTER);
                yn.getChildren().addAll(y,n);
                bq.setBottom(yn);
                Scene idkanymore = new Scene(bq,500,500);
                stage.setScene(idkanymore);
                stage.show();
            }
        });
        
        cont.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                if (qnumber<totquestions){
                    
                    VBox bfinal = new VBox(50);
                    HBox prevnext = new HBox(150);
                    if (qnumber==1){
                        prevnext.getChildren().add(next);
                    }else{
                        prevnext.getChildren().addAll(prev,next);
                    }
                    prevnext.setAlignment(Pos.CENTER);
                    bfinal.getChildren().addAll(prompt,prevnext);
                    bfinal.setAlignment(Pos.CENTER);
                    b.setBottom(bfinal);
                    nextQuestion(scene,next);
                }else{
                    VBox bfinal = new VBox(50);
                    HBox prevnext = new HBox(150);
                    prevnext.getChildren().addAll(prev,next);
                    prevnext.setAlignment(Pos.CENTER);
                    bfinal.getChildren().addAll(prompt,prevnext);
                    bfinal.setAlignment(Pos.CENTER);
                    b.setBottom(bfinal);
                    end(next,scene);
                }
            }
            
        });
        
        skip.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                if (skiponce){
                    prompt.setText("Are you sure? Press again to confirm.");
                    prompt.setFill(Color.FIREBRICK);
                    prompt.setFont(Font.font("Verdana",FontWeight.BOLD,14));
                    skiponce = false;
                }else{
                    anss.setEditable(false);
                    skiponce = true; 
                    prompt.setText("Skipped! The answer was "+q.getAnswer()+".");
                    prompt.setFont(Font.font("Verdana",FontWeight.BOLD,14));
                    VBox bbb  =new VBox(15);
                    bbb.getChildren().addAll(prompt,cont,new Text(), quit);
                    bbb.setAlignment(Pos.CENTER);
                    b.setBottom(bbb);
                }   
            }  
        });
        stage.setScene(scene);
        stage.show();   
    }
    
    public void end(Button prevnext,Scene prevscene){
        
        
        double scorepercent = (double)Math.abs(1000*score/totquestions)/10;
        if(mode[0]&&mode[1]){
            p.callDatabase().saveAll();
        }
        BorderPane b = new BorderPane();
        b.setPadding(new Insets(100,25,50,25));
        b.setStyle("-fx-background-color: AliceBlue");
        
        VBox justsoicancenter = new VBox(mode[0]?40:60);
        Text yeas = new Text("Your final score is " + score +"/"+totquestions+", or");
        yeas.setFont(Font.font("Verdana",FontWeight.BOLD,19));
        Text YEAS = new Text(""+ scorepercent+"% ");
        YEAS.setFont(Font.font("Verdana",FontWeight.EXTRA_BOLD,mode[0]?22:26));
        
        justsoicancenter.getChildren().addAll(yeas,YEAS);
        justsoicancenter.setAlignment(Pos.CENTER);
        VBox center = new VBox(15);
        if(mode[0]&&mode[1]){
            double bb = p.getBest(diff,cat);
            p.recordScore(scorepercent,diff,cat);
            Text idk = new Text();
            if (bb<0){
                idk.setText("This is your first game in this mode.");
            }else if (bb==scorepercent){
                idk.setText("This is the same as your previous best!");
            }else if(bb>scorepercent){
                idk.setText("You can do better! Your best score is "+bb+"%.");
            }else{
                idk.setText("Congratulations! You beat your previous best ("+bb+"%)!");
            }
            Text idk2 = new Text("Your average for this mode is "+Math.round(100*p.getAverage(diff,cat))/100+"%.");
            center.getChildren().addAll(idk,idk2);
            center.setAlignment(Pos.CENTER);
        }
        VBox bot = new VBox(15);
        Button home = new Button("Home");
        Button review = new Button("Review");
        review.setMinWidth(160);
        review.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(prevscene);
                stage.show();
            }
            
        });
        home.setMinWidth(160);
        home.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                if(mode[0]){
                    PlayerWelcomeWindow.Display(p, stage, homescene);
                }else{
                    stage.setScene(homescene);
                    stage.show();
                }
            }
            
        });
        bot.getChildren().addAll(review,home);
        bot.setAlignment(Pos.CENTER);
        
        b.setTop(justsoicancenter);
        b.setCenter(center);
        b.setBottom(bot);
        
        Scene yeeaaas = new Scene(b,500,500);
        prevnext.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(yeeaaas);
                stage.show();
            } 
        });
        
        stage.setScene(yeeaaas);
        stage.show();
        
        
        
    }
    
}
