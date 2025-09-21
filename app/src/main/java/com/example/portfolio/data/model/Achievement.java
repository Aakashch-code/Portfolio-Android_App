// Achievement.java
package com.example.portfolio.data.model;

import java.util.List;

public class Achievement {
    private int id;
    private String type;
    private String title;
    private String subtitle;
    private String description;
    private int iconResId;
    private int iconTint;
    private int backgroundResId;
    private String status;
    private int statusColor;
    private List<AchievementItem> items;

    public Achievement(int id, String type, String title, String subtitle, String description,
                       int iconResId, int iconTint, int backgroundResId,
                       String status, int statusColor, List<AchievementItem> items) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.subtitle = subtitle;
        this.description = description;
        this.iconResId = iconResId;
        this.iconTint = iconTint;
        this.backgroundResId = backgroundResId;
        this.status = status;
        this.statusColor = statusColor;
        this.items = items;
    }

    // Getters
    public int getId() { return id; }
    public String getType() { return type; }
    public String getTitle() { return title; }
    public String getSubtitle() { return subtitle; }
    public String getDescription() { return description; }
    public int getIconResId() { return iconResId; }
    public int getIconTint() { return iconTint; }
    public int getBackgroundResId() { return backgroundResId; }
    public String getStatus() { return status; }
    public int getStatusColor() { return statusColor; }
    public List<AchievementItem> getItems() { return items; }
}