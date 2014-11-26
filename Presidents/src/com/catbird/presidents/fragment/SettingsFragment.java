/**
 * @author allisonklopp
 * @date Sept 13, 2014
 */
package com.catbird.presidents.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.catbird.presidents.R;

/**
 * TODO
 */
public class SettingsFragment extends Fragment {
	public static final String DIFFICULTY_SELECTED = "diffmode";
	public static final String SETTINGS = "settings";

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        //TODO more settings?!?!?
        //TODO define diff mode meaning and implement
        RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.dmRG);
        radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				
				 // We need an Editor object to make preference changes.
		        // All objects are from android.context.Context
		        SharedPreferences settings = getActivity().getSharedPreferences(SETTINGS, 0);
		        SharedPreferences.Editor editor = settings.edit();
		        editor.putInt(DIFFICULTY_SELECTED, checkedId);

		        // Commit the edits!
		        editor.commit();
				
			}
		});
        
        SharedPreferences settings = getActivity().getSharedPreferences(SETTINGS, 0);
        int currentChecked = settings.getInt(DIFFICULTY_SELECTED, -1);
        
        if(currentChecked != -1)
        {
        	((RadioButton) rootView.findViewById(currentChecked)).setChecked(true);
        }
        
        return rootView;
	}
}
