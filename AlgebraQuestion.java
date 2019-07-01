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
import java.util.Random;
public class AlgebraQuestion extends Question{
    
    public AlgebraQuestion(){
        super();
    }
    
    public AlgebraQuestion(String q, String a){
        super(q,a);
    }
    
    public static AlgebraQuestion generateQuestion(int difficulty){
        Random r = new Random();
        int a,b,c;
        int asign,bsign,csign;
        int aplusb,atimesb;
        switch (difficulty){
            case 1:
                a = r.nextInt(11)+1;
                b = r.nextInt(11)+1;
                asign = r.nextInt(2);
                if (asign==0) a=-a;
                bsign = r.nextInt(2);
                if (bsign==0) b=-b;
                aplusb = -(a+b);
                atimesb = a*b;
                return new AlgebraQuestion("Find the roots of the quadratic equation\nx^2 "+((aplusb==0)?"":((aplusb<0)?"- ":"+ ")+((Math.abs(aplusb)!=1)?Math.abs(aplusb):"")+"x ") +((atimesb<0)?"- ":"+ ")+Math.abs(atimesb)+" = 0.",a+", "+b);
            case 2:
                a = r.nextInt(16)+1;
                b = r.nextInt(16)+1;
                asign = r.nextInt(2);
                if (asign==0) a=-a;
                bsign = r.nextInt(2);
                if (bsign==0) b=-b;
                int acoeff = r.nextInt(5)+1;
                int bcoeff = r.nextInt(5)+1;
                int abcd = -(acoeff*b+bcoeff*a);
                return new AlgebraQuestion("Find the roots of the quadratic equation\n"+(acoeff*bcoeff==1?"":(acoeff*bcoeff))+"x^2 "+((abcd==0)?"":((abcd>0)?"+ ":"- ")+(Math.abs(abcd)==1?"":Math.abs(abcd))+"x ") + (a*b>0?"+ ":"- ")+Math.abs(a*b)+" = 0 (to 2 d.p.)",(double)Math.round(100*a/acoeff)/100+", "+(double)Math.round(100*b/bcoeff)/100);                
            case 3:
                a = r.nextInt(11)+1;
                b = r.nextInt(11)+1;
                c = r.nextInt(11)+1;
                asign = r.nextInt(2);
                if (asign==0) a=-a;
                bsign = r.nextInt(2);
                if (bsign==0) b=-b;
                csign = r.nextInt(2);
                if (csign==0) c=-c;
                aplusb = -(a+b+c);
                atimesb = a*b+b*c+c*a;
                int abc = -a*b*c;
                return new AlgebraQuestion("Find the roots of the cubic equation\nx^3 "+(aplusb==0?"":(aplusb>0?"+ ":"- ")+Math.abs(aplusb)+"x^2 ") + (atimesb==0?"":(atimesb>0?"+ ":"- ")+Math.abs(atimesb)+"x ") + (abc>0?"+ ":"- ")+ Math.abs(abc)+" = 0.",a+", "+b+", "+c);
            default:
                return new AlgebraQuestion();
                
        }
    }
    //Adjusted so it gives marks for partially correct answers e.g. one of the two roots correct or the negative of a root.
    @Override
    public double answerCorrect(String[] playeranswer){
        String[] theanswer = super.getAnswer().split(",");
        if (theanswer.length!=playeranswer.length){
            return 0;
        }     
        double tot =0;
        double pts = 1.0/theanswer.length;
        ArrayList<String[]> idkwhy = new ArrayList();
        for (String s: playeranswer) idkwhy.add(new String[] {s.trim(),"0"});
        for (String s: theanswer){
            try{
                double d = Double.valueOf(s.trim());
                for (int i=0;i<2;i++){
                    outerloop:
                    for (String[] ss: idkwhy){
                            switch(i){
                                //First pass checks if playeranswer==actualanswer
                                case 1:
                                    if (Math.abs(d-Double.valueOf(ss[0]))<0.01&&!ss[1].equals("2")){
                                        tot+=ss[1].equals("0")?pts:pts/2;
                                        ss[1]="2";
                                        break outerloop;
                                    }
                                //Second pass checks if playeranswer==-(actualanswer) and gives half points for correct answer with wrong sign
                                case 2:
                                    if (Math.abs(d+(Double.valueOf(ss[0])))<0.01&&ss[1].equals("0")){
                                        tot+=pts/2;
                                        ss[1]="1";
                                        break outerloop;
                                    }
                            }
                    }
                }
            }catch (Exception e){
                return 0;
            }
        }
        return tot;
    }
    
    
}
