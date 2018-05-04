package fr.uha.ensisa.dbpediaquizz.questions.sport;

import java.util.List;

import org.apache.jena.query.QuerySolution;

import fr.uha.ensisa.dbpediaquizz.questions.Question;
import fr.uha.ensisa.dbpediaquizz.util.Constantes;
import fr.uha.ensisa.dbpediaquizz.util.DBpediaQuery;

public class QuestionSportOlympique extends Question {
	public QuestionSportOlympique() {
		super(Constantes.GEOGRAPHIE);
		String requete = "select ?nomSport ?olympique WHERE { ?sport a <http://dbpedia.org/ontology/Sport>."
                          + "?sport foaf:name ?nomSport."
                          + "?sport prop-fr:olympique ?olympique."
                          + "FILTER (?olympique<2018)"
                          + "FILTER (lang(?nomSport) = 'fr')}";
		List<QuerySolution> olympique = DBpediaQuery.execRequete(requete);
		QuerySolution ligne = olympique.get((int)(Math.random()*olympique.size()));

		if(Math.random()<0.5)
		{

			this.enonce = "Quelle est l'année d'entrée du "+ligne.getLiteral("?nomSport").getString()+" aux jeux olympiques ?";
			this.bonneReponse= ligne.getLiteral("?olympique").getString();

			int index=0;
			while(index<Constantes.NB_REPONSES-1)
			{
				ligne = olympique.get((int)(Math.random()*olympique.size()));
				if(reponseAbsente(ligne.getLiteral("?nomSport").getString()))
				{
					this.mauvaisesReponses[index]=ligne.getLiteral("?olympique").getString();
					index++;
				}
			}
		}
		
		else
		{

			this.enonce = "Quel sport est entré aux jeux olympiques en "+ligne.getLiteral("?olympique").getString()+ " ?";
			this.bonneReponse= ligne.getLiteral("?nomPays").getString();

			int index=0;
			while(index<Constantes.NB_REPONSES-1)
			{
				ligne = olympique.get((int)(Math.random()*olympique.size()));
				if(!this.bonneReponse.equalsIgnoreCase(ligne.getLiteral("?nomSport").getString()))
				{
					this.mauvaisesReponses[index]=ligne.getLiteral("?nomSport").getString();
					index++;
				}
			}
		}

            
	}

}
