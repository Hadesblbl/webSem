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
		String requete = "select ?nomChateau ?pays ?nomProp where { ?chateau a <http://dbpedia.org/ontology/Castle>." + 
				"?chateau <http://fr.dbpedia.org/property/propriétaireInitial> ?prop." + 
				"?chateau <http://fr.dbpedia.org/property/pays> ?pays." + 
				"?chateau <http://www.w3.org/2000/01/rdf-schema#label> ?nomChateau." + 
				"?prop <http://www.w3.org/2000/01/rdf-schema#label> ?nomProp " + 
				"FILTER (lang(?nomChateau)='fr')." + 
				"FILTER (lang(?pays)='fr')." + 
				"FILTER (lang(?nomProp)='fr').}";
		List<QuerySolution> chateaux = DBpediaQuery.execRequete(requete);
		QuerySolution ligne = chateaux.get((int)(Math.random()*chateaux.size()));

		if(Math.random()<0.5)
		{

			this.enonce = "À qui appartenait initialement le "+ligne.getLiteral("?nomChateau").getString()+" situé en "+ligne.getLiteral("?pays").getString()+" ?";
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

			this.enonce = "Quel château situé en "+ligne.getLiteral("?pays").getString()+" appartenait inititialement à "+ligne.getLiteral("?nomProp").getString()+ "?";
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
