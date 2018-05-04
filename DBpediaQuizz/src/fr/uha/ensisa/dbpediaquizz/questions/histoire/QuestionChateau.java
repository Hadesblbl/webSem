package fr.uha.ensisa.dbpediaquizz.questions.histoire;

import java.util.List;

import org.apache.jena.query.QuerySolution;

import fr.uha.ensisa.dbpediaquizz.questions.Question;
import fr.uha.ensisa.dbpediaquizz.util.Constantes;
import fr.uha.ensisa.dbpediaquizz.util.DBpediaQuery;

public class QuestionChateau extends Question{

	public QuestionChateau()
	{
		super(Constantes.HISTOIRE);
		//Récupère toutes les capitales
		String requete = "select ?nomChateau ?pays ?nomProp where { ?chateau a <http://dbpedia.org/ontology/Castle>." + 
				"                                       ?chateau prop-fr:propriétaireInitial ?prop." + 
				"                                       ?chateau prop-fr:pays ?pays." + 
				"                                       ?chateau rdfs:label ?nomChateau." + 
				"                                       ?prop rdfs:label ?nomProp " + 
				"                                       FILTER (lang(?nomChateau)='fr')." + 
				"                                       FILTER (lang(?pays)='fr')." + 
				"                                       FILTER (lang(?nomProp)='fr').}";
		List<QuerySolution> chateaux = DBpediaQuery.execRequete(requete);
		QuerySolution ligne = chateaux.get((int)(Math.random()*chateaux.size()));

		if(Math.random()<0.5)
		{

			this.enonce = "A qui appartenait initilament le "+ligne.getLiteral("?nomChateau").getString()+" situé en "+ligne.getLiteral("?pays").getString()+" ?";
			this.bonneReponse= ligne.getLiteral("?nomProp").getString();

			int index=0;
			while(index<Constantes.NB_REPONSES-1)
			{
				ligne = chateaux.get((int)(Math.random()*chateaux.size()));
				if(reponseAbsente(ligne.getLiteral("?nomProp").getString()))
				{
					this.mauvaisesReponses[index]=ligne.getLiteral("?nomProp").getString();
					index++;
				}
			}
		}
		else
		{

			this.enonce = "Quel château situé en "+ligne.getLiteral("?pays").getString()+"appartenait inititialement à "+ligne.getLiteral("?nomProp").getString()+ "?";
			this.bonneReponse= ligne.getLiteral("?nomChateau").getString();

			int index=0;
			while(index<Constantes.NB_REPONSES-1)
			{
				ligne = chateaux.get((int)(Math.random()*chateaux.size()));
				if(!this.bonneReponse.equalsIgnoreCase(ligne.getLiteral("?nomChateau").getString()))
				{
					this.mauvaisesReponses[index]=ligne.getLiteral("?nomChateau").getString();
					index++;
				}
			}
		}


	}
}
