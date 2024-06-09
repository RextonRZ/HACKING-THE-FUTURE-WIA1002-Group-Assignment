package com.example.demo;

public class Quiz {

    private String title;
    private String description;
    private String theme;
    private String link;

    public Quiz(String title, String description, String theme, String link) {
        this.title = title;
        this.description = description;
        this.theme = theme;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getTheme() {
        return theme;
    }

    public String getLink() {
        return link;
    }
}
