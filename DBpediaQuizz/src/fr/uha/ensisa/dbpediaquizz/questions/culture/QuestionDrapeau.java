package fr.uha.ensisa.dbpediaquizz.questions.culture;

import java.util.List;

import org.apache.jena.query.QuerySolution;

import fr.uha.ensisa.dbpediaquizz.questions.Question;
import fr.uha.ensisa.dbpediaquizz.util.Constantes;
import fr.uha.ensisa.dbpediaquizz.util.DBpediaQuery;

public class QuestionDrapeau extends Question{

	public QuestionDrapeau() {
		super(Constantes.CULTURE);
		String requete = "select ?nomPays ?drapeau  where {?pays a <http://dbpedia.org/ontology/Country>." + 
				"?pays <http://dbpedia.org/ontology/thumbnail> ?drapeau." + 
				"?pays <http://www.w3.org/2000/01/rdf-schema#label> ?nomPays." + 
				"FILTER (lang(?nomPays)='fr').}";
		List<QuerySolution> drapeaux = DBpediaQuery.execRequete(requete);
		QuerySolution ligne = drapeaux.get((int)(Math.random()*drapeaux.size()));

		this.enonce = "Quel est le drapeau de "+ligne.getLiteral("?nomPays").getString()+" ?";
		this.bonneReponse= ligne.getResource("?drapeau").getURI();

		int index=0;
		while(index<Constantes.NB_REPONSES-1)
		{
			ligne = drapeaux.get((int)(Math.random()*drapeaux.size()));
			if(reponseAbsente(ligne.getResource("?drapeau").getURI()))
			{
				this.mauvaisesReponses[index]=ligne.getResource("?drapeau").getURI();
				index++;
			}
		}

	}

}
