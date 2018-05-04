package fr.uha.ensisa.dbpediaquizz.themes;

import fr.uha.ensisa.dbpediaquizz.questions.Question;
import fr.uha.ensisa.dbpediaquizz.questions.histoire.QuestionBataille;
import fr.uha.ensisa.dbpediaquizz.questions.histoire.QuestionChateau;
import fr.uha.ensisa.dbpediaquizz.questions.histoire.QuestionDynastieRoi;
import fr.uha.ensisa.dbpediaquizz.questions.histoire.QuestionRoiEtPredecesseur;

public class ThemeHistoire extends Theme{

	@Override
	public Question createQuestion() {
		int questionType= (int)(Math.random()*4);
		Question question;
		switch(questionType)
		{
		case 0 : 	
			question=new QuestionBataille();
			break;
		case 1 :	
			question=new QuestionChateau();
			break;
		case 2 :
			question=new QuestionDynastieRoi();
			break;
		default : 	
			question= new QuestionRoiEtPredecesseur();
			break;
		}
		return question;
	}

}
