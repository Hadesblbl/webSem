package fr.uha.ensisa.dbpediaquizz;

import java.util.Scanner;

import fr.uha.ensisa.dbpediaquizz.questions.Question;
import fr.uha.ensisa.dbpediaquizz.themes.Theme;
import fr.uha.ensisa.dbpediaquizz.themes.ThemeFactory;
import fr.uha.ensisa.dbpediaquizz.util.Constantes;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class DBpediaQuizz extends Application{

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
		primaryStage.setTitle("Quizz"); 
		final Pane root = new Pane();
		final Scene scene = new Scene(root, 700, 600); 
		primaryStage.setScene(scene); 
		primaryStage.show(); 
	}

}
