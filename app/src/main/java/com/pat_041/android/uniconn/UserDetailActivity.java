package com.pat_041.android.uniconn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.pat_041.android.uniconn.definitions.Project;
import com.pat_041.android.uniconn.definitions.User;

public class UserDetailActivity extends AppCompatActivity {
    private User mUser;
    private TextView mUserNameTextView;
    private TextView mUserDetailTextView;
    private TextView mUserProjectListTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        mUser = (User)getIntent().getSerializableExtra("UserObj");
        mUserDetailTextView=(TextView)findViewById(R.id.tv_user_detail);
        mUserNameTextView=(TextView)findViewById(R.id.textview_user_name);
        mUserProjectListTextView=(TextView)findViewById(R.id.tv_project_display);
        populateData();
    }
    private void populateData() {
        mUserNameTextView.setText(mUser.getName()+"\n\n");
        mUserDetailTextView.setText("Username : "+mUser.getUname()+"\n\n");
        mUserDetailTextView.append("User type : "+mUser.getType()+"\n\n");
        mUserDetailTextView.append("Institute : "+mUser.getInstitute()+"\n\n");
        mUserDetailTextView.append("City,State : "+mUser.getCity()+","+mUser.getState()+"\n\n");
        mUserDetailTextView.append("User Email : "+mUser.getEmail()+"\n\n");
        DatabaseHandler dbHandler = new DatabaseHandler(this);
        Project project=dbHandler.getProjectOfUser(mUser.getId());

        if(project!=null){
            if(project.getName()!=null)
                mUserProjectListTextView.setText("Name : \n\n"+project.getName()+"\n\n");
            if(project.getTag()!=null)
                mUserProjectListTextView.append("Tag : \n\n"+project.getTag()+"\n\n");
            if(project.getInfo()!=null)
                mUserProjectListTextView.append("Overview : \n\n"+project.getInfo()+"\n\n");
    }}
}
