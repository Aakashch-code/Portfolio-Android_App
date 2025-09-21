package com.example.portfolio.data.model;

import java.util.List;

public class Project {
    private final String name;
    private final String description;
    private final int imageResId;
    private final String demoUrl;
    private final List<String> techList;

    public Project(String name, String description, int imageResId, String demoUrl, List<String> techList) {
        this.name = name;
        this.description = description;
        this.imageResId = imageResId;
        this.demoUrl = demoUrl;
        this.techList = techList;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getDemoUrl() {
        return demoUrl;
    }

    public List<String> getTechList() {
        return techList;
    }
}