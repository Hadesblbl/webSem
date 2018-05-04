package fr.uha.ensisa.dbpediaquizz.questions.histoire;

import java.util.List;

import org.apache.jena.query.QuerySolution;

import fr.uha.ensisa.dbpediaquizz.questions.Question;
import fr.uha.ensisa.dbpediaquizz.util.Constantes;
import fr.uha.ensisa.dbpediaquizz.util.DBpediaQuery;

public class QuestionDynastieRoi extends Question{

	public QuestionDynastieRoi()
	{
		super(Constantes.HISTOIRE);
		String requete = "select ?nomRoi ?nomDy ?fonction {?roi a <http://dbpedia.org/ontology/Royalty>." + 
				"?roi a <http://dbpedia.org/ontology/Agent>." + 
				"?roi <http://dbpedia.org/ontology/dynasty> ?surnom." + 
				"?roi <http://www.w3.org/2000/01/rdf-schema#label> ?nomRoi." + 
				"?roi <http://fr.dbpedia.org/property/fonction> ?fonction." + 
				"?surnom <http://www.w3.org/2000/01/rdf-schema#label> ?nomDy" + 
				"FILTER (lang(?nomRoi)='fr')." + 
				"FILTER (lang(?nomDy)='fr')." + 
				"FILTER (lang(?fonction)='fr')}";
		List<QuerySolution> dynastie = DBpediaQuery.execRequete(requete);
		QuerySolution ligne = dynastie.get((int)(Math.random()*dynastie.size()));

		if(Math.random()<0.5)
		{

			this.enonce = "Quelle est la dynastie de "+ligne.getLiteral("?nomRoi").getString()+", "+ligne.getLiteral("?fonction").getString()+" ?";
			this.bonneReponse= ligne.getLiteral("?nomDy").getString();

			int index=0;
			while(index<Constantes.NB_REPONSES-1)
			{
				ligne = dynastie.get((int)(Math.random()*dynastie.size()));
				if(reponseAbsente(ligne.getLiteral("?nomDy").getString()))
				{
					this.mauvaisesReponses[index]=ligne.getLiteral("?nomDy").getString();
					index++;
				}
			}
		}
		else
		{

			this.enonce = "Quelle est la fonction de "+ligne.getLiteral("?nomRoi").getString()+" ?";
			this.bonneReponse= ligne.getLiteral("?fonction").getString();

			int index=0;
			while(index<Constantes.NB_REPONSES-1)
			{
				ligne = dynastie.get((int)(Math.random()*dynastie.size()));
				if(!this.bonneReponse.equalsIgnoreCase(ligne.getLiteral("?fonction").getString()))
				{
					this.mauvaisesReponses[index]=ligne.getLiteral("?fonction").getString();
					index++;
				}
			}
		}
	}
}
