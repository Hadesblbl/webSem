package fr.uha.ensisa.dbpediaquizz.questions.geographie;

import java.util.List;

import org.apache.jena.query.QuerySolution;

import fr.uha.ensisa.dbpediaquizz.questions.Question;
import fr.uha.ensisa.dbpediaquizz.util.Constantes;
import fr.uha.ensisa.dbpediaquizz.util.DBpediaQuery;

public class QuestionMonnaie extends Question{

	public QuestionMonnaie()
	{
		super(Constantes.GEOGRAPHIE);
		String requete = "select ?nomPays ?nomDevise WHERE { ?pays a <http://dbpedia.org/ontology/Country>." + 
				"?pays <http://www.w3.org/2000/01/rdf-schema#label> ?nomPays." + 
				"?pays <http://dbpedia.org/ontology/currency> ?devise." + 
				"?devise <http://fr.dbpedia.org/property/codeiso> ?nomDevise." + 
				"FILTER (lang(?nomPays)='fr')." + 
				"FILTER (lang(?nomDevise)='fr').}";
		List<QuerySolution> monnaies = DBpediaQuery.execRequete(requete);
		QuerySolution ligne = monnaies.get((int)(Math.random()*monnaies.size()));

		this.enonce = "Quelle est le code ISO de la monnaie de "+ligne.getLiteral("?nomPays").getString()+" ?";
		this.bonneReponse= ligne.getLiteral("?nomDevise").getString();

		int index=0;
		while(index<Constantes.NB_REPONSES-1)
		{
			ligne = monnaies.get((int)(Math.random()*monnaies.size()));
			if(reponseAbsente(ligne.getLiteral("?nomDevise").getString()))
			{
				this.mauvaisesReponses[index]=ligne.getLiteral("?nomDevise").getString();
				index++;
			}
		}


	}
}
