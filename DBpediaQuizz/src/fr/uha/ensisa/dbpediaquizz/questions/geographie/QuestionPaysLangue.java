package fr.uha.ensisa.dbpediaquizz.questions.geographie;

import java.util.List;

import org.apache.jena.query.QuerySolution;

import fr.uha.ensisa.dbpediaquizz.questions.Question;
import fr.uha.ensisa.dbpediaquizz.util.Constantes;
import fr.uha.ensisa.dbpediaquizz.util.DBpediaQuery;

public class QuestionPaysLangue extends Question{
	public QuestionPaysLangue() {

		super(Constantes.GEOGRAPHIE);
		String requete = "select ?nomLangue ?nomPays where { ?langage a <http://dbpedia.org/ontology/Language>."
				+ "?langage <http://dbpedia.org/ontology/spokenIn> ?pays."
				+ "?langage <http://www.w3.org/2000/01/rdf-schema#label> ?nomLangue."
				+ "?pays <http://www.w3.org/2000/01/rdf-schema#label> ?nomPays."
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
				if(reponseAbsente(ligne.getLiteral("?nomLangue").getString()))
				{
					this.mauvaisesReponses[index]=ligne.getLiteral("?nomLangue").getString();
					index++;
				}
			}
		}

		else
		{

			this.enonce = "Dans quelle pays la langue "+ligne.getLiteral("?nomLangue").getString()+" est-elle utilisée ?";
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
