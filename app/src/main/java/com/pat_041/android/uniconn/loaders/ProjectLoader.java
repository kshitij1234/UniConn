package com.pat_041.android.uniconn.loaders;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.pat_041.android.uniconn.DatabaseHandler;
import com.pat_041.android.uniconn.definitions.Project;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP -)(- HP on 10/15/2017.
 */

public class ProjectLoader extends AsyncTaskLoader<List<Project>> {


    private String key;
    private String value;
    private DatabaseHandler mdbhelper;

    public ProjectLoader(Context context, String key, String value, DatabaseHandler helper) {
        super(context);
        this.key=key;
        this.value=value;
        this.mdbhelper=helper;
    }

    @Override
    public List<Project> loadInBackground() {
        ArrayList<Project> list = null;
        list = mdbhelper.getAllProjects();

        return list;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
