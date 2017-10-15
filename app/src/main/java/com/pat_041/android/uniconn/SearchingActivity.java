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

import android.provider.Settings;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;

import android.util.Log;
import android.view.LayoutInflater;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pat_041.android.uniconn.definitions.College;
import com.pat_041.android.uniconn.definitions.Event;
import com.pat_041.android.uniconn.definitions.SuperObjects;
import com.pat_041.android.uniconn.definitions.UserLocation;
import com.pat_041.android.uniconn.loaders.CollegeLoader;
import com.pat_041.android.uniconn.loaders.EventLoader;

import java.util.ArrayList;
import java.util.List;

public class SearchingActivity extends AppCompatActivity implements SearchingActivityAdapter.ListItemClickListener {

    private int id;
    private ProgressBar mLoadingIndicator;
    private Button mGisButton;
    private String lsearchQuery;
    private int lsearchType;
    private String lKey;

    private ArrayList<? extends SuperObjects> list;


    private SearchingActivityAdapter mAdapter;
    private RecyclerView recyclerView;
    private TextView mErrorView;
    CollegeCallbacks collegeCallbacks;
    EventCallbacks eventCallbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching);

        Intent intent = getIntent();

        id = intent.getExtras().getInt("id");
        mErrorView=(TextView)findViewById(R.id.tv_error_message_display);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mGisButton = (Button)findViewById(R.id.detail_gis_button);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(false);

        list = new ArrayList<>();

        mAdapter = new SearchingActivityAdapter(this,list);

        recyclerView.setAdapter(mAdapter);
        SearchingActivityAdapter.BackgroundItemDecoration decoration = new SearchingActivityAdapter.BackgroundItemDecoration();
        //recyclerView.addItemDecoration(decoration);
        mGisButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                LinearLayout gis_layout = (LinearLayout)findViewById(R.id.gis_linear_layout);
                gis_layout.setVisibility(View.GONE);
                //showAlert();
                UserLocation userLoc= new UserLocation(SearchingActivity.this);
                if(userLoc.isLocationEnabled()){
                    displayGisBasedSearch(userLoc);
                }else{
                    showErrorView();
                    Log.v("UserLocation Error : ","OnClick Error");
                }
            }
        });

        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(
                recyclerView.getContext(),
                layoutManager.getOrientation()
        );
        recyclerView.addItemDecoration(mDividerItemDecoration);

        collegeCallbacks = new CollegeCallbacks(this,getLoaderManager());
        eventCallbacks = new EventCallbacks(this,getLoaderManager());

    }
    private void showJsonDataView() {
        // First, make sure the error is invisible
        mErrorView.setVisibility(View.INVISIBLE);
        // Then, make sure the JSON data is visible
        recyclerView.setVisibility(View.VISIBLE);
    }
    private void showErrorView() {
        // First, make sure the error is invisible
        mErrorView.setVisibility(View.VISIBLE);
        // Then, make sure the JSON data is visible
        recyclerView.setVisibility(View.INVISIBLE);
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
                query=(""+query.charAt(0)).toUpperCase()+query.substring(1).toLowerCase();
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
                            String parameter=parameterView.getText().toString().toLowerCase();
                            String value=valueView.getText().toString();
                            value=(value.charAt(0)+"").toUpperCase()+value.substring(1).toLowerCase();
                            // Add searching over here
                            lKey = parameter;
                            searchQuery(value,SearchCaseConstants.PARAMETERIZED_SEARCH);
                            //System.out.println(parameter);
                            //System.out.println(value);
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
                //System.out.println("inside case 0");
                getLoaderManager().restartLoader(1,null,collegeCallbacks);
                break;
            case 2:
                getLoaderManager().restartLoader(1,null,eventCallbacks);
                break;
            case 1:

            case 3:

        }


    }


    @Override
    public void onListItemClick(int clickedItemIndex) {
        // this will have an intent to go to the item specific activity based on value of id

        Intent intent;
        switch(id)
        {
            case 0:
                intent  = new Intent(getApplicationContext(),DetailPage.class);
                intent.putExtra("CollegeObj",(College)list.get(clickedItemIndex));
                startActivity(intent);
                break;

            case 2:
                intent  = new Intent(getApplicationContext(),EventsDetail.class);
                intent.putExtra("EventObj",(Event)list.get(clickedItemIndex));
                startActivity(intent);
                break;
        }



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
                    mLoadingIndicator.setVisibility(View.VISIBLE);
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
            LinearLayout gis_layout = (LinearLayout)findViewById(R.id.gis_linear_layout);
            gis_layout.setVisibility(View.GONE);
            list =(ArrayList<? extends SuperObjects>) data;
            mAdapter.setList(list);
            if(data==null)
            {
                showErrorView();
            }
            else
            {
                showJsonDataView();
            }
            mLoadingIndicator.setVisibility(View.INVISIBLE);

        }

        @Override
        public void onLoaderReset(Loader<List<College>> loader) {
            list.clear();
            mAdapter.setList(new ArrayList<SuperObjects>());
        }
    }

    private void displayGisBasedSearch(UserLocation userLoc){
        if(userLoc.locate){
            //System.out.println("The value of the address is  :: "+userLoc.userAddress);
            //Log.v("UserLocation : ",""+userLoc.userAddress);
            String state = userLoc.userAddress;
            state = state.substring(0,state.lastIndexOf(','));
            state = state.substring(state.lastIndexOf(','));
            state = state.substring(1,state.lastIndexOf(' '));
            state.trim();
            Log.v("UserLocation : ",""+state);
            lKey = "state";
            searchQuery(state,SearchCaseConstants.PARAMETERIZED_SEARCH);
        }else{
            showErrorView();
            Log.v("UserLocation Error : ","Inside displayGISBASED");
        }
    }

    private void showAlert() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Enable Location")
                .setMessage("Your Locations Settings is set to 'Off'.\nPlease Enable Location to " +
                        "use this app")
                .setPositiveButton("Location Settings", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(myIntent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    }
                });
        dialog.show();
    }
    private class EventCallbacks implements LoaderManager.LoaderCallbacks<List<Event>> {

        Context context;

        public EventCallbacks(Context context, LoaderManager loaderManager) {
            this.context = context;
            loaderManager.initLoader(0, null, this);
        }


        @Override
        public Loader<List<Event>> onCreateLoader(int id, Bundle args) {
            list.clear();
            mAdapter.setList(new ArrayList<SuperObjects>());
            System.out.println("inside oncreateloader");
            switch (lsearchType) {
                case SearchCaseConstants.NORMAL_SEARCH:
                    System.out.println("eloader");
                    EventLoader c =  new EventLoader(context, lsearchQuery, null, SearchCaseConstants.NORMAL_SEARCH);
                    System.out.println("outside coleddd");
                    return c;
                case SearchCaseConstants.PARAMETERIZED_SEARCH:
                    return new EventLoader(context, lsearchQuery, lKey, SearchCaseConstants.PARAMETERIZED_SEARCH);

            }
            return null;
        }



        @Override
        public void onLoadFinished(Loader<List<Event>> loader, List<Event> data) {
            System.out.println("inside load finished");
            list =(ArrayList<? extends SuperObjects>) data;
            mAdapter.setList(list);

        }

        @Override
        public void onLoaderReset(Loader<List<Event>> loader) {
            list.clear();
            mAdapter.setList(new ArrayList<SuperObjects>());
        }
    }
}
