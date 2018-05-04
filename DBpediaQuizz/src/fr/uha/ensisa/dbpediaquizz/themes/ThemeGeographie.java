package fr.uha.ensisa.dbpediaquizz.themes;

import fr.uha.ensisa.dbpediaquizz.questions.Question;
import fr.uha.ensisa.dbpediaquizz.questions.geographie.QuestionCapitale;
import fr.uha.ensisa.dbpediaquizz.questions.geographie.QuestionMonnaie;
import fr.uha.ensisa.dbpediaquizz.questions.geographie.QuestionPaysLangue;
import fr.uha.ensisa.dbpediaquizz.questions.geographie.QuestionRegion;

public class ThemeGeographie extends Theme{

	@Override
	public Question createQuestion() {
		int questionType= (int)(Math.random()*4);
		Question question;
		switch(questionType)
		{
		case 0 : 	
			question=new QuestionCapitale();
			break;
		case 1 :	
			question=new QuestionMonnaie();
			break;
		case 2 : 	
			question=new QuestionPaysLangue();
			break;
		default : 	
			question= new QuestionRegion();
			break;
		}
		return question;
	}

}
