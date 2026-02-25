package org.example;

import java.sql.SQLException;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        DBConnection db = new DBConnection();
        DataRetriever data = new DataRetriever(db);

        try {
            System.out.println("***************Question 1***************");
            long total = data.countAllVotes();
            System.out.println("totalVote=" + total);

        } catch (SQLException e) {
            System.err.println("Erreur de base de données : " + e.getMessage());
        }
        try {
            System.out.println("***************Question 2***************");
            List<VoteTypeCount> list = data.countVotesByType();
            System.out.println(list);

        } catch (SQLException e) {
            System.err.println("Erreur de base de données : " + e.getMessage());
        }
        try {

            System.out.println("***************Question 3***************");
            List<CandidateVoteCount> results = data.countValidVotesByCandidate();

            // Affichage personnalisé
            System.out.print("[");
            for (int i = 0; i < results.size(); i++) {
                CandidateVoteCount c = results.get(i);
                System.out.print(c.getName() + "=" + c.getValid_vote());
                if (i < results.size() - 1) System.out.print(", ");
            }
            System.out.println("]");

        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getMessage());
        }

        try {

            System.out.println("***************Question 4***************");
            VoteSummary summary = data.computeVoteSummary();

            // L'affichage du record appellera automatiquement sa méthode toString()
            System.out.println(summary);
            // Résultat attendu : VoteSummary[validCount=3, blankCount=2, nullCount=1]

        } catch (SQLException e) {
            System.err.println("Erreur : " + e.getMessage());
        }

        try {

            System.out.println("***************Question 5***************");
            double rate = data.computeTurnoutRate();

            // Affichage simple
            System.out.println("Taux de participation = " + (int)rate + "%");

        } catch (SQLException e) {
            System.err.println("Erreur : " + e.getMessage());
        }

        try {

            System.out.println("***************Question 6***************");
            ElectionResult winner = data.findWinner();

            if (winner != null) {
                System.out.println(winner.getCandidateName() + " | " + winner.getValidVoteCount());
            } else {
                System.out.println("Aucun vainqueur (pas de votes valides).");
            }

        } catch (SQLException e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }
}