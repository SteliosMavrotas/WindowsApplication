package yeas;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Zhelyaz
 * 
 * 
 */
public class PlayerDatabase {
    private final HashMap<String,String> playerLoginData;
    private final HashMap<String,HashMap<String,ArrayList<Double>>> playerScores;
    private final File pld = new File("playerLoginData.txt");
    private final File scores = new File("playerScores.txt");
    private final boolean scoresok;
    private final boolean dataok;
    
    
    public PlayerDatabase(){
        playerLoginData = new HashMap();
        playerScores = new HashMap();
        dataok = fillData();
        scoresok = fillScores();
    }
    
    public boolean scoreOK(){
        return scoresok;
    }
    public boolean dataOK(){
        return dataok;
    }
    
    private boolean fillData(){
        Scanner scan;
        if (!pld.exists()){
            return false;
        }
        try{
            scan = new Scanner(pld);
            while (scan.hasNextLine()){
                String pp = scan.nextLine();
                if (pp.equals("")){
                    break;
                }
                try{
                String[] p = pp.split(":");
                playerLoginData.put(p[0], p[1]);
                }catch(Exception e){
                    return false;
                }
            }
            scan.close();
            return true;
        }catch (IOException e){
            return false;
        }
    }
    
    
    private boolean fillScores(){
        Scanner scan;
        if(!scores.exists()){
            return false;
        }
        try{
            scan = new Scanner(scores);
            while (scan.hasNextLine()){
                String nn = scan.nextLine();
                if (nn.equals("")) break;
                String name = nn.substring(1);
                HashMap<String,ArrayList<Double>> thisPlayerScores = new HashMap();
                for (int i=0;i<3;i++){
                    String difficulty = scan.nextLine();
                    if (difficulty.charAt(0)=='-'){
                        difficulty = difficulty.substring(1);
                        for (int j=0;j<4;j++){
                            String subjectArea = scan.nextLine();
                            if (subjectArea.charAt(0)=='>'){
                                String[] idk = subjectArea.split(":");
                                ArrayList<Double> scoresForThisCategory = new ArrayList();
                                if(idk.length>1){
                                    for (String num : idk[1].split(";")){
                                        if (!num.equals("")){
                                            double n = Double.valueOf(num);
                                            scoresForThisCategory.add(n);
                                        } 
                                    }
                                }
                                thisPlayerScores.put(difficulty+"-"+idk[0].substring(1), scoresForThisCategory);
                            }else{
                                throw new IOException("Error while loading scores.");
                            }
                        }
                    }else{
                        throw new IOException("Error while loading scores.");
                    }
                }
                playerScores.put(name, thisPlayerScores);
            }
            scan.close();
            return true;
        }catch (IOException e){
            return false;
        }
    }
    
    public int createNewUser(String un,String pas1,String pas2){
        
        if (!isValid(un)){
            return 1;
        }else if(un.length()<3){
            return 2;
        }else if (playerLoginData.containsKey(un)){
            return 3;
        }else if(pas1.length()<8){
            return 4;
        }else if (!pas1.equals(pas2)){
            return 5;
        }else if(!isValid(pas1)){
            return 6;
        }
        PrintWriter pp;
        try{
            playerLoginData.put(un, pas1);
            pp = new PrintWriter(new FileOutputStream("playerLoginData.txt",true));
            pp.println(un+":"+pas1);
            pp.close();
            HashMap<String,ArrayList<Double>> newplayer  = new HashMap();
            for (String s: new String[] {"Easy","Medium","Hard"}){
                for (String ss: new String[] {"Algebra","Geometry","Calculus","Mixed"}){
                    newplayer.put(s+"-"+ss, new ArrayList<Double>());
                }
            }
            playerScores.put(un, newplayer);
            saveAll();
            return 0;
            
        }catch (FileNotFoundException e){
            return 7;
        }
    }
    
    public boolean saveScores(String username){
        if (playerScores.containsKey(username)){
            HashMap<String,ArrayList<Double>> m = playerScores.get(username);
            PrintWriter p;
            try{
                p = new PrintWriter(new FileOutputStream("playerScores.txt"));
                p.println("#"+username);
                for (String s: new String[] {"Easy","Medium","Hard"}){
                    p.println("-"+s);
                    for (String ss: new String[] {"Algebra","Geometry","Calculus","Mixed"}){
                        p.print(">"+ss+":");
                        String hl = s+"-"+ss;
                        if (!m.get(hl).isEmpty()){
                            for (int i=0;i<m.get(hl).size();i++){
                                if (i==m.get(hl).size()-1){
                                    p.println(m.get(hl).get(i));
                                }else{
                                    p.print(m.get(hl).get(i)+";");
                                }
                            }
                        }else{
                            p.println();
                        }
                    }
                }
                p.close();
                return true;
                
            } catch (FileNotFoundException ex) {
                return false;
            }
        }
        return false;
    }
    
    public boolean saveAll(){
        return playerScores.keySet().stream().map(a->saveScores(a)).reduce(Boolean.TRUE, (a,b) -> a&&b);
    }
    
    public int login(String uname,String pass){
        if (!isValid(uname)){
            return 1;
        }else if (!playerLoginData.containsKey(uname)){
            return 3;            
        }else if(pass.length()<8||!isValid(pass)||!playerLoginData.get(uname).equals(pass)){
            return 2;
        }else{
            return 0;
        }
/////////////////////////////////////////////   OLD VERSION BEFORE JAVAFX    ///////////////////////////////////////////////////        
//        Scanner scan = new Scanner(System.in);
//        System.out.println();
//        System.out.println("Enter your username and password below.");
//        System.out.println();
//        System.out.println("Username: ");
//        String un = scan.nextLine().trim();
//        while (!isValid(un)||!playerLoginData.keySet().contains(un)){
//            if (!isValid(un)){
//                System.out.println();
//                System.out.println("Username contains illegal characters.");
//            }else{
//                System.out.println();
//                System.out.println("Username not in player database.");
//            }
//            System.out.println("Make sure you have entered it correctly.");
//            System.out.println();
//            System.out.println("Username: ");
//            un = scan.nextLine().trim();
//        }
//        System.out.println();
//        Random r = new Random();
//        int [] c = new int[3];
//        String[] ss = new String[3];
//        c[0] = r.nextInt(playerLoginData.get(un)[0].length()/2);
//        c[1] = r.nextInt(3*playerLoginData.get(un)[0].length()/4-c[0])+c[0];
//        c[2] = r.nextInt(playerLoginData.get(un)[0].length()-c[1])+c[1];
//        for (int i=0;i<3;i++){
//            c[i]+=1;
//            switch (c[i]){
//                case 1:
//                    ss[i] = "st";
//                    break;
//                case 2:
//                    ss[i]  ="nd";
//                    break;
//                case 3:
//                    ss[i] = "rd";
//                    break;
//                default:
//                    ss[i] = "th";
//            }
//        }
//        boolean passcorrect = false;
//        System.out.println("Enter the "+c[0]+ss[0]+", "+c[1]+ss[1]+" and "+c[2]+ss[2]+" characters of your password (case-sensitive), separated by commas.");
//        System.out.println("(Type in 'hint' to see your password hint if you have one.)");
//        String pas;
//        while (!passcorrect){
//            pas = scan.nextLine().trim();
//            switch (pas) {
//                case "":
//                    System.out.println("Invalid format! Please try again.");
//                    break;
//                case "hint":
//                    if (playerLoginData.get(un)[1].equals("<>")){
//                        System.out.println("You have not saved a hint for your password.");
//                    }else{
//                        System.out.println("Password hint: "+playerLoginData.get(un)[1]);
//                    }   System.out.println();
//                    System.out.println("Enter the "+c[0]+ss[0]+", "+c[1]+ss[1]+" and "+c[2]+ss[2]+" characters of your password , separated by commas.");
//                    break;
//                default:
//                    String[] pc = pas.split(",");
//                    for (int i=0;i<pc.length;i++) pc[i] = pc[i].trim();
//                    if (pc.length!=3 || (Arrays.asList(pc).stream().anyMatch(p -> p.length()!=1))){
//                        System.out.println("Invalid format! Please try again.");
//                    }else {
//                        passcorrect = true;
//                        for (int i=0;i<3;i++){
//                            if (pc[i].charAt(0)!=playerLoginData.get(un)[0].charAt(c[i])) passcorrect = false;
//                        }
//                    }   break;
//            }
//            System.out.println();
//            if (!passcorrect){
//                System.out.println("Enter the "+c[0]+ss[0]+", "+c[1]+ss[1]+" and "+c[2]+ss[2]+" characters of your password, separated by commas:");
//            }
//        }
//        System.out.println("Password correct!");
//        System.out.println();
//        System.out.println("Login successful! Retrieving your personal data.");
//        return new Player(un);
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////        
    }
    
    
    public HashMap<String,ArrayList<Double>> getScores(String username){
        if (playerScores.containsKey(username)){
            return playerScores.get(username);
        }
        return new HashMap();
    }
    
    
    private boolean isValid(String s){
        if (s.length()>20){
            return false;
        }
        String pattern = "[^A-Za-z0-9!@#$%&*()+=-_]";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(s);
        return !m.find();
    }
}
