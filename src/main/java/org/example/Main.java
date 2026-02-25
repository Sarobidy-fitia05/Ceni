package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DBConnection db = new DBConnection();

        // On récupère la connexion une seule fois
        try (Connection conn = db.getConnection()) {
            if (conn == null) {
                System.err.println("Échec de la connexion à la base de données.");
                return;
            }

            DataRetriever data = new DataRetriever(db);

            // --- Question 1 ---
            System.out.println("***************Question 1***************");
            System.out.println("totalVote=" + data.countAllVotes());

            // --- Question 2 ---
            System.out.println("\n***************Question 2***************");
            System.out.println(data.countVotesByType());

            // --- Question 3 ---
            System.out.println("\n***************Question 3***************");
            List<CandidateVoteCount> results = data.countValidVotesByCandidate();
            System.out.print("[");
            for (int i = 0; i < results.size(); i++) {
                CandidateVoteCount c = results.get(i);
                System.out.print(c.getName() + "=" + c.getValid_vote());
                if (i < results.size() - 1) System.out.print(", ");
            }
            System.out.println("]");

            // --- Question 4 ---
            System.out.println("\n***************Question 4***************");
            System.out.println(data.computeVoteSummary());

            // --- Question 5 ---
            System.out.println("\n***************Question 5***************");
            data.displayTurnoutDetails();

            // --- Question 6 ---
            System.out.println("\n***************Question 6***************");
            ElectionResult winner = data.findWinner();
            if (winner != null) {
                System.out.println(winner.getCandidateName() + " | " + winner.getValidVoteCount());
            } else {
                System.out.println("Aucun vainqueur.");
            }

        } catch (SQLException e) {
            System.err.println("ERREUR GÉNÉRALE SQL : " + e.getMessage());
        }
    }
}