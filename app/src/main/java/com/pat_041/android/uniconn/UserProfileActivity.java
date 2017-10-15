package com.pat_041.android.uniconn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.pat_041.android.uniconn.definitions.User;

public class UserProfileActivity extends AppCompatActivity {

    User user;
    TextView userInfo;
    String user_info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        user = (User)getIntent().getSerializableExtra("User");
        userInfo = (TextView)findViewById(R.id.userInfo);
        user_info = "NAME : "+user.getName()+"\nType : "+user.getType()+"\nCity : "+user.getCity()+"\nState : "+user.getState()+"\nEmail : "+user.getEmail();
        userInfo.setText(user_info);
    }
}
