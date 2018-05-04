package fr.uha.ensisa.dbpediaquizz.themes;

import fr.uha.ensisa.dbpediaquizz.questions.Question;

public class ThemeGeneral extends Theme{

	/**
	 * (non-Javadoc)
	 * @see fr.uha.ensisa.dbpediaquizz.themes.Theme#createQuestion()
	 **/
	@Override
	public Question createQuestion() {
		int questionType= (int)(Math.random()*3);
		Theme theme;
		switch(questionType)
		{
			case 0 : 	theme=new ThemeGeographie();
						break;
			case 1 :	theme=new ThemeHistoire();
						break;		
			case 2 :    theme=new ThemeSport();
			default : 	theme=new ThemeCulture();
						break;
		}
		return theme.createQuestion();
	}

}
