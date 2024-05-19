/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo;
import java.util.List;

/**
 *
 * @author ruizh
 */
public class User {
    private String email, username, passsword;
    private Role role;
    private List<User> parents; // For young students
    private List<User> children; // For parents
    private LocationCoordinate locationCoordinate;
    private int currentPoints;

    public User(String email, String username, String passsword, Role role) {
        this.email = email;
        this.username = username;
        this.passsword = passsword;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPasssword() {
        return passsword;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPasssword(String passsword) {
        this.passsword = passsword;
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
}

enum Role{
    Young_Students, Parent, Educators
}

class LocationCoordinate{
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
