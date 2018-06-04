package fr.uha.ensisa.dbpediaquizz;

import java.util.Scanner;

import fr.uha.ensisa.dbpediaquizz.questions.Question;
import fr.uha.ensisa.dbpediaquizz.themes.Theme;
import fr.uha.ensisa.dbpediaquizz.themes.ThemeFactory;
import fr.uha.ensisa.dbpediaquizz.util.Constantes;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class DBpediaQuizz extends Application{

	int reponse;
	int score;
	boolean repondu;
	Theme theme;

	int currentQuestion;
	Question question;
	int correctAnswer;

	Label label= new Label("Choisissez votre thème parmi les thèmes suivants :");
	Label lastQuestionResponse = new Label();
	Label questionLabel = new Label();
	Button button1= new Button("Geographie");
	Button button2= new Button("Histoire");
	Button button3= new Button("Sport");
	Button button4= new Button("Culture");
	Button button5= new Button("Général (tous types de question confondus)");

	public static void main(String[] args) {
		int currentQuestion=0, score=0;
		boolean console=false;
		if (console) {
			long startingTime;
			Scanner entry = new Scanner(System.in);
			System.out.println("******* DBpedia Quizz *******");
			System.out.println("Choisissez votre thème parmi les thèmes suivants :");
			System.out.println("0. Géographie");
			System.out.println("1. Histoire");
			System.out.println("2. Sport");
			System.out.println("3. Culture");
			System.out.println("4. Général (Tous types de questions confondus)");
			int choisi=entry.nextInt();
			Theme theme = ThemeFactory.createTheme(choisi);
			System.out.println("C'est parti pour "+Constantes.NB_QUESTIONS+" questions !");
			while(currentQuestion<Constantes.NB_QUESTIONS)
			{
				currentQuestion++;
				Question question=theme.createQuestion();
				System.out.println("***********************************");
				System.out.println("QUESTION "+currentQuestion);
				System.out.println("***********************************");
				startingTime=System.currentTimeMillis();
				score+=question.ask(entry);
				System.out.println();
			}
			System.out.println("***********************************");
			System.out.println("SCORE FINAL : "+score+"/"+Constantes.NB_QUESTIONS);
			System.out.println("***********************************");
		} else {
			Application.launch(args);
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		reponse=-1;
		score=0;
		repondu=false;
		currentQuestion=0;
		correctAnswer=-1;
		
		primaryStage.setTitle("Quizz"); 
		final Pane root = new Pane();
		root.getChildren().add(label);
		button1.setOnAction(event -> {
			if(currentQuestion>=Constantes.NB_QUESTIONS) {
				printAnswer();
				label.setText("Quizz fini, score ="+score+"/"+Constantes.NB_QUESTIONS);
			} else {
				setReponse(0);
				setTheme();
				nextQuestion();
			}
		});
		button1.setLayoutY(100);
		button1.setLayoutX(300);
		root.getChildren().add(button1);
		button2.setOnAction(event -> {
			if(currentQuestion>=Constantes.NB_QUESTIONS) {
				printAnswer();
				label.setText("Quizz fini, score ="+score+"/"+Constantes.NB_QUESTIONS);
			} else {
				setReponse(1);
				setTheme();
				nextQuestion();
			}
		});
		button2.setLayoutY(150);
		button2.setLayoutX(300);
		root.getChildren().add(button2);
		button3.setOnAction(event -> {
			if(currentQuestion>=Constantes.NB_QUESTIONS) {
				printAnswer();
				label.setText("Quizz fini, score ="+score+"/"+Constantes.NB_QUESTIONS);
			} else {
				setReponse(2);
				setTheme();
				nextQuestion();
			}
		});
		button3.setLayoutY(200);
		button3.setLayoutX(300);
		root.getChildren().add(button3);
		button4.setOnAction(event -> {
			if(currentQuestion>=Constantes.NB_QUESTIONS) {
				printAnswer();
				label.setText("Quizz fini, score ="+score+"/"+Constantes.NB_QUESTIONS);
			} else {
				setReponse(3);
				setTheme();
				nextQuestion();
			}
		});
		button4.setLayoutY(250);
		button4.setLayoutX(300);
		root.getChildren().add(button4);
		button5.setOnAction(event -> {
			if(currentQuestion>=Constantes.NB_QUESTIONS) {
				printAnswer();
				label.setText("Quizz fini, score ="+score+"/"+Constantes.NB_QUESTIONS);
			} else {
				setReponse(4);
				setTheme();
				nextQuestion();
			}
		});
		button5.setLayoutY(300);
		button5.setLayoutX(300);
		root.getChildren().add(button5);
		questionLabel.setLayoutY(50);
		root.getChildren().add(questionLabel);
		lastQuestionResponse.setLayoutY(350);
		root.getChildren().add(lastQuestionResponse);
		final Scene scene = new Scene(root, 700, 600); 
		primaryStage.setScene(scene); 
		primaryStage.show(); 
	}

	private void nextQuestion() {
		printAnswer();
		currentQuestion++;
		question=theme.createQuestion();
		label.setText("QUESTION "+currentQuestion);
		correctAnswer=question.ask(questionLabel, button1, button2, button3, button4); //set next Question
	}
	
	private void printAnswer() {
		String text= new String();
		switch(correctAnswer) {
		case 0:
			text=button1.getText();
			break;
		case 1:
			text=button2.getText();
			break;
		case 2:
			text=button3.getText();
			break;
		case 3:
			text=button4.getText();
			break;
		default:
			break;
		}
		if(text.equals("")) {
			
		} else if(reponse==correctAnswer) {
			score+=1;
			lastQuestionResponse.setText("Gagné ! La réponse à \""+questionLabel.getText()+"\" était bien "+text);
		} else {
			lastQuestionResponse.setText("Raté ! La réponse à \""+questionLabel.getText()+"\" était "+text);
		}
	}

	private void setReponse(int i) {
		reponse=i;
	}

	private int getReponse() {
		return reponse;
	}

	private Question getQuestion() {
		return question;
	}

	private void setTheme() {
		if(!repondu) {
			repondu=true;
			theme=ThemeFactory.createTheme(getReponse());
			button5.setVisible(false);
		}
	}

}
