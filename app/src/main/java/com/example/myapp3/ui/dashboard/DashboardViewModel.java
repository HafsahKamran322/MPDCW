package com.example.myapp3.ui.dashboard;

import android.os.StrictMode;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapp3.XMLParser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;

//Hafsah Kamran - S1627179
public class DashboardViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DashboardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    static final String URL = "https://trafficscotland.org/rss/feeds/plannedroadworks.aspx";

    static final String KEY_ITEM = "item";     //parent node
    static final String KEY_TITLE = "title";
    static final String KEY_LINK = "link";
    static final String KEY_PUBDATE = "pubDate";
    static final String KEY_GUID = "guid";
    static final String KEY_DESCRIPTION = "description";

    ArrayList<HashMap<String, String>> items ;
    public ArrayList<HashMap<String, String>> getText(String date ) {
        items = new ArrayList<>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        System.out.println("background process started--- in Build RoadWorks = "+date);
        XMLParser parser = new XMLParser();
        String xml = parser.sendPostRequest(URL);
        System.out.println(xml);
        Document doc = parser.getDomElement(xml);
        //  System.out.print(doc);

        NodeList nl = doc.getElementsByTagName(KEY_ITEM);
        //looping through all items
        for (int i = 0; i < nl.getLength(); i++) {
            HashMap<String, String> map = new HashMap<>();
            Element e = (Element) nl.item(i);

            //adding each child to hashmap




            String desc = parser.getValue(e, KEY_DESCRIPTION);
            String descArray[] = desc.split("End Date:");

            if(descArray[0].toString().toLowerCase().contains(", "+date)){
                map.put(KEY_PUBDATE, parser.getValue(e, KEY_PUBDATE));
                map.put(KEY_LINK, parser.getValue(e, KEY_LINK));
                map.put(KEY_GUID, parser.getValue(e, KEY_GUID));
                map.put(KEY_DESCRIPTION, parser.getValue(e, KEY_DESCRIPTION));
                items.add(map);
            }



            //adding hashmap to arraylist



        }
        System.out.println("Total Items size in RoadWoks = "+items.size());
        return items;

    }
}