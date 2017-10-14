package com.pat_041.android.uniconn;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;

import com.pat_041.android.uniconn.definitions.SuperObjects;

import java.util.ArrayList;

public class SearchingActivity extends AppCompatActivity implements SearchingActivityAdapter.ListItemClickListener{

    private int id;

    private ArrayList<SuperObjects> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching);

        Intent intent  = getIntent();

        id = intent.getExtras().getInt("id");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        // Retrieve the SearchView and plug it into SearchManager
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        // this will have an intent to go to the item specific activity
    }
}
