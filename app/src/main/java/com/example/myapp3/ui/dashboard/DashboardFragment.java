package com.example.myapp3.ui.dashboard;

import android.content.Intent;
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

import com.example.myapp3.MainActivity;
import com.example.myapp3.R;

import java.util.ArrayList;
import java.util.HashMap;

//Hafsah Kamran - S1627179
public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
 //  private Object String;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_dashboard, container, false);



       /* final TextView textView = root.findViewById(R.id.text_dashboard);
        ArrayList<HashMap<String, String>> items = dashboardViewModel.getText();
        for(int i = 0 ; i<items.size();i++) {
            textView.setText(items.get(i).get("link").toString());

        }*/

        CalendarView calendarView = root.findViewById(R.id.calendarView_buildRoadWorks);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                String curDate = String.valueOf(dayOfMonth);
                System.out.println("CURDATE " + curDate);

                final TextView textView = root.findViewById(R.id.text_dashboard);
                ArrayList<HashMap<String, String>> items = dashboardViewModel.getText(curDate);
                String data = "";
                for(HashMap<String, String> item : items){


                    data += item.get("link").toString() +" ";
                }


                textView.setText("Date = "+ curDate +" \n\n "+items.size() +"= "+data);


            }
        });

       // TextView.setText(TextView.getText().toString() + String)




        return root;
    }
}


