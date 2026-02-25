package org.example;

import java.util.Objects;

public class CandidateVoteCount {
    private String name;
    private long valid_vote ;

    public CandidateVoteCount(String name, long valid_vote) {
        this.name = name;
        this.valid_vote = valid_vote;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getValid_vote() {
        return valid_vote;
    }

    public void setValid_vote(long valid_vote) {
        this.valid_vote = valid_vote;
    }

    @Override
    public String toString() {
        return "CandidateVoteCount{" +
                "name='" + name + '\'' +
                ", valid_vote=" + valid_vote +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CandidateVoteCount that = (CandidateVoteCount) o;
        return valid_vote == that.valid_vote && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, valid_vote);
    }
}
