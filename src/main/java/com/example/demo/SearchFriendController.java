package com.example.demo;
import java.util.ArrayList;
import java.util.List;

public class SearchFriendController {
    public List<String[]> searchFriend(String keyword) {
        List<String[]> records = CSVHandler.readCSV();
        List<String[]> searchResults = new ArrayList<>();
        for (String[] record : records) {
            if (record[1].contains(keyword)) {
                searchResults.add(record);
            }
        }
        return searchResults;
    }
}
