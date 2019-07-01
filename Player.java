/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yeas;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Zhelyaz
 */
public class Player {

    
    
    final String username;
    private final HashMap<String,ArrayList<Double>> scores;
    private final PlayerDatabase db;
    
    public Player(String username, PlayerDatabase db){
        this.username = username;
        this.db = db;  
        scores = db.getScores(username);
    }
    

    
        

    double getBest(int diff, int cat) {
        String mode = int2str(diff,cat);
        if (scores.get(mode).isEmpty()){
            return -1;
        }
        scores.get(mode).sort((a,b)->(int)Math.round(b-a));
        return scores.get(mode).get(0);
    }
    
    boolean recordScore(double score, int diff, int cat) {
        String mode = int2str(diff,cat);
        scores.get(mode).add(score);
        return db.saveAll();
    }
    
    public PlayerDatabase callDatabase(){
        return db;
    }
    
    ArrayList<Double> getScore(int diff, int cat){
        String mode = int2str(diff,cat);
        return scores.get(mode);
    }
    
    HashMap<String,ArrayList<Double>> getAllScores(){
        return scores;
    }
    

    double getAverage(int diff, int cat) {
        String mode = int2str(diff,cat);
        if (scores.get(mode).isEmpty()){
            return -1;
        }
        return scores.get(mode).stream().reduce((double)0,(a,b)->a+b)/scores.get(mode).size();
    }
    
    String int2str(int diff, int cat){
        String mode = "";
        switch (diff){
            case 1:
                mode+="Easy";
                break;
            case 2:
                mode+="Medium";
                break;
            case 3:
                mode+="Hard";
                break;
        }
        mode+="-";
        switch(cat){
            case 1:
                mode+="Algebra";
                break;
            case 2:
                mode+="Geometry";
                break;
            case 3:
                mode+="Calculus";
                break;
            case 4:
                mode+="Mixed";
                break;
        }
        return mode;
    }
}
