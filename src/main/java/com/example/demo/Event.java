package com.example.demo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Event {
    private String title;
    private String description;
    private String venue;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;




    public Event(String title, String description, String venue, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.title = title;
        this.description = description;
        this.venue = venue;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getVenue() {
        return venue;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

}
