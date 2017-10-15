package com.pat_041.android.uniconn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.pat_041.android.uniconn.definitions.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView gridview = (GridView) findViewById(R.id.gridview);
        Log.v("Inside Main Activity : ","I Have entered the main View Going to GridViewAdapter");
        gridview.setAdapter(new GridViewAdapter(this));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent,View v, int position, long id){
                // Send intent to SingleViewActivity
                switch (position) {
                    case 0:
                    case 1:
                    case 2:
                    Intent i = new Intent(getApplicationContext(), SearchingActivity.class);
                    // Pass image index
                    i.putExtra("id", position);
                    startActivity(i);
                        break;
                    case 3:
                        Intent in = new Intent(getApplicationContext(), ProjectSearchingActivity.class);
                        // Pass image index
                        in.putExtra("id", position);
                        in.putExtra("User",(User)getIntent().getSerializableExtra("User"));
                        startActivity(in);
                }
            }
        });
    }


}
