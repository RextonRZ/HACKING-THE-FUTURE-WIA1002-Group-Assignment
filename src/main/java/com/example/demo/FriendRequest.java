package com.example.demo; // Replace with your actual package

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FriendRequest {
    private static final String REQUEST_FILE = "friend_requests.csv";
    private static final String USER_FILE = "users.csv";

    public void sendFriendRequest(String fromUsername, String toUsername) throws IOException {
        List<String> requests = readAllLines(REQUEST_FILE);
        requests.add(fromUsername + "," + toUsername);
        writeAllLines(REQUEST_FILE, requests);
    }

    public void manageFriendRequests(String username, boolean accept) throws IOException {
        List<String> requests = readAllLines(REQUEST_FILE);
        List<String> updatedRequests = new ArrayList<>();

        for (String request : requests) {
            String[] parts = request.split(",");
            if (parts[1].equals(username)) {
                if (accept) {
                    addFriend(parts[0], parts[1]);
                }
            } else {
                updatedRequests.add(request);
            }
        }

        writeAllLines(REQUEST_FILE, updatedRequests);
    }

    private void addFriend(String user1, String user2) throws IOException {
        appendToFriendList(user1 + "_friendList.csv", user2);
        appendToFriendList(user2 + "_friendList.csv", user1);
    }

    private void appendToFriendList(String fileName, String friend) throws IOException {
        List<String> friends = readAllLines(fileName);
        friends.add(friend);
        writeAllLines(fileName, friends);
    }

    public List<String> viewFriendList(String username) throws IOException {
        return readAllLines(username + "_friendList.csv");
    }

    public List<String> viewFriendRequests(String username) throws IOException {
        List<String> requests = readAllLines(REQUEST_FILE);
        return requests.stream()
                .filter(request -> request.split(",")[1].equals(username))
                .map(request -> request.split(",")[0])
                .collect(Collectors.toList());
    }

    public List<String[]> searchFriends(String keyword) throws IOException {
        List<String[]> users = readCSV(USER_FILE);
        return users.stream()
                .filter(user -> user[1].contains(keyword) || user[0].contains(keyword))
                .collect(Collectors.toList());
    }

    // Utility methods
    private List<String> readAllLines(String filename) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (FileNotFoundException e) {
            // File not found, return empty list
        }
        return lines;
    }

    private void writeAllLines(String filename, List<String> lines) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        }
    }

    private List<String[]> readCSV(String filename) throws IOException {
        List<String[]> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(values);
            }
        }
        return records;
    }
}
