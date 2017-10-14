package com.pat_041.android.uniconn;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.pat_041.android.uniconn.definitions.SuperObjects;

import java.util.ArrayList;
import java.util.List;

public class ResultsLoader extends AsyncTaskLoader<List<SuperObjects>> {


    private String url;

    public ResultsLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    public List<SuperObjects> loadInBackground() {
        if(url==null)
            return null;

        ArrayList<SuperObjects> list = null;

        // call function here

        return list;
    }
}
