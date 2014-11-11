package ru.ifmo.md.colloquium2;

/**
 * Created by Миша on 11.11.2014.
 */
public class Candidate {
    private String name = null;
    private int votes = 0;
    private int percentage = 0;

    Candidate(String name) {
        this.name = name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public void setVotes(int value) {
        this.votes = value;
    }

    public void setPercentage(int value) {
        this.percentage = value;
    }

    public String getName() {
        return this.name;
    }

    public String getVotes() {
        return Integer.toString(this.votes);
    }

    public String getPercentage() {
        return Integer.toString(this.percentage);
    }

    public void increaseVotes() {
        this.votes++;
    }
}
