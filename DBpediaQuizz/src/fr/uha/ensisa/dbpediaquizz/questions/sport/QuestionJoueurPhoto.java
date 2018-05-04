package fr.uha.ensisa.dbpediaquizz.questions.sport;

import java.util.List;

import org.apache.jena.query.QuerySolution;

import fr.uha.ensisa.dbpediaquizz.questions.Question;
import fr.uha.ensisa.dbpediaquizz.util.Constantes;
import fr.uha.ensisa.dbpediaquizz.util.DBpediaQuery;

public class QuestionJoueurPhoto extends Question{
	public QuestionJoueurPhoto() {
		super(Constantes.SPORT);
		String requete = "select ?nomJoueur ?photoJoueur where {?joueur a <http://dbpedia.org/ontology/SoccerPlayer>." + 
				"?joueur <http://www.w3.org/2000/01/rdf-schema#label> ?nomJoueur." + 
				"?joueur <http://dbpedia.org/ontology/thumbnail> ?photoJoueur." + 
				"FILTER (lang(?nomJoueur)='fr')}";
		List<QuerySolution> joueurs = DBpediaQuery.execRequete(requete);
		QuerySolution ligne = joueurs.get((int)(Math.random()*joueurs.size()));

		if(Math.random()<0.5)
		{

			this.enonce = "Quel est le nom de ce joueur ?";//TODO ajouter la photo du joueur d'une manière ou d'une autre
			this.bonneReponse= ligne.getLiteral("?nomJoueur").getString();

			int index=0;
			while(index<Constantes.NB_REPONSES-1)
			{
				ligne = joueurs.get((int)(Math.random()*joueurs.size()));
				if(reponseAbsente(ligne.getLiteral("?nomJoueur").getString()))
				{
					this.mauvaisesReponses[index]=ligne.getLiteral("?nomJoueur").getString();
					index++;
				}
			}
		}

		else
		{

			this.enonce = "Lequel de ces joueurs est "+ligne.getLiteral("?nomJoueur").getString()+" ?";
			this.bonneReponse= ligne.getResource("?photoJoueur").getURI();
			
			int index=0;
			while(index<Constantes.NB_REPONSES-1)
			{
				ligne = joueurs.get((int)(Math.random()*joueurs.size()));
				if(!this.bonneReponse.equalsIgnoreCase(ligne.getResource("?photoJoueur").getURI()))
				{
					this.mauvaisesReponses[index]=ligne.getResource("?photoJoueur").getURI();
					index++;
				}
			}
		}
	}

}

