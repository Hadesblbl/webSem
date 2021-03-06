package fr.uha.ensisa.dbpediaquizz.questions.geographie;

import java.util.List;

import org.apache.jena.query.QuerySolution;

import fr.uha.ensisa.dbpediaquizz.questions.Question;
import fr.uha.ensisa.dbpediaquizz.util.Constantes;
import fr.uha.ensisa.dbpediaquizz.util.DBpediaQuery;

public class QuestionCapitale extends Question {

	public QuestionCapitale()
	{
		super(Constantes.GEOGRAPHIE);
		//Récupère toutes les capitales
		String requete = "select ?nomPays ?nomVille where {?pays <http://dbpedia.org/ontology/capital> ?ville. "
				+ "?pays <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://dbpedia.org/ontology/Country>. "
				+ "?pays <http://www.w3.org/2000/01/rdf-schema#label> ?nomPays. "
				+ "?ville <http://www.w3.org/2000/01/rdf-schema#label> ?nomVille. "
				+ "FILTER (lang(?nomPays) = 'fr') "
				+ "FILTER (lang(?nomVille) = 'fr')}";
		List<QuerySolution> capitales = DBpediaQuery.execRequete(requete);
		QuerySolution ligne = capitales.get((int)(Math.random()*capitales.size()));

		if(Math.random()<0.5)
		{

			this.enonce = "Quelle est la capitale de "+ligne.getLiteral("?nomPays").getString()+" ?";
			this.bonneReponse= ligne.getLiteral("?nomVille").getString();

			int index=0;
			while(index<Constantes.NB_REPONSES-1)
			{
				ligne = capitales.get((int)(Math.random()*capitales.size()));
				if(reponseAbsente(ligne.getLiteral("?nomVille").getString()))
				{
					this.mauvaisesReponses[index]=ligne.getLiteral("?nomVille").getString();
					index++;
				}
			}
		}
		else
		{

			this.enonce = "De quel pays "+ligne.getLiteral("?nomVille").getString()+" est la capitale ?";
			this.bonneReponse= ligne.getLiteral("?nomPays").getString();

			int index=0;
			while(index<Constantes.NB_REPONSES-1)
			{
				ligne = capitales.get((int)(Math.random()*capitales.size()));
				if(!this.bonneReponse.equalsIgnoreCase(ligne.getLiteral("?nomPays").getString()))
				{
					this.mauvaisesReponses[index]=ligne.getLiteral("?nomPays").getString();
					index++;
				}
			}
		}


	}

}
