package com.example.buisness_application.Tools_TabLayout_Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.buisness_application.R;
import com.example.buisness_application.Tools_TabLayout_Fragments.ToolsList.ToolPirateFunnelActivity;
import com.google.android.material.card.MaterialCardView;

public class TabToolsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab_tools_fragment, container, false);

        MaterialCardView pirateFunnel = view.findViewById(R.id.MCV_pirate_funnel);

        if (pirateFunnel == null) {
            Log.e("TabToolsFragment", "MaterialCardView not found!");
        } else {
            Log.d("TabToolsFragment", "MaterialCardView successfully found.");
        }


        pirateFunnel.setOnClickListener(v -> {
            Log.d("TabToolsFragment", "MaterialCardView clicked!");

            // Запуск нового активити
            Intent intent = new Intent(requireContext(), ToolPirateFunnelActivity.class);
            startActivity(intent);
        });



        return view;
    }


}
