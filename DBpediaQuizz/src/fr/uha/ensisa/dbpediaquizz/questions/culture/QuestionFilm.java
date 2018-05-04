package fr.uha.ensisa.dbpediaquizz.questions.culture;

import java.util.List;

import org.apache.jena.query.QuerySolution;

import fr.uha.ensisa.dbpediaquizz.questions.Question;
import fr.uha.ensisa.dbpediaquizz.util.Constantes;
import fr.uha.ensisa.dbpediaquizz.util.DBpediaQuery;

public class QuestionFilm extends Question {

	public QuestionFilm() {
		super(Constantes.CULTURE);
		String requete = "select ?titre ?nomGenre ?nomRealisateur ?sortie where {?film a <http://dbpedia.org/ontology/Film>." + 
				"?film <http://fr.dbpedia.org/property/titre> ?titre." + 
				"?film <http://fr.dbpedia.org/property/genre> ?genre." + 
				"?film <http://fr.dbpedia.org/property/réalisation> ?realisateur." + 
				"?film <http://fr.dbpedia.org/property/annéeDeSortie> ?sortie." + 
				"?realisateur <http://fr.dbpedia.org/property/nom> ?nomRealisateur." + 
				"?genre <http://www.w3.org/2000/01/rdf-schema#label> ?nomGenre." + 
				"FILTER (lang(?titre)='fr')." + 
				"FILTER (lang(?nomGenre)='fr')." + 
				"FILTER (lang(?nomRealisateur)='fr').}";
		List<QuerySolution> films = DBpediaQuery.execRequete(requete);
		QuerySolution ligne = films.get((int)(Math.random()*films.size()));

		this.enonce = "Quel est le genre du film "+ligne.getLiteral("?titre").getString()+" réalisé par "+ligne.getLiteral("?nomRealisateur").getString()+" en "+ligne.getLiteral("?sortie").getString()+" ?";
		this.bonneReponse= ligne.getLiteral("?nomGenre").getString();

		int index=0;
		while(index<Constantes.NB_REPONSES-1)
		{
			ligne = films.get((int)(Math.random()*films.size()));
			if(reponseAbsente(ligne.getLiteral("?nomGenre").getString()))
			{
				this.mauvaisesReponses[index]=ligne.getLiteral("?nomGenre").getString();
				index++;
			}
		}

	}

}
