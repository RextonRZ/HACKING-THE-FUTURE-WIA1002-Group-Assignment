package com.example.demo;


import java.time.LocalDateTime;

public class Student {
    private String username;
    private int points;
    private LocalDateTime pointsLastUpdated;

    public Student(String username, int points, LocalDateTime pointsLastUpdated) {
        this.username = username;
        this.points = points;
        this.pointsLastUpdated = pointsLastUpdated;
    }

    public String getUsername() {
        return username;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int additionalPoints) {
        this.points += additionalPoints;
        this.pointsLastUpdated = LocalDateTime.now();
    }

    public LocalDateTime getPointsLastUpdated() {
        return pointsLastUpdated;
    }
}
