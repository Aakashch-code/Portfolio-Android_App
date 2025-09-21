package com.example.portfolio.ui.contact;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.portfolio.R;

public class ContactFragment extends Fragment {

    public static ContactFragment newInstance() {
        return new ContactFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);

        LinearLayout layoutEmail = view.findViewById(R.id.layoutEmail);
        ImageButton btnLinkedIn = view.findViewById(R.id.btnLinkedIn);
        ImageButton btnGitHub = view.findViewById(R.id.btnGitHub);
        ImageButton btnTwitter = view.findViewById(R.id.btnTwitter);
        ImageButton btnInstagram = view.findViewById(R.id.btnInstagram);

        layoutEmail.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:aakashch.code@gmail.com"));
            startActivity(Intent.createChooser(intent, "Send Email"));
        });

        btnLinkedIn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.linkedin.com/in/aakash-chauhan-521a65379/"));
            startActivity(intent);
        });

        btnGitHub.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://github.com/Aakashch-code"));
            startActivity(intent);
        });

        btnTwitter.setOnClickListener(v -> {
            String twitterUrl = "https://twitter.com/Aakashch_code"; // fallback to old twitter URL
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(twitterUrl));
            try {
                startActivity(intent);
            } catch (android.content.ActivityNotFoundException e) {
                // If no app can handle the intent, show a toast or do nothing
                Toast.makeText(getContext(), "No app found to open Twitter", Toast.LENGTH_SHORT).show();
            }
        });



        btnInstagram.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.instagram.com/aakashch_90/?hl=en"));
            startActivity(intent);
        });

        return view;
    }
}