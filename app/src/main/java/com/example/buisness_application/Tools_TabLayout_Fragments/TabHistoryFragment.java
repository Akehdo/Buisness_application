package com.example.buisness_application.Tools_TabLayout_Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.buisness_application.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TabHistoryFragment extends Fragment {

    private LinearLayout historyContainer;
    private DatabaseReference databaseReference;
    private String userId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_history_fragment, container, false);

        historyContainer = view.findViewById(R.id.historyContainer);
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference("users").child(userId).child("pirate_funnels");

        loadHistory();

        return view;
    }

    private void loadHistory() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                historyContainer.removeAllViews(); // Очищаем контейнер перед загрузкой новых данных

                for (DataSnapshot data : snapshot.getChildren()) {
                    String value = data.child("attractedClientsNum").getValue(String.class); // Получаем данные

                    TextView textView = new TextView(getContext());
                    textView.setText(value);
                    textView.setTextSize(16);
                    textView.setTextColor(getResources().getColor(R.color.white));
                    textView.setPadding(16, 8, 16, 8);
                    textView.setBackgroundColor(getResources().getColor(R.color.card_hover_effect));

                    historyContainer.addView(textView);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}
