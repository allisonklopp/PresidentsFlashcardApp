/**
 * 
 */
package com.catbird.presidents.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.catbird.presidents.Presidents;
import com.catbird.presidents.R;

/**
 * @author allisonklopp
 *
 */
public class FlashcardFragment extends Fragment{
	
	public int position;
	
	public FlashcardFragment(int position)
	{	
		this.position = position;
	}
	
	 @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container,
             Bundle savedInstanceState) {
		 View rootView = inflater.inflate(R.layout.fragment_flashcardmode, container, false);
		 
		 // Populate with card data
		 TextView indexValTV = (TextView) rootView.findViewById(R.id.indexVal);
		 indexValTV.setText((position+1) + " of " + Presidents.NAMES.length); 
		 
		 TextView cardNameTV = (TextView) rootView.findViewById(R.id.cardNameTV);
		 cardNameTV.setText(Presidents.NAMES[position]);
		 
		 TextView cardDobTV = (TextView) rootView.findViewById(R.id.cardDobTV);
		 cardDobTV.setText(Presidents.DOBS[position]);
		 
		 TextView cardDodTV = (TextView) rootView.findViewById(R.id.cardDodTV);
		 cardDodTV.setText(Presidents.DODS[position]);
		 
		 TextView cardStartTermTV = (TextView) rootView.findViewById(R.id.cardTermStartTV);
		 cardStartTermTV.setText(Presidents.TERM_START[position]);
		 
		 TextView cardEndTermTV = (TextView) rootView.findViewById(R.id.cardTermEndTV);
		 cardEndTermTV.setText(Presidents.TERM_END[position]);
		 
		 ImageView cardPortraitIV = (ImageView) rootView.findViewById(R.id.portraitIV);
		 cardPortraitIV.setImageResource(Presidents.PORTRAITS[position]);
		 
		 return rootView;
	 }
}
