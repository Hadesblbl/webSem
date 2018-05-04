package fr.uha.ensisa.dbpediaquizz.questions.sport;

import java.util.List;

import org.apache.jena.query.QuerySolution;

import fr.uha.ensisa.dbpediaquizz.questions.Question;
import fr.uha.ensisa.dbpediaquizz.util.Constantes;
import fr.uha.ensisa.dbpediaquizz.util.DBpediaQuery;

public class QuestionJoueurFoot extends Question{
	public QuestionJoueurFoot() {
		super(Constantes.SPORT);
		//Récupère toutes les capitales
		String requete = "select ?nomJoueur ?nomEquipe WHERE { ?joueur a <http://dbpedia.org/ontology/SoccerPlayer>." + 
				"?joueur dbpedia-owl:currentTeam ?equipe." + 
				"?joueur dbpedia-owl:birthName ?nomJoueur." + 
				"?equipe rdfs:label ?nomEquipe" + 
				"FILTER (lang(?nomEquipe)='fr')}";
		List<QuerySolution> joueurs = DBpediaQuery.execRequete(requete);
		QuerySolution ligne = joueurs.get((int)(Math.random()*joueurs.size()));

		if(Math.random()<0.5)
		{

			this.enonce = "Dans quelle équipe est "+ligne.getLiteral("?nomJoueur").getString()+" ?";
			this.bonneReponse= ligne.getLiteral("?nomEquipe").getString();

			int index=0;
			while(index<Constantes.NB_REPONSES-1)
			{
				ligne = joueurs.get((int)(Math.random()*joueurs.size()));
				if(reponseAbsente(ligne.getLiteral("?nomEquipe").getString()))
				{
					this.mauvaisesReponses[index]=ligne.getLiteral("?nomEquipe").getString();
					index++;
				}
			}
		}

		else
		{

			this.enonce = "Lequel de ces joueurs est dans l'équipe suivante :"+ligne.getLiteral("?nomEquipe").getString()+" ?";
			this.bonneReponse= ligne.getLiteral("?nomJoueur").getString();

			int index=0;
			while(index<Constantes.NB_REPONSES-1)
			{
				ligne = joueurs.get((int)(Math.random()*joueurs.size()));
				if(!this.bonneReponse.equalsIgnoreCase(ligne.getLiteral("?nomJoueur").getString()))
				{
					this.mauvaisesReponses[index]=ligne.getLiteral("?nomJoueur").getString();
					index++;
				}
			}
		}
	}

}
