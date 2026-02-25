package org.example;

import java.util.Objects;

public class VoteTypeCount {
    private String voteType ;
    private int count ;

    public VoteTypeCount(String voteType, int count) {
        this.voteType = voteType;
        this.count = count;
    }

    public String getVoteType() {
        return voteType;
    }

    public void setVoteType(String voteType) {
        this.voteType = voteType;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        VoteTypeCount that = (VoteTypeCount) o;
        return count == that.count && Objects.equals(voteType, that.voteType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(voteType, count);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "VoteTypeCount{" +
                "voteType='" + voteType + '\'' +
                ", count=" + count +
                '}';
    }
}
