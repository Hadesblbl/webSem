package fr.uha.ensisa.dbpediaquizz.questions.histoire;

import java.util.List;

import org.apache.jena.query.QuerySolution;

import fr.uha.ensisa.dbpediaquizz.questions.Question;
import fr.uha.ensisa.dbpediaquizz.util.Constantes;
import fr.uha.ensisa.dbpediaquizz.util.DBpediaQuery;

public class QuestionBataille extends Question {

	public QuestionBataille()
	{
		super(Constantes.HISTOIRE);
		String requete = "select ?nomBataille ?localisation ?victoire where {?g a <http://dbpedia.org/ontology/MilitaryConflict.>" + 
				"                 ?g <http://www.w3.org/2000/01/rdf-schema#label> ?nomBataille." + 
				"                 ?g <http://fr.dbpedia.org/property/géolocalisation> ?localisation." + 
				"                 ?g <http://dbpedia.org/ontology/result> ?victoire." + 
				"                 FILTER (lang(?gnom)='fr')." + 
				"                 FILTER regex (?victoire, \"victoire\")}";
		List<QuerySolution> batailles = DBpediaQuery.execRequete(requete);
		QuerySolution ligne = batailles.get((int)(Math.random()*batailles.size()));

		if(Math.random()<0.5)
		{

			this.enonce = "Quel est le nom de la bataille ayant lieu à "+ligne.getLiteral("?localisation").getString()+" où la victoire était : "+ligne.getLiteral("?victoire").getString()+" ?";
			this.bonneReponse= ligne.getLiteral("?nomBataille").getString();

			int index=0;
			while(index<Constantes.NB_REPONSES-1)
			{
				ligne = batailles.get((int)(Math.random()*batailles.size()));
				if(reponseAbsente(ligne.getLiteral("?nomBataille").getString()))
				{
					this.mauvaisesReponses[index]=ligne.getLiteral("?nomBataille").getString();
					index++;
				}
			}
		}
		else
		{

			this.enonce = "Où a eu lieu la "+ligne.getLiteral("?nomBataille").getString()+" ? Qui l'a gagné ?";
			this.bonneReponse= "Lieu : "+ligne.getLiteral("?localisation").getString()+" ; "+ligne.getLiteral("?victoire").getString();

			int index=0;
			while(index<Constantes.NB_REPONSES-1)
			{
				ligne = batailles.get((int)(Math.random()*batailles.size()));
				if(!this.bonneReponse.equalsIgnoreCase("Lieu : "+ligne.getLiteral("?localisation").getString()+" ; "+ligne.getLiteral("?victoire").getString()))
				{
					this.mauvaisesReponses[index]="Lieu : "+ligne.getLiteral("?localisation").getString()+" ; "+ligne.getLiteral("?victoire").getString();
					index++;
				}
			}
		}


	}

}
