package yeas;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javafx.scene.text.*;
import javafx.stage.*;
import javafx.util.Duration;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

public class KnowledgeStage extends ComputerPlayer implements EventHandler<ActionEvent> {
	Button firstChoise;
	Button secondChoise;
	Button thirdChoise;
	Button skip;
	Button cheat;
	Label Question;
	Label Lives;
	Label Clock;
	Scene Kn;
	BorderPane layout;
	Random ran;
	ArrayList<Boolean> Score=new ArrayList<Boolean>();
	ArrayList<Boolean> ScoreP2=new ArrayList<Boolean>();
	ArrayList<Integer> QuestionsChecked=new ArrayList<Integer>(Collections.nCopies(50, 0));
	private Timeline timeline=new Timeline();
	public Integer timeSeconds=20;
	private String RealAnswer;
	private String UserAnswer;
	public String firstAnswer;
	public String secondAnswer;
	public String thirdAnswer;
	public String question;
	static VBox Top;
	static VBox Bottom;
	static VBox Center;
	static HBox cheatskip;
	Label Firstans;
	Label Secondans;
	Label Thirdans;
	Label Skip;
	Label Cheat;
	Text Turn;
	public String PlayerTurn;
	private int turn;
	public int AnswerBoxRan;
	public int QuestionIndex;
	public int Pvpc;
	public int InitialTurn;
	String Lp;
	String Rp;
	public Stage stage;
	//PvPC is used to decide if it is PvP or PvC, 1 for PvP and 0 for PvC
	public  void displayKnowledge(Stage startStage,int PvPC,double accuracy) throws FileNotFoundException{
		ArrayList<String> Questions=new ArrayList<String>();
		ArrayList<String> Answers=new ArrayList<String>();
		ArrayList<String[]> AnswersandQuestions=new ArrayList<String[]>();
		Pvpc=PvPC;
		stage=startStage;
		
		ran=new Random();
		if(PvPC==1){
			Rp="Left Player";
			Lp="Right Player";
		}else{
			Lp="Human";
			Rp="Computer";
		}
		
		if (PvPC==1){
		if(Score.size()==0 && ScoreP2.size()==0){
			int s=ran.nextInt(100);
			InitialTurn=s;
			if (s%2==0){
				PlayerTurn="Right Player's Turn";
				setTurn(0);
			}else{
				PlayerTurn="Left Player's Turn";
				setTurn(1);
			}
		}
		}else{
			if(Score.size()==0 && ScoreP2.size()==0){
				PlayerTurn="It is the Humans turn";
				setTurn(1);
			}
		}
		
		
		if (FirstListReturner(PlayerTurn).size()==20){
			Alert End=new Alert(Alert.AlertType.WARNING);
			End.setContentText("Your game has finished, your score is: "+Lp+" has: "+Integer.toString(Scoring(Score))+"/20"+" Whereas "+Rp+" has: "+Integer.toString(Scoring(ScoreP2))+"/20");
			End.showAndWait();
			timeline.stop();
			startStage.close();
			System.exit(0);
		}
		
		if(  getTurn()%2==0 && PvPC==0 && getTurn()!=1){
			try {
				ComputerPlayer(accuracy);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
			
			
		AnswersandQuestions=Knowledge.Everything();
		Questions=Knowledge.Questions(AnswersandQuestions);
		Answers=Knowledge.Answers(AnswersandQuestions);
		
		
	
		while(QuestionsChecked.get(QuestionIndex)==1){
			QuestionIndex=ran.nextInt(49)+1;
		}
		question=Questions.get(QuestionIndex);
		QuestionsChecked.set(1,QuestionIndex);
		setRealAnswer(Answers.get(QuestionIndex*3));
		
		
		AnswerBoxRan=ran.nextInt(2)+1;
		switch (AnswerBoxRan){
			case 1:
				firstAnswer=getRealAnswer();
				AnswerBoxRan=ran.nextInt(1)+1;
				if (AnswerBoxRan==1){
					secondAnswer=Answers.get(QuestionIndex*3+1);
					thirdAnswer=Answers.get(QuestionIndex*3+2);
				}else{
					thirdAnswer=Answers.get(QuestionIndex*3+1);
					secondAnswer=Answers.get(QuestionIndex*3+2);
				}
				break;
			case 2:
				secondAnswer=getRealAnswer();
				AnswerBoxRan=ran.nextInt(1)+1;
				if (AnswerBoxRan==1){
					firstAnswer=Answers.get(QuestionIndex*3+1);
					thirdAnswer=Answers.get(QuestionIndex*3+2);
				}else{
					thirdAnswer=Answers.get(QuestionIndex*3+1);
					firstAnswer=Answers.get(QuestionIndex*3+2);
				}
				break;
			case 3:
				thirdAnswer=getRealAnswer();
				AnswerBoxRan=ran.nextInt(1)+1;
				if (AnswerBoxRan==1){
					firstAnswer=Answers.get(QuestionIndex*3+1);
					secondAnswer=Answers.get(QuestionIndex*3+2);
				}else{
					secondAnswer=Answers.get(QuestionIndex*3+1);
					firstAnswer=Answers.get(QuestionIndex*3+2);
				}
				break;
		}
		
		
		Firstans=new Label(firstAnswer);
		Firstans.setFont(Font.font("Verdana",FontWeight.BLACK,13));
		Secondans=new Label(secondAnswer);
		Secondans.setFont(Font.font("Verdana",FontWeight.BLACK,13));
		Thirdans=new Label(thirdAnswer);
		Thirdans.setFont(Font.font("Verdana",FontWeight.BLACK,13));
		firstChoise=new Button();
		secondChoise=new Button();
		thirdChoise=new Button();
		firstChoise.setMinHeight(40);
		secondChoise.setMinHeight(40);
		thirdChoise.setMinHeight(40);
		firstChoise.setMinWidth(160);
		secondChoise.setMinWidth(160);
		thirdChoise.setMinWidth(160);
		firstChoise.setGraphic(Firstans);
		secondChoise.setGraphic(Secondans);
		thirdChoise.setGraphic(Thirdans);
		
		Turn=new Text();
		Turn.setFont(Font.font("Verdana",FontWeight.BOLD,13));
		Turn.setFill(Color.FIREBRICK);
		Turn.setText(TurnLabel(getTurn(),PvPC));
		
		skip=new Button();
		Skip=new Label("Skip");
		Skip.setFont(Font.font("Verdana",FontWeight.BLACK,13));
		skip.setGraphic(Skip);
		cheat=new Button();
		Cheat=new Label("Cheat");
		Cheat.setFont(Font.font("Verdana",FontWeight.BLACK,13));
		cheat.setGraphic(Cheat);
		Question=new Label(question);
		Question.setWrapText(true);
		
		Clock=new Label();
		Clock.setFont(Font.font("Verdana",FontWeight.BOLD,13));
		
		cheat.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent a){
				timeline.stop();
				setTimeline();
				timeSeconds=20;
				cheat.setText(getRealAnswer());
			}
		});
		
		skip.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent a){
				if (getTurn()%2==0){
					Score.add(false);
				}else{
					ScoreP2.add(false);
				}
				timeline.stop();
				setTimeline();
				timeSeconds=20;
				setTurn(getTurn()+1);
				try {
					displayKnowledge(startStage,PvPC,accuracy);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
		
		firstChoise.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent a){
				setUserAnswer(firstAnswer);
				if(getRealAnswer().equals(getUserAnswer())==true){
					try {
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
						timeline.stop();
						setTimeline();
						timeSeconds=20;
						setTurn(getTurn()+1);
						displayKnowledge(startStage,PvPC,accuracy);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}else{
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
					timeline.stop();
					setTimeline();
					timeSeconds=20;
					setTurn(getTurn()+1);
					try {
						displayKnowledge(startStage,PvPC,accuracy);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					
				}
			}
		});
		
		secondChoise.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent a){
				setUserAnswer(secondAnswer);
				if(getRealAnswer().equals(getUserAnswer())==true){
					try {
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
						timeline.stop();
						setTimeline();
						timeSeconds=20;
						setTurn(getTurn()+1);
						displayKnowledge(startStage,PvPC,accuracy);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}else{
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
					timeline.stop();
					setTimeline();
					timeSeconds=20;
					setTurn(getTurn()+1);
					try {
						displayKnowledge(startStage,PvPC,accuracy);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					
				}
			}
		});
		
		thirdChoise.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent a){
				setUserAnswer(thirdAnswer);
				if(getRealAnswer().equals(getUserAnswer())==true){
					try {
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
						timeline.stop();
						setTimeline();
						timeSeconds=20;
						setTurn(getTurn()+1);
						displayKnowledge(startStage,PvPC,accuracy);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}else{
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
					timeline.stop();
					setTimeline();
					timeSeconds=20;
					setTurn(getTurn()+1);
					try {
						displayKnowledge(startStage,PvPC,accuracy);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					
				}
			}
		});
		
		layout=new BorderPane();
		layout.setStyle("-fx-background-color: AliceBlue");
	    layout.setPadding(new Insets(25,25,50,25));
		
		Top=new VBox(30);
		Top.getChildren().addAll(Question);
		Top.setAlignment(Pos.CENTER);
		
		Center=new VBox(30);
		Center.getChildren().addAll(firstChoise,secondChoise,thirdChoise);
		Center.setAlignment(Pos.CENTER);
		
		Bottom=new VBox(30);
		cheatskip=new HBox(30);
		cheatskip.getChildren().addAll(cheat,skip);
		cheatskip.setAlignment(Pos.CENTER);
		Bottom.getChildren().addAll(cheatskip,Clock,Turn);
		Bottom.setAlignment(Pos.CENTER);
		
		layout.setTop(Top);
		layout.setBottom(Bottom);
		layout.setCenter(Center);

		ClockTimer(startStage,accuracy);
		Kn=new Scene(layout,500,500);
		startStage.setScene(Kn);
		startStage.show();
	}
	
	public void ComputerPlayer(double accuracy) throws InterruptedException{
		if(super.PCPlayer(accuracy)==true){
			ScoreP2.add(true);
			timeline.stop();
			setTimeline();
			timeSeconds=20;
			setTurn(getTurn()+1);
			try {
				this.displayKnowledge(stage,Pvpc,accuracy);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}else if (super.PCPlayer(accuracy)==false){
			ScoreP2.add(false);
			timeline.stop();
			setTimeline();
			timeSeconds=20;
			setTurn(getTurn()+1);
			try {
				this.displayKnowledge(stage,Pvpc,accuracy);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void ClockTimer(Stage startStage,double accuracy) throws FileNotFoundException{
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame frame=new KeyFrame(Duration.seconds(1),new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event) {
				timeSeconds--;
				Clock.setText(timeSeconds.toString());
				if (timeSeconds<=0){
					if (getTurn()%2==0){
						Score.add(false);
					}else{
						ScoreP2.add(false);
					}
					timeline.stop();
					setTimeline();
					timeSeconds=20;
					try {
						displayKnowledge(startStage,Pvpc,accuracy);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		});
		timeline.getKeyFrames().add(frame);
		timeline.playFromStart();
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
	
	public String TurnLabel(int Trn,int PvPC){
		if (PvPC==1){
		if(Trn%2==0){
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
			return ScoreP2;
		}else{
			return Score;
		}
	}
	public void setTurn(int t){
		turn=t;
	}
	public int getTurn(){
		return turn;
	}
	public Timeline getTimeline(){
		return timeline;
	}
	public void setTimeline(){
		timeline=new Timeline();
	}
	public void setRealAnswer(String answer){
		RealAnswer=answer;
	}
	
	public String getRealAnswer(){
		return RealAnswer;
	}
	public void setUserAnswer(String answer){
		UserAnswer=answer;
	}
	
	public String getUserAnswer(){
		return UserAnswer;
	}

	@Override
	public void handle(ActionEvent event) {
		
	}
}
