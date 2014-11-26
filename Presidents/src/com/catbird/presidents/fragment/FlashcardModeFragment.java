/**
 * @author allisonklopp
 * @date Sept 13, 2014	 
 */
package com.catbird.presidents.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.catbird.presidents.MainActivity;
import com.catbird.presidents.Presidents;
import com.catbird.presidents.R;

/**
 * The fragment that displays the swipeable flashcards. 
 */
public class FlashcardModeFragment extends Fragment{
	// An invalid card index position
	private int INVALID_CARD_INDEX = -1;

	/**
	 * The number of pages to show in this flashcard mode.
	 */
	private static final int NUM_PAGES = Presidents.NAMES.length;

	/**
	 * The pager widget, which handles animation and allows swiping horizontally to access previous
	 * and next cards.
	 */
	private ViewPager mPager;

	/**
	 * The pager adapter, which provides the pages to the view pager widget.
	 */
	private PagerAdapter mPagerAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_screenslide, container, false);

		// Instantiate a ViewPager and a PagerAdapter.
		mPager = (ViewPager) rootView.findViewById(R.id.pager);
		mPagerAdapter = new ScreenSlidePagerAdapter(getActivity().getSupportFragmentManager());
		mPager.setAdapter(mPagerAdapter);

		int currentCard = ((MainActivity)getActivity()).getCurrentCardToView();
		if(currentCard != INVALID_CARD_INDEX)
			mPager.setCurrentItem(currentCard);

		return rootView;
	}
	
	/**
	 * Called by MainActivity when the back button is pressed and this fragment is active.
	 * @return true if successfully switched to previous card. <br>
	 * 		   false if did not switch to previous card (already at index 0)
	 */
	public boolean onBackPressed()
	{
		if(mPager.getCurrentItem()!=0)
		{
			mPager.setCurrentItem(mPager.getCurrentItem() - 1);
			return true;
		}
		return false;
	}
	/**
	 * A simple pager adapter that represents X ScreenSlidePageFragment objects, in
	 * sequence.
	 */
	private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

		/**
		 * TODO
		 * @param fm
		 * @param start
		 */
		public ScreenSlidePagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			return new FlashcardFragment(position);
		}

		@Override
		public int getCount() {
			return NUM_PAGES;
		}
	}
}
