package fr.uha.ensisa.dbpediaquizz.questions;

import java.util.List;

import org.apache.jena.query.QuerySolution;

import fr.uha.ensisa.dbpediaquizz.util.Constantes;
import fr.uha.ensisa.dbpediaquizz.util.DBpediaQuery;

public class QuestionPaysLangue extends Question{
	public QuestionPaysLangue() {
		
	super(Constantes.GEOGRAPHIE);
	//Récupère toutes les capitales
	String requete = "select ?nomLangue ?nomPays where { ?langage a <http://dbpedia.org/ontology/Language>."
			         + "?langage dbo:spokenIn ?pays."
			         + "?langage rdfs:label ?nomLangue."
			         + "?pays rdfs:label ?nomPays."
		             + "FILTER (lang(?nomLangue) = 'fr')"
			         + "FILTER (lang(?nomPays) = 'fr')}";
	List<QuerySolution> langage = DBpediaQuery.execRequete(requete);
	QuerySolution ligne = langage.get((int)(Math.random()*langage.size()));
	
	if(Math.random()<0.5)
	{

		this.enonce = "Quelle est la langue de "+ligne.getLiteral("?nomPays").getString()+" ?";
		this.bonneReponse= ligne.getLiteral("?nomLangue").getString();

		int index=0;
		while(index<Constantes.NB_REPONSES-1)
		{
			ligne = langage.get((int)(Math.random()*langage.size()));
			if(reponseAbsente(ligne.getLiteral("?nomPays").getString()))
			{
				this.mauvaisesReponses[index]=ligne.getLiteral("?nomPays").getString();
				index++;
			}
		}
	}
	
	else
	{

		this.enonce = "Dans quelle pays la langue"+ligne.getLiteral("?nomLangue").getString()+" est t'elle utilis�e ?";
		this.bonneReponse= ligne.getLiteral("?nomPays").getString();

		int index=0;
		while(index<Constantes.NB_REPONSES-1)
		{
			ligne = langage.get((int)(Math.random()*langage.size()));
			if(!this.bonneReponse.equalsIgnoreCase(ligne.getLiteral("?nomPays").getString()))
			{
				this.mauvaisesReponses[index]=ligne.getLiteral("?nomPays").getString();
				index++;
			}
		}
	}
	
}
}
