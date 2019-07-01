package yeas;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;


public class Knowledge
{
  private static Scanner scn;
  
  public Knowledge() {}
  
  public static ArrayList<String[]> Everything()
    throws FileNotFoundException
  {
    ArrayList<String[]> first = new ArrayList();
    File fil = new File("Questions.txt");
    Scanner scn = new Scanner(fil);
    while (scn.hasNext()) {
      first.add(scn.nextLine().split("/"));
    }
    first.remove(0);
    return first;
  }
  
  public static ArrayList<String> Questions(ArrayList<String[]> Everything)
  {
    ArrayList<String> Questions = new ArrayList();
    for (String[] s : Everything) {
      Questions.add(s[1]);
    }
    return Questions;
  }
  
  public static ArrayList<String> Answers(ArrayList<String[]> Everything) throws FileNotFoundException {
    ArrayList<String> Answers = new ArrayList();
    for (String[] s : Everything) {
      Answers.add(s[2]);
      Answers.add(s[3]);
      Answers.add(s[4]);
    }
    return Answers;
  }
  
}