/**
 * @author allisonklopp
 * @date Sept 13, 2014
 */
package com.catbird.presidents.fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.catbird.presidents.MainActivity;
import com.catbird.presidents.Presidents;

/**
 * TODO
 */
public class PresidentListFragment extends ListFragment{
	@Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, Presidents.NAMES));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
    	// TODO: show card on click
        Log.i("PresidentListFragment", "Item clicked: " + id);
        
        //TODO enum for fragments
        ((MainActivity) getActivity()).setCurrentCardToView(position);
        ((MainActivity) getActivity()).getNavigationDrawerFragment().selectItem(position);
        ((MainActivity) getActivity()).onNavigationDrawerItemSelected(1);
        //TODO small bug: after clicking on an item and going to that card, the nav drawer still shows
        // this fragment as being the current selected fragment
        
    }
}
