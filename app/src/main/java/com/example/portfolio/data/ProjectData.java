package com.example.portfolio.data;

import com.example.portfolio.R;
import com.example.portfolio.data.model.Project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProjectData {
    private static ProjectData instance;
    private final List<Project> projects;

    private ProjectData() {
        projects = new ArrayList<>();

        projects.add(new Project(
                "Portfolio",
                "Personal Portfolio App showcasing projects and skills",
                R.drawable.portoflio,
                "",
                Arrays.asList("Java", "Android")
        ));

        projects.add(new Project(
                "Intelli Wealth",
                "Finance Manager App helps users track, manage, and organize finances",
                R.drawable.intelli_wealth,
                "https://github.com/Aakashch-code/Intelli-Wealth",
                Arrays.asList("Java", "SQLite")
        ));

        projects.add(new Project(
                "FitProof",
                "FitProof accurately verifies data from Google Fit for reliable tracking",
                R.drawable.fitproof,
                "https://github.com/Aakashch-code/FitProof",
                Arrays.asList("Kotlin", "Google Fit API")
        ));

        projects.add(new Project(
                "EduLens AI",
                "AI-powered learning assistant for smarter education",
                R.drawable.edulens_ai,
                "https://github.com/Aakashch-code/EduLens-AI",
                Arrays.asList("Java", "XML", "Google Sign-In", "Google ML Kit", "Gemini API", "Room Database")
        ));
    }

    public static synchronized ProjectData getInstance() {
        if (instance == null) {
            instance = new ProjectData();
        }
        return instance;
    }

    public List<Project> getProjects() {
        return new ArrayList<>(projects);
    }

    public void addProject(Project project) {
        if (project != null) {
            projects.add(project);
        }
    }
}