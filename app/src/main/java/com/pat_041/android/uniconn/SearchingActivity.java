package com.pat_041.android.uniconn;


import android.app.LoaderManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;

import com.pat_041.android.uniconn.definitions.College;
import com.pat_041.android.uniconn.definitions.SuperObjects;
import com.pat_041.android.uniconn.loaders.CollegeLoader;

import java.util.ArrayList;
import java.util.List;

public class SearchingActivity extends AppCompatActivity implements SearchingActivityAdapter.ListItemClickListener {

    private int id;

    private String lsearchQuery;
    private int lsearchType;
    private String lKey;

    private ArrayList<? extends SuperObjects> list;


    private SearchingActivityAdapter mAdapter;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching);

        Intent intent = getIntent();

        id = intent.getExtras().getInt("id");

        

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        // Retrieve the SearchView and plug it into SearchManager
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                query.trim();

                if (query == null || query.equals(""))
                    return true;

                // we have a query baby!!!!
                searchQuery(query, SearchCaseConstants.NORMAL_SEARCH);
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    private void searchQuery(String query, int searchType) {

        lsearchQuery = query;
        lsearchType = searchType;
        // need to have lKey ready from before for other case
        // call the api in async task and display

        switch (id) {
            case 0:

            case 1:

            case 2:

            case 3:

        }


    }


    @Override
    public void onListItemClick(int clickedItemIndex) {
        // this will have an intent to go to the item specific activity based on value of id
    }


    private class CollegeCallbacks implements LoaderManager.LoaderCallbacks<List<College>> {

        Context context;

        public CollegeCallbacks(Context context) {
            this.context = context;
        }


        @Override
        public android.content.Loader<List<College>> onCreateLoader(int id, Bundle args) {
            switch (lsearchType) {
                case SearchCaseConstants.NORMAL_SEARCH:
                    return new CollegeLoader(context, lsearchQuery, null, SearchCaseConstants.NORMAL_SEARCH);
                case SearchCaseConstants.PARAMETERIZED_SEARCH:
                    return new CollegeLoader(context, lsearchQuery, lKey, SearchCaseConstants.PARAMETERIZED_SEARCH);

            }
            return null;
        }

        @Override
        public void onLoadFinished(android.content.Loader<List<College>> loader, List<College> data) {

        }

        @Override
        public void onLoaderReset(android.content.Loader<List<College>> loader) {

        }
    }
}
