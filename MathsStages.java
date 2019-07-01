package yeas;

import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.*;
import javafx.util.Duration;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.text.*;



import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class MathsStages extends ComputerPlayer implements EventHandler<KeyEvent> {
	static Stage maths;
	static TextField Input;
	static Label Output;
	Label Clock;
	Label skip;
	Label cheat;
	Button Cheat;
	Button Skip;
	Label Description;
	Button go;
	Label Go;
	VBox Top;
	VBox Center;
	VBox Bottom;
	HBox cheatskip;
	VBox sides;
	BorderPane layout;
	ArrayList<Boolean> Score=new ArrayList<Boolean>();
	ArrayList<Boolean> ScoreP2=new ArrayList<Boolean>();
	public static String answer;
	private static int difficulty;
	public static Stage stage;
	static Random ran;
	private Timeline timeline=new Timeline();
	public Integer timeSeconds=20;
	public int count;
	private static int firstnum;
	private static int secondnum;
	private static double ActualAnswer;
	private static double UserAnswer;
	private boolean multiordiv;
	Scene myScene;
	public int Pvpc;
	private int turn;
	Text Turn;
	Text alert;
	String Lp;
	String Rp;
	public Scene sceene;
	private boolean KeyAccess;
	public double accu;
	
	
	public void MathsStage(int Difficulty,Stage startStage,int cout,Scene PreviousScene,int PvPC,double accuracy) throws InterruptedException, FileNotFoundException{
		Pvpc=PvPC;
		setKeyAccess(true);
		sceene=PreviousScene;
		accu=accuracy;
		
		if(PvPC==1){
			Rp="Left Player";
			Lp="Right Player";
		}else{
			Lp="Human";
			Rp="Computer";
		} 
		
		
		if (PvPC==1){
		if(Score.size()==0 && ScoreP2.size()==0){
			count=0;
			Random randi=new Random();
			int s=randi.nextInt(100);
			if (s==0 || s%2==0){
				setTurn(0);
			}else{
				setTurn(1);
			}
		}
		}else{
			if(Score.size()==0 && ScoreP2.size()==0){			
				setTurn(1);
			}
		}
		
		if( getTurn()%2==0 && PvPC==0 && getTurn()!=1){

			ComputerPlayer();
		}
		
		if(count>39){
			Alert EndOfGame=new Alert(Alert.AlertType.WARNING);
			EndOfGame.setContentText("Your game has finished, your score is: "+Lp+" has: "+Integer.toString(Scoring(Score))+"/20"+" Whereas "+Rp+" has: "+Integer.toString(Scoring(ScoreP2))+"/20");
			timeline.stop();
			EndOfGame.showAndWait();
			startStage.close();
			System.exit(0);
		}
		
		stage=startStage;
		setDifficulty(Difficulty);
		setMultiOrDiv(MultiOrDiv());
		Randomizer(getDifficulty());
		
		
	
		if (Difficulty==1){
			Output=new Label(Integer.toString(getFirstNum())+"*"+Integer.toString(getSecondNum()));
		}else{
			if(getMultiOrDiv()==true){
				Output=new Label(Integer.toString(getFirstNum())+"*"+Integer.toString(getSecondNum()));
			}else{
				Output=new Label(Integer.toString(getFirstNum())+"/"+Integer.toString(getSecondNum()));
			}
		}
		
		Input=new TextField();
		Input.setOnKeyPressed(this);
		Input.clear();
		
		
		Skip=new Button();
		skip=new Label("Skip");
		skip.setFont(Font.font("Verdana",FontWeight.BOLD,10));
		Skip.setGraphic(skip);
		Skip.setMinHeight(40);
		Skip.setMinWidth(160);
		
		Cheat=new Button();
		cheat=new Label("Cheat");
		cheat.setFont(Font.font("Verdana",FontWeight.BOLD,10));
		Cheat.setGraphic(cheat);
		Cheat.setMinHeight(40);
		Cheat.setMinWidth(160);
		
		Turn=new Text();
		Turn.setFont(Font.font("Verdana",FontWeight.BOLD,13));
		Turn.setFill(Color.FIREBRICK);
		Turn.setText(TurnLabel(getTurn(),PvPC));
	
		Skip.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent a){
				if (getTurn()==0 || getTurn()%2==0){
					Score.add(false);
				}else{
					ScoreP2.add(false);
				}
				timeline.stop();
				setTimeline();
				timeSeconds=20;
				count++;
				setTurn(getTurn()+1);
				try {
					MathsStage(getDifficulty(),startStage,cout,myScene,PvPC,accu);
				} catch (FileNotFoundException | InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		Cheat.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent a){
				setKeyAccess(false);
				timeline.stop();
				Cheat.setText(Double.toString(getAnswer()));
			}
		});
		
		Description=new Label("\t On the blank space bellow write your answer and press enter, or press the arrow button to proceed. Any divisions results should be rounded to 3 decimal places. Good Luck!");
		Description.setWrapText(true);
		Description.setPadding(new Insets(65,20,50,20));
		
		alert=new Text();
		
		go=new Button();
		Go=new Label("-->");
		Go.setFont(Font.font("Verdana",FontWeight.BLACK,13));
		go.setGraphic(Go);
		sides=new VBox(30);
		sides.getChildren().add(go);
		sides.setTranslateY(230);
		
		Clock=new Label();
		Clock.setFont(Font.font("Verdana",FontWeight.EXTRA_BOLD,20));
		ClockTimer(startStage);
		
		cheatskip=new HBox(30);
		cheatskip.getChildren().addAll(Skip,Cheat);
		cheatskip.setAlignment(Pos.CENTER);
		
		Top=new VBox(30);
		Top.getChildren().addAll(Description,alert);
		Top.setAlignment(Pos.CENTER);
		
		Center=new VBox(30);
		Center.getChildren().addAll(Output,Input);
		Center.setAlignment(Pos.CENTER);
		
		Bottom=new VBox(30);
		Bottom.getChildren().addAll(Clock,cheatskip,Turn);
		Bottom.setAlignment(Pos.TOP_CENTER);
		Bottom.setTranslateY(-30);
		
		go.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				if (getKeyAccess()==true){
					answer=Input.getText();
					alert.setFill(Color.ALICEBLUE);
						if(answer.length()==0){
							alert.setText("You have not typed anything!");
							alert.setFont(Font.font("Verdana",FontWeight.BLACK,13));
							alert.setFill(Color.FIREBRICK);
						}else{
							if (FormatChecker(answer)==true){
								setUserAnswer(Integer.parseInt(answer));
								if(getUserAnswer()==getAnswer()){
									if(Pvpc==1){
										if (getTurn()==0 || getTurn()%2==0){
											Score.add(true);
										}else{
											ScoreP2.add(true);
										}
									}else{
										if (getTurn()==0 || getTurn()%2==0){
											ScoreP2.add(true);
										}else{
											Score.add(true);
										}
									}
									count++;
									timeline.stop();
									setTimeline();
									timeSeconds=20;
								}else {
									if(Pvpc==1){
										if (getTurn()==0 || getTurn()%2==0){
											Score.add(false);
										}else{
											ScoreP2.add(false);
										}
									}else{
										if (getTurn()==0 || getTurn()%2==0){
											ScoreP2.add(false);
										}else{
											Score.add(false);
										}
									}
									count++;
									timeline.stop();
									setTimeline();
									timeSeconds=20;
								}
								setTurn(getTurn()+1);
								try {
									MathsStage(difficulty,stage,count,myScene,Pvpc,accu);
								} catch (InterruptedException e1) {
									e1.printStackTrace();
								} catch (FileNotFoundException e1) {
									e1.printStackTrace();
										}
							}else{
								alert.setText("You are not allowed to type letters, try again with numbers!");
								alert.setFont(Font.font("Verdana",FontWeight.BLACK,13));
								alert.setFill(Color.FIREBRICK);
							}
						
						}
						
					
				}
			}
		});
		
		layout=new BorderPane();
		layout.setTop(Top);
		layout.setBottom(Bottom);
		layout.setCenter(Center);
		layout.setRight(sides);
		layout.setStyle("-fx-background-color: AliceBlue");
		
		myScene=new Scene(layout,500,500);
		if(cout==10){
			startStage.setScene(PreviousScene);
		}else{
			startStage.setScene(myScene);
		}
		
		startStage.show();

	}
	
	
	
	
	public void handle(KeyEvent e){
		if(e.getCode()==KeyCode.ENTER && getKeyAccess()==true){
			answer=Input.getText();
			alert.setFill(Color.ALICEBLUE);
				if(answer.length()==0){
					alert.setText("You have not typed anything!");
					alert.setFont(Font.font("Verdana",FontWeight.BLACK,13));
					alert.setFill(Color.FIREBRICK);
				}else{
					if (FormatChecker(answer)==true){
						setUserAnswer(Integer.parseInt(answer));
						if(getUserAnswer()==getAnswer()){
							if(Pvpc==1){
								if (getTurn()==0 || getTurn()%2==0){
									Score.add(true);
								}else{
									ScoreP2.add(true);
								}
							}else{
								if (getTurn()==0 || getTurn()%2==0){
									ScoreP2.add(true);
								}else{
									Score.add(true);
								}
							}
							count++;
							timeline.stop();
							setTimeline();
							timeSeconds=20;
						}else {
							if(Pvpc==1){
								if (getTurn()==0 || getTurn()%2==0){
									Score.add(false);
								}else{
									ScoreP2.add(false);
								}
							}else{
								if (getTurn()==0 || getTurn()%2==0){
									ScoreP2.add(false);
								}else{
									Score.add(false);
								}
							}
							count++;
							timeline.stop();
							setTimeline();
							timeSeconds=20;
						}
						setTurn(getTurn()+1);
						try {
							MathsStage(difficulty,stage,count,myScene,Pvpc,accu);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
								}
					}else{
						alert.setText("You are not allowed to type letters, try again with numbers!");
						alert.setFont(Font.font("Verdana",FontWeight.BLACK,13));
						alert.setFill(Color.FIREBRICK);
					}
				
				}
		}
			
	}
	
	
	
	public void Randomizer(int cas){
		DecimalFormat df=new DecimalFormat("#.000");
		ran=new Random();
		double fn;
		double sn;
		switch (cas){
			case 1: 
				setFirstNum(ran.nextInt(9)+1);
				setSecondNum(ran.nextInt(9)+1);
				fn=(double)getFirstNum();
				sn=(double)getSecondNum();
				setAnswer(Double.parseDouble(df.format(fn*sn)));
				break;
			case 2:
				setFirstNum(ran.nextInt(99)+1);
				setSecondNum(ran.nextInt(99)+1);
				fn=(double)getFirstNum();
				sn=(double)getSecondNum();
				if (getMultiOrDiv()==true){
					setAnswer(Double.parseDouble(df.format(fn*sn)));
				}else if(getMultiOrDiv()==false){
					setAnswer(Double.parseDouble(df.format(fn/sn)));
				}
				break;
			case 3:
				setFirstNum(ran.nextInt(999)+1);
				setSecondNum(ran.nextInt(999)+1);
				fn=(double)getFirstNum();
				sn=(double)getSecondNum();
				if(getMultiOrDiv()==true){
					setAnswer(Double.parseDouble(df.format(fn*sn)));
				}else if(getMultiOrDiv()==false){
					setAnswer(Double.parseDouble(df.format(fn/sn)));
				}
				break;
			default:
				break;
		}
		}
	
	
	
		public int Scoring(ArrayList<Boolean> scr){
			int countOftrues=0;
			for(boolean Scr:scr){
				if (Scr==true){
					countOftrues++;
				}
			}
			return countOftrues;
		}
		public static boolean MultiOrDiv(){
			ran=new Random();
			int guess=ran.nextInt(10000)+1;
			if (guess%2==0){
				return true;
			}else{
				return false;
			}

				
		
	}
		
		
		
		public void ClockTimer(Stage startStage) throws FileNotFoundException{
			timeline.setCycleCount(Timeline.INDEFINITE);
			KeyFrame frame=new KeyFrame(Duration.seconds(1),new EventHandler<ActionEvent>(){
				public void handle(ActionEvent event) {
					timeSeconds--;
					Clock.setText(timeSeconds.toString());
					if (timeSeconds<=0){
						if (getTurn()==0 || getTurn()%2==0){
							Score.add(false);
						}else{
							ScoreP2.add(false);
						}
						timeline.stop();
						setTimeline();
						timeSeconds=20;
						setTurn(getTurn()+1);
						try {
							MathsStage(getDifficulty(),startStage,0,myScene,Pvpc,accu);
						} catch (InterruptedException e) {
							e.printStackTrace();
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
					}
				}
			});
			timeline.getKeyFrames().add(frame);
			timeline.playFromStart();
	}
		
		public String TurnLabel(int Trn,int PvPC){
			if (PvPC==1){
				
			if(Trn==0 || Trn%2==0){
				return "Right Player's Turn";
			}else{
				return "Left Player's Turn";
			}
			
			}else{
				return "It is the Humans Turn";
			}
		}
		
	public ArrayList<Boolean> FirstListReturner(String s){
		if(s=="Right Player's Turn"){
			return Score;
		}else{
			return ScoreP2;
			}
		}
	
	public static boolean FormatChecker(String s){
		try{
			Integer.parseInt(s);
		}catch (NumberFormatException a){
			return false;
		}
		return true;
	}
	
	public void ComputerPlayer() throws InterruptedException{
			if(super.PCPlayer(accu)==true){
				count++;
				ScoreP2.add(true);
				timeline.stop();
				setTimeline();
				timeSeconds=20;
				setTurn(getTurn()+1);
				try {
					this.MathsStage(getDifficulty(), stage, 0, sceene, Pvpc,accu);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}else if (super.PCPlayer(accu)==false){
				count++;
				ScoreP2.add(false);
				timeline.stop();
				setTimeline();
				timeSeconds=20;
				setTurn(getTurn()+1);
				try {
					this.MathsStage(getDifficulty(), stage, 0, sceene, Pvpc,accu);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	
	public void setKeyAccess(boolean ac){
		KeyAccess=ac;
	}
	public boolean getKeyAccess(){
		return KeyAccess;
	}
	public void setTurn(int t){
		turn=t;
	}
	public int getTurn(){
		return turn;
	}
	public void setMultiOrDiv(boolean MOD){
		multiordiv=MOD;
	}
	public boolean getMultiOrDiv(){
		return multiordiv;
	}
	public Timeline getTimeline(){
		return timeline;
	}
	public void setTimeline(){
		timeline=new Timeline();
	}
	public static void setFirstNum(int Numer){
		firstnum=Numer;
	}
	public static int getFirstNum(){
		return firstnum;
	}
	public static void setSecondNum(int Denomi){
		secondnum=Denomi;
	}
	public static int getSecondNum(){
		return secondnum;
	}
	public static void setAnswer(double Ans){
		ActualAnswer=Ans;
	}
	public static double getAnswer(){
		return ActualAnswer;
	}
	public static void setUserAnswer(double UserAns){
		UserAnswer=UserAns;
	}
	public static double getUserAnswer(){
		return UserAnswer;
	}
	public void setDifficulty(int Dif){
		difficulty=Dif;
	}
	public int getDifficulty(){
		return difficulty;
	}
}
