package com.pat_041.android.uniconn;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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
        ImageView eventImage = (ImageView) findViewById(R.id.events_image);
        TextView eventNameTextView = (TextView)findViewById(R.id.event_title_textView);
        TextView eventCityStateTextView = (TextView)findViewById(R.id.event_city_state);
        TextView eventDateTextView = (TextView)findViewById(R.id.event_date_textview);
        final TextView eventWebsite = (TextView)findViewById(R.id.event_url_textview);
        TextView eventInfo=(TextView)findViewById(R.id.more_event_info_textview);
        int resId=0;
        switch(mEvent.getId())
        {
            case 1:resId=R.drawable.sikkim_image;
                break;
            case 2:resId=R.drawable.data_driven_decision_making;
                break;
            case 3:resId=R.drawable.open_data_government;
                break;
            case 4:resId=R.drawable.open_data_government;
                break;
            case 5:resId=R.drawable.national_conference_on_open_data;
                break;
            case 6:resId=R.drawable.code_for_honour;
                break;
            case 7:resId=R.drawable.event_cma_hackathon;
        }
        eventImage.setImageResource(resId);
        eventWebsite.setText(mEvent.getLink());
        eventWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(eventWebsite.getText().toString()));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
        eventNameTextView.setText(mEvent.getName());
        eventCityStateTextView.setText(mEvent.getCity()+", "+mEvent.getState());
        eventDateTextView.setText(mEvent.getSdate()+" - "+mEvent.getEdate());
        eventInfo.setText(mEvent.getInfo());
    }
}
