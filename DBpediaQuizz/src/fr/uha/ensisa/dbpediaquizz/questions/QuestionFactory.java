package fr.uha.ensisa.dbpediaquizz.questions;

import fr.uha.ensisa.dbpediaquizz.questions.geographie.QuestionCapitale;
import fr.uha.ensisa.dbpediaquizz.questions.histoire.QuestionRoiEtPredecesseur;
import fr.uha.ensisa.dbpediaquizz.questions.sport.QuestionChampionnatFranceFootball;

public class QuestionFactory {

	public static Question createQuestion()
	{
		int questionType= (int)(Math.random()*3);
		Question question;
		switch(questionType)
		{
			case 0 : 	question=new QuestionCapitale();
						break;
			case 1 :	question=new QuestionRoiEtPredecesseur();
			break;			
			default : 	question= new QuestionChampionnatFranceFootball();
						break;
		}
		//question= new QuestionChampionnatFranceFootball();
		//question=new QuestionCapitale();
		return question;
	}
	
}
