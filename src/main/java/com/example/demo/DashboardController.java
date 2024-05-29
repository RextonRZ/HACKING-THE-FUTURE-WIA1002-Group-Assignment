package com.example.demo;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DashboardController {
    private String currentUserEmail;
    private FriendRequest friendRequest;

    public DashboardController(String currentUserEmail) {
        this.currentUserEmail = currentUserEmail;
        this.friendRequest = new FriendRequest();
    }

    public void sendFriendRequest(String receiverEmail) {
        try {
            friendRequest.sendFriendRequest(currentUserEmail, receiverEmail);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> viewPendingFriendRequests() {
        try {
            return friendRequest.viewFriendRequests(currentUserEmail);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void acceptFriendRequest(String senderEmail) {
        try {
            friendRequest.manageFriendRequests(currentUserEmail, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void rejectFriendRequest(String senderEmail) {
        try {
            friendRequest.manageFriendRequests(currentUserEmail, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String[]> searchFriends(String keyword) {
        try {
            return friendRequest.searchFriends(keyword);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void promptFriendRequest() {
        String receiverEmail = JOptionPane.showInputDialog(null, "Enter the username to send friend request:");
        if (receiverEmail != null && !receiverEmail.trim().isEmpty()) {
            sendFriendRequest(receiverEmail);
            JOptionPane.showMessageDialog(null, "Friend request sent to " + receiverEmail);
        } else {
            JOptionPane.showMessageDialog(null, "Username cannot be empty.");
        }
    }
}


