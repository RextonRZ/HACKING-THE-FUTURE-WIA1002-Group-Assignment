package com.example.demo;

import java.util.List;

public class User {
    private String email,password;
    public String username;
    private Role role;
    private List<User> parents; // For young students
    private List<User> children; // For parents
    private LocationCoordinate locationCoordinate;
    private int currentPoints;
    private List<String> friends; // Add this field

    public User(String email, String username, String password, Role role) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(String username, String email, String password, Role role, LocationCoordinate coordinate, List<String> friends) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.locationCoordinate = coordinate;
        this.friends = friends;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public List<User> getParents() {
        return parents;
    }

    public List<User> getChildren() {
        return children;
    }

    public LocationCoordinate getLocationCoordinate() {
        return locationCoordinate;
    }

    public int getCurrentPoints() {
        return currentPoints;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setParents(List<User> parents) {
        this.parents = parents;
    }

    public void setChildren(List<User> children) {
        this.children = children;
    }

    public void setLocationCoordinate(LocationCoordinate locationCoordinate) {
        this.locationCoordinate = locationCoordinate;
    }

    public void setCurrentPoints(int currentPoints) {
        this.currentPoints = currentPoints;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }
}

enum Role {
    Young_Students, Parent, Educators
}

class LocationCoordinate {
    private double latitude, longitude;

    public LocationCoordinate(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
