package com.example.portfolio.ui.projects;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.portfolio.R;
import com.example.portfolio.data.model.Project;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import java.util.List;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder> {

    private final List<Project> projectList;

    public ProjectAdapter(List<Project> projectList) {
        this.projectList = projectList;
    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_project_card, parent, false);
        return new ProjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        try {
            Project project = projectList.get(position);

            holder.projectName.setText(project.getName());
            holder.projectDescription.setText(project.getDescription());
            holder.projectImage.setImageResource(project.getImageResId());

            if (project.getDemoUrl() != null && !project.getDemoUrl().isEmpty()) {
                holder.githubButton.setVisibility(View.VISIBLE);
                holder.githubButton.setOnClickListener(v -> {
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(project.getDemoUrl()));
                        v.getContext().startActivity(intent);
                    } catch (Exception e) {
                        // Handle invalid URL or activity not found
                    }
                });
            } else {
                holder.githubButton.setVisibility(View.GONE);
            }

            holder.techBadges.removeAllViews();
            for (String tech : project.getTechList()) {
                Chip chip = new Chip(holder.itemView.getContext());
                chip.setText(tech);
                chip.setTextSize(12);
                chip.setTextColor(holder.itemView.getContext().getResources().getColor(android.R.color.holo_blue_light));
                chip.setChipBackgroundColorResource(android.R.color.background_dark);
                chip.setChipStrokeColorResource(android.R.color.holo_blue_bright);
                chip.setChipStrokeWidth(1f);
                chip.setChipStartPadding(8f);
                chip.setChipEndPadding(8f);
                chip.setEnsureMinTouchTargetSize(false);
                chip.setClickable(false);
                chip.setCheckable(false);
                holder.techBadges.addView(chip);
                Log.d("ProjectAdapter", "Added chip: " + tech); // Add this line
            }
            holder.itemView.clearAnimation();
            android.view.animation.Animation animation = android.view.animation.AnimationUtils.loadAnimation(
                    holder.itemView.getContext(), R.anim.card_entrance);
            holder.itemView.startAnimation(animation);
        } catch (Exception e) {
            // Handle potential null pointer or resource exceptions
        }
    }

    @Override
    public int getItemCount() {
        return projectList != null ? projectList.size() : 0;
    }

    public static class ProjectViewHolder extends RecyclerView.ViewHolder {
        TextView projectName, projectDescription;
        ImageView projectImage;
        ImageButton githubButton;
        ChipGroup techBadges;

        public ProjectViewHolder(@NonNull View itemView) {
            super(itemView);
            projectName = itemView.findViewById(R.id.projectName);
            projectDescription = itemView.findViewById(R.id.projectDescription);
            projectImage = itemView.findViewById(R.id.projectImage);
            githubButton = itemView.findViewById(R.id.githubButton);
            techBadges = itemView.findViewById(R.id.techBadges);
        }
    }
}