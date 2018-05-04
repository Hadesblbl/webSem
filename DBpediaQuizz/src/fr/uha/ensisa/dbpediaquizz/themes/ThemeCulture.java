package fr.uha.ensisa.dbpediaquizz.themes;

import fr.uha.ensisa.dbpediaquizz.questions.Question;
import fr.uha.ensisa.dbpediaquizz.questions.culture.QuestionFilm;

public class ThemeCulture extends Theme{

	@Override
	public Question createQuestion() {
		int questionType= (int)(Math.random()*3);
		Question question;
		switch(questionType)
		{		
		default : 	
			question= new QuestionFilm();
			break;
		}
		return question;
	}

}
