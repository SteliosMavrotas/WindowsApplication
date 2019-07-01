package yeas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class ComputerPlayer {
	public double probability;
	public int numer;
	public int denom;
	
	public boolean PCPlayer(double accuracy){
		//Random ran=new Random();
//		while(probability<1 || probability>999){
//			numer=ran.nextInt(100000)+10;
//			denom=ran.nextInt(1000);
//			probability=(double)numer/denom;
//			probability=probability*100;
//		}
//		ArrayList<Integer> arr=new ArrayList<Integer>(Collections.nCopies(1000, 0));
//		for (int i=0;i<=Math.ceil(probability)-1;i++){
//			arr.add(i, 1);
//		}
//		int r=ran.nextInt(1000);
//		if (arr.get(r)==1){
//			return true;
//		}else{
//			return false;
//		}
           return Math.random()<accuracy;
		
	}
}
