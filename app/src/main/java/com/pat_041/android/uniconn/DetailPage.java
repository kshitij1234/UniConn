package com.pat_041.android.uniconn;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.pat_041.android.uniconn.definitions.College;

import java.util.Locale;


/**
 * Created by Abhijit on 14-10-2017.
 */

public class DetailPage extends AppCompatActivity {
    College mCollege;
    FloatingActionButton mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        Intent intent = getIntent();
        mCollege = (College)getIntent().getSerializableExtra("CollegeObj");
        displayCollegeBasicInfo();
        //displayCollegeExtendedInfo();
        //displayCollegeHostelInfo();
        mButton = (FloatingActionButton)findViewById(R.id.detail_map_button);
        mButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openMapIntent();
            }
        });
    }
    //Uncomment this Portion when you guys get the College Object
    //Block 1
    private void displayCollegeBasicInfo(){
        TextView collegeNameTextView = (TextView)findViewById(R.id.author);
        TextView collegeAddressTextView = (TextView)findViewById(R.id.quote);
        TextView collegeWebsite = (TextView)findViewById(R.id.collge_website);
        String nameCollege = mCollege.getCollegeName()+"\n";
        String address =(mCollege.getAddress()+".PIN - "+mCollege.getPincode()+"\n");
        address+=(mCollege.getCity()+" , "+mCollege.getDistrict()+" , "+mCollege.getState()+"\n");
        collegeNameTextView.setText(nameCollege);
        collegeAddressTextView.setText(address);
        collegeWebsite.setText(mCollege.getWebsite());
    }


    //Block 2
    private void displayCollegeExtendedInfo(){
        TextView collegeDepartment = (TextView)findViewById(R.id.dept);
        TextView collegeCourseLevel = (TextView)findViewById(R.id.course_level);
        TextView collegeInfrastructure = (TextView)findViewById(R.id.infrastructure);
        TextView collegeFellowship = (TextView)findViewById(R.id.fellowship);
        String department = "";
        String courseLevel = "";
        String infrastructure = "";
        String fellowShip="";
        String scholarShip="";
        int i=0;
        for(String s:mCollege.getDept()){
            department = i+")"+s+"\n\n";
        }
        for(String s:mCollege.getCourse_level()){
            courseLevel = s+", ";
        }
        for(String s:mCollege.getInfrastructure()){
            infrastructure = s+", ";
        }
        collegeDepartment.setText(department);
        collegeCourseLevel.setText(courseLevel);
        collegeInfrastructure.setText(infrastructure);
        fellowShip = (mCollege.fellowShipAvailable)?"Fellowship Opportunities Available":"No Fellowships Provided";
        scholarShip = (mCollege.fellowShipAvailable)?"Scholarship Opportunities Available":"No Scholarships Provided";
        collegeFellowship.setText(fellowShip+"\n"+scholarShip);
    }


    //Block 3
    //Provide Hostel Infromation and then I can do something
    private void displayCollegeHostelInfo(){

    }

    public void openMapIntent() {
        String uri = "http://maps.google.com/maps?q=loc:" + mCollege.getLatitute() + "," + mCollege.getLongitude() + " (" + mCollege.getAddress() + ")";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}
