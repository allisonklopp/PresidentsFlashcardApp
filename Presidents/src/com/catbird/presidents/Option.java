/**
 * @author allisonklopp
 * @date Sept 13, 2014	 
 */
package com.catbird.presidents;

import com.catbird.presidents.fragment.AboutFragment;
import com.catbird.presidents.fragment.FlashcardModeFragment;
import com.catbird.presidents.fragment.PresidentListFragment;
import com.catbird.presidents.fragment.QuizModeFragment;
import com.catbird.presidents.fragment.ScoresFragment;
import com.catbird.presidents.fragment.SettingsFragment;

import android.support.v4.app.Fragment;

/**
 * TODO
 */
public enum Option {
	SCORES (0),
	FLASHCARD_MODE (1),
	PRESIDENT_LIST(2),
	QUIZ_MODE(3),
	SETTINGS(4),
	ABOUT(5), 
	DEFAULT(99);
	
	private final int index;
	/**
	 * Constructor
	 * @param index
	 */
	private Option(int index)
	{
		this.index = index;
	}
	
	/**
	 * Returns the numeric value of this enum
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Returns the fragment indicated by enum
	 * @param index
	 * @return fragment
	 */
	public static Fragment getFragment(Option index)
	{
		Fragment retFrag = null;
		switch(index)
		{
			case SCORES:
				retFrag = new ScoresFragment();
				break;
			case FLASHCARD_MODE:
				retFrag = new FlashcardModeFragment();
				break;
			case PRESIDENT_LIST:
				retFrag = new PresidentListFragment();
				break;
			case QUIZ_MODE:
				retFrag = new QuizModeFragment();
				break;
			case SETTINGS:
				retFrag = new SettingsFragment();
				break;
			case ABOUT:
				retFrag = new AboutFragment();
				break;
			default:
				break;
		}
		return retFrag;
	}
	
	public static Option getOption(int index)
	{
		Option retVal = DEFAULT;
		switch(index)
		{
			case 0:
				retVal = SCORES;
				break;
			case 1:
				retVal = FLASHCARD_MODE;
				break;
			case 2:
				retVal = PRESIDENT_LIST;
				break;
			case 3:
				retVal = QUIZ_MODE;
				break;
			case 4:
				retVal = SETTINGS;
				break;
			case 5:
				retVal = ABOUT;
				break;
			default:
				break;
		}
		return retVal;
	}

}