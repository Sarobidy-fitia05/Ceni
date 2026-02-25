package org.example;

import java.util.Objects;

public class ElectionResult {
    private String candidateName;
    private long validVoteCount;

    public ElectionResult(String candidateName, long validVoteCount) {
        this.candidateName = candidateName;
        this.validVoteCount = validVoteCount;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ElectionResult that = (ElectionResult) o;
        return validVoteCount == that.validVoteCount && Objects.equals(candidateName, that.candidateName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(candidateName, validVoteCount);
    }

    public long getValidVoteCount() {
        return validVoteCount;
    }

    public void setValidVoteCount(long validVoteCount) {
        this.validVoteCount = validVoteCount;
    }
}
