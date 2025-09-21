// AchievementItem.java
package com.example.portfolio.data.model;

public class AchievementItem {
    private String title;
    private String subtitle;
    private String description;
    private String status;
    private int statusColor;

    public AchievementItem(String title, String subtitle, String description,
                           String status, int statusColor) {
        this.title = title;
        this.subtitle = subtitle;
        this.description = description;
        this.status = status;
        this.statusColor = statusColor;
    }

    // Getters
    public String getTitle() { return title; }
    public String getSubtitle() { return subtitle; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }
    public int getStatusColor() { return statusColor; }
}