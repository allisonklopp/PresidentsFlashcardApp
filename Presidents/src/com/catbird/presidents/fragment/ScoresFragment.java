/**
 * @author allisonklopp
 * @date Sept 13, 2014
 */
package com.catbird.presidents.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.catbird.presidents.R;

/**
 * TODO
 */
public class ScoresFragment extends Fragment{
    
	 @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container,
             Bundle savedInstanceState) {
         View rootView = inflater.inflate(R.layout.fragment_scores, container, false);
         TableLayout table = (TableLayout) rootView.findViewById(R.id.scoresTL);
         
         // Insert column headers for table
         TableRow columnHeaders = (TableRow) inflater.inflate(R.layout.row_scores, container, false);
         TextView col1 = (TextView) columnHeaders.findViewById(R.id.scoreAmountTV);
         col1.setText("Score");
         col1.setTypeface(Typeface.DEFAULT_BOLD);
         TextView col2 = (TextView) columnHeaders.findViewById(R.id.scoreDateTV);
         col2.setText("Date Earned");
         col2.setTypeface(Typeface.DEFAULT_BOLD);
         
         
         //TODO save this info and generate the scoreboard
         //TODO highlight best score - pick by checking all scores in table
         
         //Sample data
         TableRow row1 = (TableRow) inflater.inflate(R.layout.row_scores, container, false);
         TextView score1 = (TextView) row1.findViewById(R.id.scoreAmountTV);
         score1.setText("9000");
         TextView scoreDate1 = (TextView) row1.findViewById(R.id.scoreDateTV);
         scoreDate1.setText("Jan 12, 2014 10:01AM");
         
         TableRow row2 = (TableRow) inflater.inflate(R.layout.row_scores, container, false);
         TextView score2 = (TextView) row2.findViewById(R.id.scoreAmountTV);
         score2.setText("3200");
         TextView scoreDate2 = (TextView) row2.findViewById(R.id.scoreDateTV);
         scoreDate2.setText("Jan 11, 2014 1:20PM");
         
         TableRow row3 = (TableRow) inflater.inflate(R.layout.row_scores, container, false);
         TextView score3 = (TextView) row3.findViewById(R.id.scoreAmountTV);
         score3.setText("1030");
         TextView scoreDate3 = (TextView) row3.findViewById(R.id.scoreDateTV);
         scoreDate3.setText("Jan 10, 2014 09:40AM");
         
         table.addView(columnHeaders);
         table.addView(row1);
         table.addView(row2);
         table.addView(row3);
         
         return rootView;
     }
}
