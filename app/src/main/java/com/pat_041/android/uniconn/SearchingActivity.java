package com.pat_041.android.uniconn;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

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

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

               // we have a query baby!!!!
                searchQuery(query);
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
        if (itemThatWasClickedId == R.id.action_search) {
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

    private void searchQuery(String query) {

        // call the api in async task and display

    }


    @Override
    public void onListItemClick(int clickedItemIndex) {
        // this will have an intent to go to the item specific activity based on value of id
    }
}
