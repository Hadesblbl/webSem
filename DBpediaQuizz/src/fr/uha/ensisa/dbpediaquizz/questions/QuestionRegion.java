package fr.uha.ensisa.dbpediaquizz.questions;

import java.util.List;

import org.apache.jena.query.QuerySolution;

import fr.uha.ensisa.dbpediaquizz.util.Constantes;
import fr.uha.ensisa.dbpediaquizz.util.DBpediaQuery;

public class QuestionRegion extends Question{
	public QuestionRegion()
	{
		super(Constantes.GEOGRAPHIE);
		//Récupération de toutes les villes de plus de 100000 habitants
		String requete = "select ?nomVille ?nomRegion where {?ville <http://dbpedia.org/ontology/region> ?region. "
                + "?region <http://www.w3.org/2000/01/rdf-schema#label> ?nomRegion."
                + "?ville <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://dbpedia.org/ontology/Settlement>." 
                + "?ville <http://www.w3.org/2000/01/rdf-schema#label> ?nomVille." 
                + "?ville <http://dbpedia.org/ontology/populationTotal> ?population." 
                + "FILTER (lang(?nomVille) = 'fr')"
                + "FILTER (lang(?nomRegion) = 'fr')"
                + "FILTER (?population > 100000)}";
		List<QuerySolution> villes = DBpediaQuery.execRequete(requete);
		QuerySolution ligne = villes.get((int)(Math.random()*villes.size()));
		
		if(Math.random()<0.5)
		{

			this.enonce = "Quelle est la ville de "+ligne.getLiteral("?nomRegion").getString()+" de plus de 100 000 habitants ?";
			this.bonneReponse= ligne.getLiteral("?nomVille").getString();

			int index=0;
			while(index<Constantes.NB_REPONSES-1)
			{
				ligne = villes.get((int)(Math.random()*villes.size()));
				if(reponseAbsente(ligne.getLiteral("?nomVille").getString()))
				{
					this.mauvaisesReponses[index]=ligne.getLiteral("?nomVille").getString();
					index++;
				}
			}
		}
		
		else
		{

			this.enonce = "Dans quelle région est la ville de"+ligne.getLiteral("?nomVille").getString()+" ?";
			this.bonneReponse= ligne.getLiteral("?nomRegion").getString();

			int index=0;
			while(index<Constantes.NB_REPONSES-1)
			{
				ligne = villes.get((int)(Math.random()*villes.size()));
				if(!this.bonneReponse.equalsIgnoreCase(ligne.getLiteral("?nomRegion").getString()))
				{
					this.mauvaisesReponses[index]=ligne.getLiteral("?nomRegion").getString();
					index++;
				}
			}
		}
		
	}
}
