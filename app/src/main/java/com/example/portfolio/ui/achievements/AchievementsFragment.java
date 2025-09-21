package com.example.portfolio.ui.achievements;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.portfolio.R;
import com.example.portfolio.data.model.Achievement;
import com.example.portfolio.data.model.AchievementItem;

import java.util.ArrayList;
import java.util.List;

public class AchievementsFragment extends Fragment {


    private RecyclerView recyclerView;
    private AchievementsAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_achievements, container, false);

        recyclerView = root.findViewById(R.id.achievements_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Prepare data
        List<Object> items = new ArrayList<>();
        items.add("HEADER"); // Header marker

        // Add achievements
        items.addAll(getAchievements());

        items.add("STATS"); // Stats marker

        adapter = new AchievementsAdapter(getContext(), items);
        recyclerView.setAdapter(adapter);

        return root;
    }

    private List<Achievement> getAchievements() {
        List<Achievement> achievements = new ArrayList<>();

        // Awards achievement
        List<AchievementItem> awardsItems = new ArrayList<>();
        achievements.add(new Achievement(
                1, "awards", "Awards", "TBD", "TBD",
                R.drawable.ic_star, getResources().getColor(R.color.gold_tint),
                R.drawable.gradient_gold_circle, null, 0, awardsItems
        ));

        // Hackathons achievement
        List<AchievementItem> hackathonItems = new ArrayList<>();
        hackathonItems.add(new AchievementItem(
                "Proof Of Concept (Xion)",
                "Participated in developing a Verifier App Using APIs",
                "",
                "✅ COMPLETED",
                getResources().getColor(R.color.status_completed)
        ));
        hackathonItems.add(new AchievementItem(
                "Next Challenge Awaits",
                "Preparing for upcoming hackathon competitions",
                "",
                "🔄 COMING SOON",
                getResources().getColor(R.color.status_pending)
        ));

        achievements.add(new Achievement(
                2, "hackathons", "🏆 Hackathon Journey", "", "",
                R.drawable.ic_trophy, getResources().getColor(R.color.orange_tint),
                R.drawable.gradient_orange_circle, null, 0, hackathonItems
        ));

        // Certifications achievement
        List<AchievementItem> certItems = new ArrayList<>();
        certItems.add(new AchievementItem(
                "AI Agent Studio Foundations",
                "Oracle Fusion AI Agent Studio Foundations Associate - Rel 1",
                "Foundational expertise in AI Agent Studio development",
                null, 0
        ));
        certItems.add(new AchievementItem(
                "Cloud AI Foundations",
                "Oracle Cloud Infrastructure 2025 AI Foundations Associate",
                "Comprehensive knowledge of Oracle AI and Cloud Infrastructure",
                null, 0
        ));
        certItems.add(new AchievementItem(
                "OCI GenAI Professional",
                "Oracle Cloud Infrastructure 2025 Certified Generative AI Professional",
                "Comprehensive knowledge of Oracle AI and GenAI",
                null, 0
        ));
        certItems.add(new AchievementItem(
                "OCI Developer Professional",
                "Developing applications using Oracle Cloud Infrastructure",
                "Comprehensive knowledge of Oracle Cloud Infrastructure",
                null, 0
        ));

        achievements.add(new Achievement(
                3, "certifications", "🎓 Oracle Certifications", "", "",
                R.drawable.ic_certificate, getResources().getColor(R.color.cyan_tint),
                R.drawable.gradient_cyan_circle, null, 0, certItems
        ));

        // Open Source achievement
        List<AchievementItem> openSourceItems = new ArrayList<>();
        achievements.add(new Achievement(
                4, "opensource", "💻 Open Source Champion",
                "GitHub Community Recognition",
                "10+ contributions to popular Android libraries and frameworks",
                R.drawable.ic_code, getResources().getColor(R.color.green_tint),
                R.drawable.gradient_green_circle, null, 0, openSourceItems
        ));

        return achievements;
    }
}