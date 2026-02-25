package org.example;

import java.util.Objects;

public class Vote {
    private int id;
    private int candidate_id;
    private int vote_id;
    private Vote_type vote_type;

    public Vote(int id, int candidate_id, int vote_id, Vote_type vote_type) {
        this.id = id;
        this.candidate_id = candidate_id;
        this.vote_id = vote_id;
        this.vote_type = vote_type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCandidate_id() {
        return candidate_id;
    }

    public void setCandidate_id(int candidate_id) {
        this.candidate_id = candidate_id;
    }

    public int getVote_id() {
        return vote_id;
    }

    public void setVote_id(int vote_id) {
        this.vote_id = vote_id;
    }

    public Vote_type getVote_type() {
        return vote_type;
    }

    public void setVote_type(Vote_type vote_type) {
        this.vote_type = vote_type;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Vote vote = (Vote) o;
        return id == vote.id && candidate_id == vote.candidate_id && vote_id == vote.vote_id && vote_type == vote.vote_type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, candidate_id, vote_id, vote_type);
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", candidate_id=" + candidate_id +
                ", vote_id=" + vote_id +
                ", vote_type=" + vote_type +
                '}';
    }
}
