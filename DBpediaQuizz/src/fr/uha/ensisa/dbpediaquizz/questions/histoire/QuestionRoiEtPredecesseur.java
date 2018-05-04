package fr.uha.ensisa.dbpediaquizz.questions.histoire;

import java.util.List;

import org.apache.jena.query.QuerySolution;

import fr.uha.ensisa.dbpediaquizz.questions.Question;
import fr.uha.ensisa.dbpediaquizz.util.Constantes;
import fr.uha.ensisa.dbpediaquizz.util.DBpediaQuery;

public class QuestionRoiEtPredecesseur extends Question {

	public QuestionRoiEtPredecesseur()
	{
		super(Constantes.HISTOIRE);
		//Récupère tous les rois et leurs prédecesseurs
		String requete = "select distinct ?nomRoi ?nomPredecesseur where {?roi a <http://dbpedia.org/ontology/Royalty>. "
				+ "?roi <http://fr.dbpedia.org/property/prédécesseur> ?pred. "
				+ "?roi <http://www.w3.org/2000/01/rdf-schema#label> ?nomRoi. "
				+ "?pred <http://www.w3.org/2000/01/rdf-schema#label> ?nomPredecesseur. "
				+ "FILTER(lang(?nomRoi)='fr'). "
				+ "FILTER(lang(?nomPredecesseur)='fr').}";
		List<QuerySolution> lignees = DBpediaQuery.execRequete(requete);
		QuerySolution ligne = lignees.get((int)(Math.random()*lignees.size()));

		if(Math.random()<0.5)
		{

			this.enonce = "Quel est le prédecesseur du roi "+ligne.getLiteral("?nomRoi").getString()+" ?";
			this.bonneReponse= ligne.getLiteral("?nomPredecesseur").getString();

			int index=0;
			while(index<Constantes.NB_REPONSES-1)
			{
				ligne = lignees.get((int)(Math.random()*lignees.size()));
				if(reponseAbsente(ligne.getLiteral("?nomPredecesseur").getString()))
				{
					this.mauvaisesReponses[index]=ligne.getLiteral("?nomPredecesseur").getString();
					index++;
				}
			}
		}
		else
		{

			this.enonce = "Quel est le successeur du roi "+ligne.getLiteral("?nomPredecesseur").getString()+" ?";
			this.bonneReponse= ligne.getLiteral("?nomRoi").getString();

			int index=0;
			while(index<Constantes.NB_REPONSES-1)
			{
				ligne = lignees.get((int)(Math.random()*lignees.size()));
				if(!this.bonneReponse.equalsIgnoreCase(ligne.getLiteral("?nomRoi").getString()))
				{
					this.mauvaisesReponses[index]=ligne.getLiteral("?nomRoi").getString();
					index++;
				}
			}
		}

	}

}
