package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class FriendListController {
    @FXML
    private TableView<User> friendListTable;

    private friendRequestController friendRequestController = new friendRequestController();

    @FXML
    public void friendListStartUp(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("friendList.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        loadFriendList(username);
    }

    public void loadFriendList(String username) throws IOException {
        friendListTable.getColumns().clear();

        TableColumn<User, String> usernameCol = new TableColumn<>("Username");
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        friendListTable.getColumns().add(usernameCol);

        ObservableList<User> friendItems = FXCollections.observableArrayList();
        List<String> friends = friendRequestController.viewFriendList(username);
        for (String friend : friends) {
            User friendUser = findUserByUsername(friend);
            if (friendUser != null) {
                friendItems.add(friendUser);
            }
        }
        friendListTable.setItems(friendItems);
    }

    private User findUserByUsername(String username) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/Data/user.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username)) {
                    return createUserFromCSVLine(parts);
                }
            }
        }
        return null;
    }

    private User createUserFromCSVLine(String[] parts) {
        Role role = Role.valueOf(parts[3]);
        LocationCoordinate coordinate = new LocationCoordinate(Double.parseDouble(parts[4]), Double.parseDouble(parts[5]));
        List<String> friends = Arrays.asList(parts[6].split(":"));
        return new User(parts[0], parts[1], parts[2], role, coordinate, friends);
    }


    @FXML
    public void homeButton(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("homePage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene homeScene = new Scene(root);
        stage.setScene(homeScene);
    }


}
