package com.pat_041.android.uniconn;



import android.app.LoaderManager;
import android.app.SearchManager;
import android.content.Context;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Loader;
import android.content.Intent;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;

import android.view.LayoutInflater;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

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

    CollegeCallbacks collegeCallbacks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching);

        Intent intent = getIntent();

        id = intent.getExtras().getInt("id");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(false);

        list = new ArrayList<>();

        mAdapter = new SearchingActivityAdapter(this,list);

        recyclerView.setAdapter(mAdapter);


        collegeCallbacks = new CollegeCallbacks(this,getLoaderManager());
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

                System.out.println("inside submit  1");
                query.trim();

                if (query == null || query.equals(""))
                    return true;
                System.out.println("inside submit");
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.filter_search) {
            AlertDialog.Builder builder = new AlertDialog.Builder(SearchingActivity.this);
            // Get the layout inflater
            LayoutInflater inflater = SearchingActivity.this.getLayoutInflater();
            final View v = inflater.inflate(R.layout.popup_dialog, null);
            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            builder.setView(v)
                    // Add action buttons
                    .setPositiveButton("Make Query", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            EditText parameterView=(EditText)v.findViewById(R.id.parameter);
                            EditText valueView= (EditText)v.findViewById(R.id.value);
                            String parameter=parameterView.getText().toString();
                            String value=valueView.getText().toString();
                            // Add searching over here
                            System.out.println(parameter);
                            System.out.println(value);
                            dialog.cancel();
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    }).create().show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void searchQuery(String query, int searchType) {

        lsearchQuery = query;
        lsearchType = searchType;
        // need to have lKey ready from before for other case
        // call the api in async task and display

        switch (id) {
            case 0:
                System.out.println("inside case 0");
                getLoaderManager().restartLoader(1,null,collegeCallbacks);
                break;
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

        public CollegeCallbacks(Context context, LoaderManager loaderManager) {
            this.context = context;
            loaderManager.initLoader(0, null, this);
        }


        @Override
        public Loader<List<College>> onCreateLoader(int id, Bundle args) {
            System.out.println("inside oncreateloader");
            switch (lsearchType) {
                case SearchCaseConstants.NORMAL_SEARCH:
                    System.out.println("eloader");
                    CollegeLoader c =  new CollegeLoader(context, lsearchQuery, null, SearchCaseConstants.NORMAL_SEARCH);
                    System.out.println("outside coleddd");
                    return c;
                case SearchCaseConstants.PARAMETERIZED_SEARCH:
                    return new CollegeLoader(context, lsearchQuery, lKey, SearchCaseConstants.PARAMETERIZED_SEARCH);

            }
            return null;
        }



        @Override
        public void onLoadFinished(Loader<List<College>> loader, List<College> data) {
            System.out.println("inside load finished");
            mAdapter.setList((ArrayList<? extends SuperObjects>) data);

        }

        @Override
        public void onLoaderReset(Loader<List<College>> loader) {
            mAdapter.setList(new ArrayList<SuperObjects>());
        }
    }
}
