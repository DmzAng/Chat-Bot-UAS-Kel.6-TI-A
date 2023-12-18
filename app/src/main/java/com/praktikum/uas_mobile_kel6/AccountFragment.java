package com.praktikum.uas_mobile_kel6;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class AccountFragment extends Fragment {

    private TextView displayNameTextView;
    private TextView displayEmailTextView;
    private Button logoutButton;
    private DatabaseHelper databaseHelper;

    public AccountFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_account, container, false);

        displayNameTextView = rootView.findViewById(R.id.displayname);
        displayEmailTextView = rootView.findViewById(R.id.displayemail);
        logoutButton = rootView.findViewById(R.id.logoutButton);
        databaseHelper = new DatabaseHelper(requireContext());

        String loggedInUsername = getLoggedInUsername(requireContext());
        String loggedInEmail = getLoggedInEmail(requireContext());
        if (loggedInUsername != null) {
            displayNameTextView.setText(loggedInUsername);
            if (loggedInEmail != null) {
                displayEmailTextView.setText(loggedInEmail);
            }
        }

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(requireContext(), LoginActivity.class);
                loginIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(loginIntent);
            }
        });

        return rootView;
    }

    private String getLoggedInEmail(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        return preferences.getString("email", null);
    }

    private String getLoggedInUsername(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        return preferences.getString("username", null);
    }
}
