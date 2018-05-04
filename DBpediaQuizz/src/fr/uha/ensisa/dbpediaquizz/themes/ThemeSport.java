package fr.uha.ensisa.dbpediaquizz.themes;

import fr.uha.ensisa.dbpediaquizz.questions.Question;
import fr.uha.ensisa.dbpediaquizz.questions.sport.QuestionChampionnatFranceFootball;
import fr.uha.ensisa.dbpediaquizz.questions.sport.QuestionJoueurFoot;
import fr.uha.ensisa.dbpediaquizz.questions.sport.QuestionJoueurPhoto;
import fr.uha.ensisa.dbpediaquizz.questions.sport.QuestionSportOlympique;

public class ThemeSport extends Theme{

	@Override
	public Question createQuestion() {
		int questionType= (int)(Math.random()*4);
		Question question;
		switch(questionType)
		{
		case 0 : 	
			question=new QuestionChampionnatFranceFootball();
			break;
		case 1 :	
			question=new QuestionJoueurFoot();
			break;
		case 2:
			question=new QuestionJoueurPhoto();
			break;
		default : 	
			question= new QuestionSportOlympique();
			break;
		}
		return question;
	}

}
