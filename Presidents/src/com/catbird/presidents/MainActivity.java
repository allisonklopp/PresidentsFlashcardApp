/**
 * @author allisonklopp
 * @date Sept 13, 2014
 */
package com.catbird.presidents;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.catbird.presidents.fragment.FlashcardModeFragment;
import com.catbird.presidents.fragment.NavigationDrawerFragment;

/**
 * The main activity, starting point, of the application
 */
public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {
	
	// An invalid index for the current flashcard
	private int INVALID_CARD_INDEX = -1;
	
	// The current flashcard, initialize to invalid, since this is start-up
	private int currentCardToView = INVALID_CARD_INDEX;

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
        
        // Open Scoreboard
        onNavigationDrawerItemSelected(Option.SCORES.getIndex());
    }

    /**
     * Gets the navigation drawer fragment
     * @return the navigation drawer fragment
     */
    public NavigationDrawerFragment getNavigationDrawerFragment() {
		return mNavigationDrawerFragment;
	}

	@Override
    public void onNavigationDrawerItemSelected(int position) {
    	// Update title
    	onSectionAttached(position);
    	restoreActionBar();
    	
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
		.replace(R.id.container, Option.getFragment(Option.getOption(position)), Option.getOption(position).toString())
		.commit();
    }

    /**
     * When the section is attached, set the title to that section name. 
     * Restore action bar should always be called after this method.
     * @param number the index of the current fragment in the drawer
     */
    public void onSectionAttached(int number) {
    	mTitle = getResources().getStringArray(R.array.section_titles)[number];
    }

    /**
     * Refresh the action bar, including the title.
     */
    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
        	onNavigationDrawerItemSelected(Option.SETTINGS.getIndex());
        	
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
    	Fragment fragment = getSupportFragmentManager().findFragmentByTag(Option.FLASHCARD_MODE.toString());
    	if (fragment != null)
    	{
    		FlashcardModeFragment fMfragment = (FlashcardModeFragment) fragment;
    		boolean successful = fMfragment.onBackPressed();
    		
    		if(!successful)
    			super.onBackPressed();
    	}
    	else
    	{
    		super.onBackPressed();
    	}
    }
    
    /**
	 * @return the currentCardToView
	 */
	public int getCurrentCardToView() {
		return currentCardToView;
	}

	/**
	 * @param currentCardToView the currentCardToView to set
	 */
	public void setCurrentCardToView(int currentCardToView) {
		this.currentCardToView = currentCardToView;
	}

	/**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
