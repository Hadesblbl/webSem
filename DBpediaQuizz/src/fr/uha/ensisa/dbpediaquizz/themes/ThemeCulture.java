package fr.uha.ensisa.dbpediaquizz.themes;

import fr.uha.ensisa.dbpediaquizz.questions.Question;
import fr.uha.ensisa.dbpediaquizz.questions.culture.QuestionDrapeau;
import fr.uha.ensisa.dbpediaquizz.questions.culture.QuestionFilm;

public class ThemeCulture extends Theme{

	@Override
	public Question createQuestion() {
		int questionType= (int)(Math.random()*2);
		Question question;
		switch(questionType)
		{
		case 0:
			question= new QuestionFilm();
			break;
		default : 	
			question= new QuestionDrapeau();
			break;
		}
		return question;
	}

}
