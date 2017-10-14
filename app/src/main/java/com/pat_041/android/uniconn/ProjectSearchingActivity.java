package com.pat_041.android.uniconn;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class ProjectSearchingActivity extends AppCompatActivity {


    private ProgressBar mLoadingIndicator;
    private String lsearchQuery;
    private int lsearchType;
    private String lKey;
    private ArrayList<Project> list;
    private RecyclerView recyclerView;
    private TextView mErrorView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_searching);
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

}
