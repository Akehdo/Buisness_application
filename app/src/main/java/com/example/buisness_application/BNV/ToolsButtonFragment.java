package com.example.buisness_application.BNV;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.buisness_application.R;
import com.example.buisness_application.Tools_TabLayout_Fragments.TabHistoryFragment;
import com.example.buisness_application.Tools_TabLayout_Fragments.TabToolsFragment;
import com.example.buisness_application.Tools_TabLayout_Fragments.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;


public class ToolsButtonFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bnv_tools_button, container, false);

        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        ViewPager2 viewPager = view.findViewById(R.id.viewPager);

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new TabToolsFragment());
        fragments.add(new TabHistoryFragment());

        ViewPagerAdapter adapter = new ViewPagerAdapter(this, fragments);
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Выбор инструментов");
                    break;
                case 1:
                    tab.setText("История");
                    break;
            }
        }).attach();

        return view;
    }
}