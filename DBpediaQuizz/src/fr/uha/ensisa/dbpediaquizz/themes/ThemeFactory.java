package fr.uha.ensisa.dbpediaquizz.themes;

public class ThemeFactory {
	public static Theme createTheme(int choisi) {
		Theme theme;
		switch(choisi) {
		case 0:
			theme = new ThemeGeographie();
			break;
		case 1:
			theme = new ThemeHistoire();
			break;
		case 2:
			theme = new ThemeSport();
			break;
		case 3:
			theme = new ThemeCulture();
			break;
		default:
			theme=new ThemeGeneral();
			break;
		}
		return theme;
	}
}
