package com.example.portfolio.ui.projects;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.portfolio.R;
import com.example.portfolio.data.model.Project;
import com.example.portfolio.data.ProjectData;

import java.util.List;

public class ProjectsFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProjectAdapter adapter;
    private ProgressBar progressBar;

    public static ProjectsFragment newInstance() {
        return new ProjectsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_projects, container, false);

        recyclerView = root.findViewById(R.id.rv_featured_projects);
        progressBar = root.findViewById(R.id.progressBar);

        setupRecyclerView();
        loadProjects();

        return root;
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(true);
    }

    private void loadProjects() {
        try {
            progressBar.setVisibility(View.VISIBLE);
            List<Project> projects = ProjectData.getInstance().getProjects();
            adapter = new ProjectAdapter(projects);
            recyclerView.setAdapter(adapter);
            progressBar.setVisibility(View.GONE);
        } catch (Exception e) {
            progressBar.setVisibility(View.GONE);
            // Handle error (e.g., show error message)
        }
    }
}