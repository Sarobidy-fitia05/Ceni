package org.example;

import java.util.Objects;

public class VoteSummary {
    private long valid_count;
    private long blank_count;
    private long null_count;

    public VoteSummary(long valid_count, long blank_count, long null_count) {
        this.valid_count = valid_count;
        this.blank_count = blank_count;
        this.null_count = null_count;
    }

    public long getValid_count() {
        return valid_count;
    }

    public void setValid_count(long valid_count) {
        this.valid_count = valid_count;
    }

    public long getBlank_count() {
        return blank_count;
    }

    public void setBlank_count(long blank_count) {
        this.blank_count = blank_count;
    }

    public long getNull_count() {
        return null_count;
    }

    public void setNull_count(long null_count) {
        this.null_count = null_count;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        VoteSummary that = (VoteSummary) o;
        return valid_count == that.valid_count && blank_count == that.blank_count && null_count == that.null_count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(valid_count, blank_count, null_count);
    }

    @Override
    public String toString() {
        return "VoteSummary{" +
                "valid_count=" + valid_count +
                ", blank_count=" + blank_count +
                ", null_count=" + null_count +
                '}';
    }
}
