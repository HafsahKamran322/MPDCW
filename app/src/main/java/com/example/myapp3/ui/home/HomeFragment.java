package com.example.myapp3.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapp3.R;
import com.example.myapp3.ui.dashboard.DashboardViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArraySet;

//Hafsah Kamran - S1627179
public class HomeFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final HomeViewModel homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_home, container, false);

        CalendarView calendarView = root.findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                String curDate = String.valueOf(dayOfMonth);
                System.out.println("CURDATE " + curDate);

                final TextView textView = root.findViewById(R.id.home_textview);
                ArrayList<HashMap<String, String>> items = homeViewModel.getText(curDate);
                String data = "";
                for(HashMap<String, String> item : items){


                    data += item.get("link").toString() +" ";
                }


                textView.setText(items.size() +"= "+data);


           }
        });






        return root;
    }
}
