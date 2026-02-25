package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataRetriever {
    private DBConnection db ;

    public DataRetriever(DBConnection db) {
        this.db = db;
    }

    public long countAllVotes() throws SQLException {
        String sql = "SELECT COUNT(id) AS total_votes\n" +
                "FROM vote;";
        try (Connection conn = db.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getLong(1);
            }
        }
        return 0;
    }
    public List<VoteTypeCount> countVotesByType() throws SQLException {
        List<VoteTypeCount> results = new ArrayList<>();
        String sql = "SELECT vote_type, COUNT(id) AS count\n" +
                "FROM vote\n" +
                "GROUP BY vote_type;";

        try (Connection conn = db.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String type = rs.getString("vote_type");
                int count = rs.getInt("count");

                results.add(new VoteTypeCount(type , count));
            }
        }
        return results;
    }
    public List<CandidateVoteCount> countValidVotesByCandidate() throws SQLException {
        List<CandidateVoteCount> results = new ArrayList<>();

        // Le LEFT JOIN garde tous les candidats même s'il n'y a pas de correspondance dans 'vote'
        // On ajoute la condition v.vote_type = 'VALID' dans le JOIN pour ne compter que les valides
        String sql = """
                 SELECT c.name, COUNT(v.id) AS valid_vote 
                 FROM candidate c 
                 LEFT JOIN vote v ON c.id = v.candidate_id AND v.vote_type = 'VALID' 
                 GROUP BY c.id, c.name
                 """;

        try (Connection conn = db.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                results.add(new CandidateVoteCount(
                        rs.getString("name"),
                        rs.getLong("valid_vote")
                ));
            }
        }
        return results;
    }
    public VoteSummary computeVoteSummary() throws SQLException {
        // On compte sélectivement chaque type dans une seule passe sur la table
        String sql = """
        SELECT 
            COUNT(id) FILTER (WHERE vote_type = 'VALID') AS valid_count,
            COUNT(id) FILTER (WHERE vote_type = 'BLANK') AS blank_count,
            COUNT(id) FILTER (WHERE vote_type = 'NULL') AS null_count
        FROM vote
        """;

        try (Connection conn = db.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return new VoteSummary(
                        rs.getLong("valid_count"),
                        rs.getLong("blank_count"),
                        rs.getLong("null_count")
                );
            }
        }
        return new VoteSummary(0, 0, 0);
    }
    public void displayTurnoutDetails() throws SQLException {
        String sql = """
        SELECT 
            (SELECT COUNT(id) FROM voter) AS total_voters,
            (SELECT COUNT(id) FROM vote) AS total_votes,
            (COUNT(id)::FLOAT / (SELECT COUNT(id) FROM voter)) * 100 AS rate
        FROM vote
        """;

        try (Connection conn = db.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                long totalVoters = rs.getLong("total_voters");
                long totalVotes = rs.getLong("total_votes");
                double rate = rs.getDouble("rate");

                System.out.println("● Nombre total d’électeurs : " + totalVoters);
                System.out.println("● Nombre de votes enregistrés : " + totalVotes);
                System.out.println("Donc taux de participations = " + (int)rate + "%");
            }
        }
    }
    public ElectionResult findWinner() throws SQLException {
        // On sélectionne le nom et on compte les votes valides
        // On trie par le compte décroissant et on prend le premier
        String sql = """
        SELECT c.name, COUNT(v.id) AS valid_vote_count
        FROM candidate c
        JOIN vote v ON c.id = v.candidate_id
        WHERE v.vote_type = 'VALID'
        GROUP BY c.id, c.name
        ORDER BY valid_vote_count DESC
        LIMIT 1
        """;

        try (Connection conn = db.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return new ElectionResult(
                        rs.getString("name"),
                        rs.getLong("valid_vote_count")
                );
            }
        }
        return null; // Retourne null si aucun vote n'a été enregistré
    }
}
