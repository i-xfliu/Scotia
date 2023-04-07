package com.example.scotia.model;

/**
 * java bean for Repositories Response
 */
public class RepoEntity {
    private String name;
    private String description;
    private String updated_at;
    private int stargazers_count;
    private int forks;

    public String getUpdate() {
        return updated_at;
    }

    public int getStars() {
        return stargazers_count;
    }

    public int getForks() {
        return forks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

}
