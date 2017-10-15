package com.pat_041.android.uniconn;

import android.app.SearchManager;
import android.content.Context;

import android.content.Intent;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.view.ViewGroup.LayoutParams;
import android.os.Build;
import android.util.Log;
import android.view.Menu;

import com.pat_041.android.uniconn.definitions.SuperObjects;

import java.util.ArrayList;

public class SearchingActivity extends AppCompatActivity implements SearchingActivityAdapter.ListItemClickListener{
    private PopupWindow mPopupWindow;
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
        if (itemThatWasClickedId == R.id.filter_search) {
            Context context = SearchingActivity.this;
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);

            // Inflate the custom layout/view
            View customView = inflater.inflate(R.layout.popup_window, null);


            // Initialize a new instance of popup window
            mPopupWindow = new PopupWindow(
                    customView,
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT
            );

            // Set an elevation value for popup window
            // Call requires API level 21
            if (Build.VERSION.SDK_INT >= 21) {
                mPopupWindow.setElevation(5.0f);
            }

            // Get a reference for the custom view close button
            ImageButton closeButton = (ImageButton) customView.findViewById(R.id.ib_close);

            // Set a click listener for the popup window close button
            closeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Dismiss the popup window
                    mPopupWindow.dismiss();
                }
            });


            mPopupWindow.showAtLocation(findViewById(R.id.searchingActivity), Gravity.CENTER, 0, 0);

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
