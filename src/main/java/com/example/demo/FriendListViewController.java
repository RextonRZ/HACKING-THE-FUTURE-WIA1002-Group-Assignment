package com.example.demo; // Replace with your actual package

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FriendListViewController {
    @FXML
    private Stage stage;
    @FXML
    private TableView<User> friendListTable;

    private final friendRequestController friendRequestController = new friendRequestController();

    public void friendListStartUp(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/friendList.fxml")); // Adjust the path as needed
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(scene);
    }

    public void loadFriendList(String username) throws IOException {
        TableColumn<User, String> usernameCol = new TableColumn<>("Username");
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));

        friendListTable.getColumns().clear();
        friendListTable.getColumns().add(usernameCol);

        List<String> friends = friendRequestController.viewFriendList(username);
        for (String friend : friends) {
            User friendUser = findUserByUsername(friend);
            if (friendUser != null) {
                friendListTable.getItems().add(friendUser);
            }
        }
    }

    private User findUserByUsername(String username) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("users.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username)) {
                    Role role = Role.valueOf(parts[3]);
                    LocationCoordinate coordinate = new LocationCoordinate(Double.parseDouble(parts[4].split(",")[0]), Double.parseDouble(parts[4].split(",")[1]));
                    List<String> friends = Arrays.asList(parts[5].split(":"));
                    return new User(parts[1], parts[0], parts[2], role, coordinate, friends);
                }
            }
        }
        return null;
    }
}
