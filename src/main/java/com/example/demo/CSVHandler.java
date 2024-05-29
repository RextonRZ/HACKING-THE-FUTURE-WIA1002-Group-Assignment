package com.example.demo;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVHandler {
    private static final String CSV_FILE = "users.csv";

    public static List<String[]> readCSV() {
        List<String[]> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

    public static void writeCSV(List<String[]> data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CSV_FILE))) {
            for (String[] record : data) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < record.length; i++) {
                    sb.append(record[i]);
                    if (i < record.length - 1) {
                        sb.append(",");
                    }
                }
                bw.write(sb.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
