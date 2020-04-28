package com.example.myapp3.ui.notifications;

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

//Hafsah Kamran - S1627179
public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final NotificationsViewModel notificationsViewModel = ViewModelProviders.of(this).get(NotificationsViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_notifications, container, false);



       /* final TextView textView = root.findViewById(R.id.text_notifications);
        ArrayList<HashMap<String, String>> items = notificationsViewModel.getText();
        for(int i = 0 ; i<items.size();i++) {
            textView.setText(items.get(i).get("guid").toString());

        }
*/

        CalendarView calendarView = root.findViewById(R.id.calendarView_roadWorks);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                String curDate = String.valueOf(dayOfMonth);
                System.out.println("CURDATE " + curDate);

                final TextView textView = root.findViewById(R.id.text_notifications);
                ArrayList<HashMap<String, String>> items = notificationsViewModel.getText(curDate);
                String data = "";
                for(HashMap<String, String> item : items){


                    data += item.get("link").toString() +" ";
                }


                textView.setText("Date = "+ curDate +" \n\n "+items.size() +"= "+data);


            }
        });



        return root;
    }
}
