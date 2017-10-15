package com.pat_041.android.uniconn;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pat_041.android.uniconn.definitions.Project;
import com.pat_041.android.uniconn.definitions.SuperObjects;
import com.pat_041.android.uniconn.definitions.User;
import com.pat_041.android.uniconn.loaders.CollegeLoader;
import com.pat_041.android.uniconn.loaders.ProjectLoader;

import java.util.ArrayList;
import java.util.List;

public class ProjectSearchingActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Project>>,ProjectSearchingActivityAdapter.ListItemClickListener{


    private ProgressBar mLoadingIndicator;
    private String lsearchQuery;
    private int lsearchType;
    private String lKey;
    private ArrayList<Project> list;
    private RecyclerView recyclerView;
    private TextView mErrorView;
    private ProjectSearchingActivityAdapter mAdapter;
    private User mLoggedInUser;
    private DatabaseHandler mdbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_searching);
        mLoggedInUser = (User)getIntent().getSerializableExtra("User");
        mdbhelper = new DatabaseHandler(this);


        mErrorView=(TextView)findViewById(R.id.error_message_display);
        recyclerView = (RecyclerView) findViewById(R.id.project_recycler_view);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.loading_indicator);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(false);

        list = new ArrayList<>();

        mAdapter = new ProjectSearchingActivityAdapter(this,list);

        recyclerView.setAdapter(mAdapter);

        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(
                recyclerView.getContext(),
                layoutManager.getOrientation()
        );
        recyclerView.addItemDecoration(mDividerItemDecoration);


        getLoaderManager().restartLoader(1,null,this);
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
            AlertDialog.Builder builder = new AlertDialog.Builder(ProjectSearchingActivity.this);
            // Get the layout inflater
            LayoutInflater inflater = ProjectSearchingActivity.this.getLayoutInflater();
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


    private void searchQuery(String query, int searchType)
    {

    }

    @Override
    public Loader<List<Project>> onCreateLoader(int id, Bundle args) {
        return new ProjectLoader(this,null, null,mdbhelper);
    }

    @Override
    public void onLoadFinished(Loader<List<Project>> loader, List<Project> data) {
        list=(ArrayList<Project>) data;
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
    public void onLoaderReset(Loader<List<Project>> loader) {
        list.clear();
        mAdapter.setList(new ArrayList<Project>());
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
    public void onListItemClick(int clickedItemIndex) {
        // create intent for next activity
        Project project=list.get(clickedItemIndex);
        User user=mdbhelper.getUser(project.getCid());
        Intent i = new Intent();
        i.putExtra("User",mLoggedInUser);
        i.putExtra("UserObj",user);
        i.putExtra("ProjectObj",project);
        startActivity(i);
    }
}
