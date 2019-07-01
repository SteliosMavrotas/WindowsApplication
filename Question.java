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
public abstract class Question {
    private String q;
    private String a;
    
    public Question(){ 
    }
    
    public Question(String q,String a){
        this.q = q;
        this.a = a;
    }
    
    public String getAnswer(){
        return a;
    }
    
    @Override
    public String toString(){
        return q;
    }
    
    public abstract double answerCorrect(String[] playeranswer);
}
