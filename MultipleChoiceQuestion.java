/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yeas;

import java.util.HashMap;

/**
 *
 * @author Zhelyaz
 */
public class MultipleChoiceQuestion extends Question {
    
    private final String letters = "ABCDE";
    private HashMap<String,String> options;
    
    public MultipleChoiceQuestion(){
        super();
    }
    
    public MultipleChoiceQuestion(String q,String a,String[] as){
        super(q,a);
        for (int i=0;i<as.length;i++){
            options.put(String.valueOf(letters.charAt(i)), as[i]);
        }
    }
    
    @Override
    public String getAnswer(){
        return options.get(super.getAnswer().toUpperCase());
    };

    @Override
    public double answerCorrect(String[] playeranswer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
