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
import java.util.HashMap;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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

public class Scores {
    
    Stage start;
    Scene home;
    Player p;
    
    public Scores(Stage startStage, Scene startScene, Player p){
        start = startStage;
        home = startScene;
        this.p = p;
    };
 
    public void Display(){
        
        BorderPane b = new BorderPane();
        b.setPadding(new Insets(40,25,50,25));
        b.setStyle("-fx-background-color: AliceBlue");
        
        VBox top = new VBox(20);
        Text scores = new Text("Scores");
        scores.setFont(Font.font("Verdana",FontWeight.EXTRA_BOLD,20));
        Text text2 = new Text("See your previous scores for Revision Mode.");
        top.getChildren().addAll(scores,text2);
        top.setAlignment(Pos.CENTER);
        
        VBox center = new VBox();
        TableView<Score> tab = new TableView();
        
        TableColumn sc = new TableColumn("Scores (%)");
        TableColumn diff = new TableColumn("Difficulty");
        TableColumn cat = new TableColumn("Category");
        
        tab.getColumns().addAll(sc,diff,cat);
        sc.setMinWidth(120);
        diff.setMinWidth(120);
        cat.setMinWidth(120);
        tab.setMaxWidth(361);
        tab.setMaxHeight(250);
        Label l = new Label("You have not played any games yet.");
        l.setFont(Font.font("Verdana",FontWeight.BOLD,15));
        tab.setPlaceholder(l);
        tab.setEditable(false);
        HashMap<Double,String> fu  = new HashMap();
        p.getAllScores().keySet().forEach((cc) -> {
            p.getAllScores().get(cc).forEach((d) -> {
                fu.put(d,cc);
            });
        });
        ObservableList<Score> idkwhy = FXCollections.observableArrayList();
        for (Double d: fu.keySet()){
            idkwhy.add(new Score(d,fu.get(d)));
        }
        idkwhy.sort((a,c) -> (int)Math.round(c.tocomp-a.tocomp));
        if (idkwhy.size()>0){
            tab.setItems(idkwhy);
        }
        sc.setCellValueFactory(new PropertyValueFactory<>("s"));
        diff.setCellValueFactory(new PropertyValueFactory<>("diff"));
        cat.setCellValueFactory(new PropertyValueFactory<>("cat"));
        
        center.getChildren().add(tab);
        center.setAlignment(Pos.CENTER);
        VBox bottom = new VBox();
        Button back = new Button("Back");
        back.setMinWidth(160);

        bottom.getChildren().addAll(back);
        bottom.setAlignment(Pos.CENTER);
        b.setTop(top);
        b.setCenter(center);
        b.setBottom(bottom);
        
        Scene scene =  new Scene(b,500,500);
                back.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                start.setScene(home);
                start.show();
                
            }
            
        });
        start.setScene(scene);
        start.show();
    
    }
    
    public class Score {
        public final SimpleStringProperty s;
        public final SimpleStringProperty diff;
        public final SimpleStringProperty cat;
        public final double tocomp;
        public Score(double s, String catdiff){
            tocomp = s;
            String[] ss = catdiff.split("-");
            this.cat = new SimpleStringProperty(ss[1]);
            this.diff = new SimpleStringProperty(ss[0]);
            this.s = new SimpleStringProperty(s+"%");
        }
        
        public String getS(){
            return s.get();
        }
        
        public String getCat(){
            return cat.get();
        }
        
        public String getDiff(){
            return diff.get();
        }
    }
    
    
    

}
