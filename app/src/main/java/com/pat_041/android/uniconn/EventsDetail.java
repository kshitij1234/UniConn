package com.pat_041.android.uniconn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.pat_041.android.uniconn.definitions.Event;

public class EventsDetail extends AppCompatActivity {

    Event mEvent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_detail);
        mEvent = (Event)getIntent().getSerializableExtra("EventObj");
        displayEventBasicInfo();
    }
    private void displayEventBasicInfo(){
        TextView eventNameTextView = (TextView)findViewById(R.id.event_title_textView);
        TextView eventCityStateTextView = (TextView)findViewById(R.id.event_city_state);
        TextView eventDateTextView = (TextView)findViewById(R.id.event_date_textview);
        TextView eventWebsite = (TextView)findViewById(R.id.event_url_textview);
        TextView eventInfo=(TextView)findViewById(R.id.more_event_info_textview);
        eventWebsite.setText(mEvent.getLink());
        eventNameTextView.setText(mEvent.getName());
        eventCityStateTextView.setText(mEvent.getCity()+", "+mEvent.getState());
        eventDateTextView.setText(mEvent.getSdate()+" - "+mEvent.getEdate());
        eventInfo.setText(mEvent.getInfo());
    }
}
