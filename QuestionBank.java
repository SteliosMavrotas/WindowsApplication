/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yeas;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Zhelyaz
 * 
 * This class currently only supports the AlgebraQuestion type because the other classes have not yet been implemented (due to time constraints)
 */
public class QuestionBank {
    ArrayList<Question> questions;
    
    public QuestionBank(int numberOfQuestions, int qtype, int difficulty){
        questions = new ArrayList();
        for (int i=0;i<numberOfQuestions;i++){
            questions.add(AlgebraQuestion.generateQuestion(difficulty));
        }
    }
    
    public Question next(){
        if (questions.isEmpty()){
            return null;
        }
        Random r = new Random();
        int i = r.nextInt(questions.size());
        Question q = questions.get(i);
        questions.remove(i);
        return q;   
    }
    
}
