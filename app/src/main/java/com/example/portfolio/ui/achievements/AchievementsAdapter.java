package com.example.portfolio.ui.achievements;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.portfolio.R;
import com.example.portfolio.data.model.Achievement;
import com.example.portfolio.data.model.AchievementItem;

import java.util.List;

public class AchievementsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_SIMPLE_ITEM = 1;
    private static final int TYPE_ITEM_WITH_NESTED = 2;
    private static final int TYPE_STATS = 3;

    private List<Object> items;
    private Context context;

    public AchievementsAdapter(Context context, List<Object> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        Object item = items.get(position);
        if (item instanceof String) {
            String type = (String) item;
            if ("HEADER".equals(type)) {
                return TYPE_HEADER;
            } else if ("STATS".equals(type)) {
                return TYPE_STATS;
            }
        } else if (item instanceof Achievement) {
            Achievement achievement = (Achievement) item;
            if (achievement.getItems() != null && !achievement.getItems().isEmpty()) {
                return TYPE_ITEM_WITH_NESTED;
            } else {
                return TYPE_SIMPLE_ITEM;
            }
        }
        return -1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case TYPE_HEADER:
                return new HeaderViewHolder(inflater.inflate(R.layout.item_achievement_header, parent, false));
            case TYPE_SIMPLE_ITEM:
                return new SimpleItemViewHolder(inflater.inflate(R.layout.item_achievement, parent, false));
            case TYPE_ITEM_WITH_NESTED:
                return new NestedItemViewHolder(inflater.inflate(R.layout.item_achievement_with_items, parent, false));
            case TYPE_STATS:
                return new StatsViewHolder(inflater.inflate(R.layout.item_achievement_stats, parent, false));
            default:
                throw new IllegalArgumentException("Invalid view type");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case TYPE_HEADER:
                // Header doesn't need binding
                break;
            case TYPE_SIMPLE_ITEM:
                Achievement achievement = (Achievement) items.get(position);
                ((SimpleItemViewHolder) holder).bind(achievement);
                break;
            case TYPE_ITEM_WITH_NESTED:
                Achievement nestedAchievement = (Achievement) items.get(position);
                ((NestedItemViewHolder) holder).bind(nestedAchievement);
                break;
            case TYPE_STATS:
                // Stats doesn't need binding
                break;
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // ViewHolder for header
    static class HeaderViewHolder extends RecyclerView.ViewHolder {
        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    // ViewHolder for simple achievement items
    static class SimpleItemViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private View iconContainer;
        private ImageView icon;
        private TextView title, subtitle, description;

        public SimpleItemViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
            iconContainer = itemView.findViewById(R.id.icon_container);
            icon = itemView.findViewById(R.id.icon);
            title = itemView.findViewById(R.id.title);
            subtitle = itemView.findViewById(R.id.subtitle);
            description = itemView.findViewById(R.id.description);
        }

        public void bind(Achievement achievement) {
            iconContainer.setBackgroundResource(achievement.getBackgroundResId());
            icon.setImageResource(achievement.getIconResId());
            icon.setColorFilter(achievement.getIconTint());
            title.setText(achievement.getTitle());
            subtitle.setText(achievement.getSubtitle());
            description.setText(achievement.getDescription());
        }
    }

    // ViewHolder for achievements with nested items
    static class NestedItemViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private View iconContainer;
        private ImageView icon;
        private TextView title;
        private RecyclerView nestedRecyclerView;

        public NestedItemViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
            iconContainer = itemView.findViewById(R.id.icon_container);
            icon = itemView.findViewById(R.id.icon);
            title = itemView.findViewById(R.id.title);
            nestedRecyclerView = itemView.findViewById(R.id.nested_recycler_view);
        }

        public void bind(Achievement achievement) {
            iconContainer.setBackgroundResource(achievement.getBackgroundResId());
            icon.setImageResource(achievement.getIconResId());
            icon.setColorFilter(achievement.getIconTint());
            title.setText(achievement.getTitle());

            // Set up nested RecyclerView
            NestedAchievementAdapter adapter = new NestedAchievementAdapter(achievement.getItems());
            nestedRecyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
            nestedRecyclerView.setAdapter(adapter);
        }
    }

    // ViewHolder for statistics
    static class StatsViewHolder extends RecyclerView.ViewHolder {
        public StatsViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    // Adapter for nested items
    static class NestedAchievementAdapter extends RecyclerView.Adapter<NestedAchievementAdapter.NestedViewHolder> {
        private List<AchievementItem> items;

        public NestedAchievementAdapter(List<AchievementItem> items) {
            this.items = items;
        }

        @NonNull
        @Override
        public NestedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_nested_achievement, parent, false);
            return new NestedViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull NestedViewHolder holder, int position) {
            AchievementItem item = items.get(position);
            holder.title.setText(item.getTitle());
            holder.subtitle.setText(item.getSubtitle());
            holder.description.setText(item.getDescription());

            if (item.getStatus() != null && !item.getStatus().isEmpty()) {
                holder.status.setVisibility(View.VISIBLE);
                holder.status.setText(item.getStatus());
                holder.status.setTextColor(item.getStatusColor());
            } else {
                holder.status.setVisibility(View.GONE);
            }
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        static class NestedViewHolder extends RecyclerView.ViewHolder {
            TextView title, subtitle, description, status;

            public NestedViewHolder(@NonNull View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.nested_title);
                subtitle = itemView.findViewById(R.id.nested_subtitle);
                description = itemView.findViewById(R.id.nested_description);
                status = itemView.findViewById(R.id.nested_status);
            }
        }
    }
}