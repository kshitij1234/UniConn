package com.pat_041.android.uniconn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.pat_041.android.uniconn.definitions.College;


/**
 * Created by Abhijit on 14-10-2017.
 */

public class DetailPage extends AppCompatActivity {
    College mCollege;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        Intent intent = getIntent();
        /**************************************************************************************************
         * See Through this code for Passing Classes into an Intent and For reading classes from an intent
         * Set the name of the putExtra Object as CollegeObj
        //To pass:
        intent.putExtra("MyClass", obj);

        // To retrieve object in second Activity
        getIntent().getSerializableExtra("MyClass");
         ***************************************************************************************************/
        mCollege = (College)getIntent().getSerializableExtra("CollegeObj");
    }

    private void displayCollegeBasicInfo(){
        TextView collegeNameTextView = (TextView)findViewById(R.id.author);
        TextView collegeAddressTextView = (TextView)findViewById(R.id.quote);
        TextView collegeStateCity = (TextView)findViewById(R.id.collge_city_state);
        TextView collegeWebsite = (TextView)findViewById(R.id.collge_website);
        collegeNameTextView.setText(mCollege.getCollegeName());
        collegeAddressTextView.setText(mCollege.getAddress());
        collegeStateCity.setText(mCollege.getCity()+" , "+mCollege.getState());
        collegeWebsite.setText(mCollege.getWebsite());
    }

}
