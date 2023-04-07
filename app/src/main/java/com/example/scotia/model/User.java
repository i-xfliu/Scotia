package com.example.scotia.model;

/**
 * java bean for the user info response
 */
public class User {

    private Integer id;
    private String name;
    private String avatar_url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAvatarUrl() {
        return avatar_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
