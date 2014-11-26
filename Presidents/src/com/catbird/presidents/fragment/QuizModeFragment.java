/**
 * @author allisonklopp
 * @date Sept 13, 2014
 */
package com.catbird.presidents.fragment;

import android.content.ClipData;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.catbird.presidents.Presidents;
import com.catbird.presidents.R;
import com.catbird.presidents.quiz.MultipleChoiceQuiz;

/**
 * Fragment displays the quiz questions in the content frame.
 */
public class QuizModeFragment extends Fragment {

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_quiz, container, false);
        LinearLayout layout = (LinearLayout) rootView.findViewById(R.id.quizLL);
        
        //TODO programmatically generate Qs
        
        // Sample question formulation
        // Add multiple choice question

        final MultipleChoiceQuiz mcQuiz = new MultipleChoiceQuiz(inflater, container);
        layout.addView(mcQuiz.getView());
        
        // Add drag-n-drop question
        View orderingQuestion = (View) inflater.inflate(R.layout.question_ordering, container, false);
        TextView orderingQuestionNo = (TextView) orderingQuestion.findViewById(R.id.orderingQuestionNo);
        orderingQuestionNo.setText(orderingQuestionNo.getText() + "2");
        TextView orderingQuestionText = (TextView) orderingQuestion.findViewById(R.id.orderingQuestionText);
        orderingQuestionText.setText("Chronologically order the presidents by the order of their terms:");
        
        //views to drag
        TextView option1 = (TextView) orderingQuestion.findViewById(R.id.orderingOption1TV);
        option1.setText(Presidents.NAMES[0]);
        TextView option2 = (TextView) orderingQuestion.findViewById(R.id.orderingOption2TV);
        option2.setText(Presidents.NAMES[1]);
        TextView option3 = (TextView) orderingQuestion.findViewById(R.id.orderingOption3TV);
        option3.setText(Presidents.NAMES[2]);
        
        //set touch listeners
        option1.setOnTouchListener(new ChoiceTouchListener());
        option2.setOnTouchListener(new ChoiceTouchListener());
        option3.setOnTouchListener(new ChoiceTouchListener());
         
        //views to drop onto
        TextView choice1 = (TextView) orderingQuestion.findViewById(R.id.choice_1);
        TextView choice2 = (TextView) orderingQuestion.findViewById(R.id.choice_2);
        TextView choice3 = (TextView) orderingQuestion.findViewById(R.id.choice_3);
        
        //set drag listeners
        choice1.setOnDragListener(new ChoiceDragListener());
        choice2.setOnDragListener(new ChoiceDragListener());
        choice3.setOnDragListener(new ChoiceDragListener());
        
        // Reset button sets the drag-and-drop items back in their original spots
        ImageView clearIcon = (ImageView) orderingQuestion.findViewById(R.id.clearIconIV);
        clearIcon.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// Reset the question
				View orderingQuestion = (View) v.getParent().getParent();
				TextView choice1 = (TextView) orderingQuestion.findViewById(R.id.choice_1);
		        TextView choice2 = (TextView) orderingQuestion.findViewById(R.id.choice_2);
		        TextView choice3 = (TextView) orderingQuestion.findViewById(R.id.choice_3);
		        
		        choice1.setText(getResources().getText(R.string.ordering_choice_1));
		        choice1.setTypeface(Typeface.DEFAULT);
		        choice2.setText(getResources().getText(R.string.ordering_choice_2));
		        choice2.setTypeface(Typeface.DEFAULT);
		        choice3.setText(getResources().getText(R.string.ordering_choice_3));
		        choice3.setTypeface(Typeface.DEFAULT);
		        
		        TextView option1 = (TextView) orderingQuestion.findViewById(R.id.orderingOption1TV);
		        option1.setText(Presidents.NAMES[0]);
		        option1.setVisibility(View.VISIBLE);
		        TextView option2 = (TextView) orderingQuestion.findViewById(R.id.orderingOption2TV);
		        option2.setText(Presidents.NAMES[1]);
		        option2.setVisibility(View.VISIBLE);
		        TextView option3 = (TextView) orderingQuestion.findViewById(R.id.orderingOption3TV);
		        option3.setText(Presidents.NAMES[2]);
		        option3.setVisibility(View.VISIBLE);
		        
				// Clear the ordering question back to default
				choice1.setText(getResources().getText(R.string.ordering_choice_1));
			}
		});
        
        layout.addView(orderingQuestion);
        
        // add submit button
        Button submitButton = (Button) rootView.findViewById(R.id.quizSubmitButton);
        submitButton.setText("Submit");
        submitButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				View parent = (View) v.getParent().getParent();
				// TODO populate below based on questions programmatically generated
				
				boolean passedOrderingQ = false;
				TextView choice1 = (TextView) parent.findViewById(R.id.choice_1);
		        TextView choice2 = (TextView) parent.findViewById(R.id.choice_2);
		        TextView choice3 = (TextView) parent.findViewById(R.id.choice_3);
				if(choice1!= null 
						&&choice2!= null 
						&& choice3!= null 
						&& choice1.getText().equals(Presidents.NAMES[0])
						&& choice2.getText().equals(Presidents.NAMES[1])
						&& choice3. getText().equals(Presidents.NAMES[2]) )
				{
					passedOrderingQ = true;
				}

				if (passedOrderingQ && mcQuiz.isCompleted())
				{

					if(mcQuiz.isCorrect())
					{
						Toast.makeText(getActivity(), "Correct!", Toast.LENGTH_SHORT).show();
					}
					else
						Toast.makeText(getActivity(), "Wrong!", Toast.LENGTH_SHORT).show();
				}
				else if(!passedOrderingQ)
				{
					Toast.makeText(getActivity(), "Wrong!", Toast.LENGTH_SHORT).show();
				}
				else
				{
					Toast.makeText(getActivity(), "You didn't finish the quiz.", Toast.LENGTH_SHORT).show();
				}
			}
		});
        
        return rootView;
    }
	
	/**
	 * Listener for the touch event preceding drag-and-drop quiz type
	 */
	private final class ChoiceTouchListener implements OnTouchListener {

		//TODO investigate warning
		@Override
		public boolean onTouch(View view, MotionEvent event) {
			switch (event.getAction()) {
		    case MotionEvent.ACTION_DOWN:
		    	//setup drag
				ClipData data = ClipData.newPlainText("", "");
				DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
				
				//start dragging the item touched
				view.startDrag(data, shadowBuilder, view, 0);
				
			    return true;
		    case MotionEvent.ACTION_UP:
		        view.performClick();
		        break;
		    default:
		        break;
		    }
		    return false;
			
		}
		 
	}
	
	/**
	 * Listener for the drag-and-drop quiz type
	 */
	private class ChoiceDragListener implements OnDragListener {

		@Override
		public boolean onDrag(View v, DragEvent event) {
			
			switch (event.getAction()) {
		    case DragEvent.ACTION_DRAG_STARTED:
		        //no action necessary
		        break;
		    case DragEvent.ACTION_DRAG_ENTERED:
		        //no action necessary
		        break;
		    case DragEvent.ACTION_DRAG_EXITED:        
		        //no action necessary
		        break;
		    case DragEvent.ACTION_DROP:
		    	//handle the dragged view being dropped over a target (drop) view
		    	View view = (View) event.getLocalState();
		    	
		    	//stop displaying the view where it was before it was dragged
		    	view.setVisibility(View.GONE);
		    	
		    	//view dragged item is being dropped on
		    	TextView dropTarget = (TextView) v;
		    	
		    	//view being dragged and dropped
		    	TextView dropped = (TextView) view;
		    	
		    	//if an item has already been dropped here, there will be a tag
		    	Object tag = dropTarget.getTag();
		    	
		    	//if there is already an item here, set it back visible in its original place
		    	if(tag!=null)
		    	{
		    	    //the tag is the view id already dropped here
		    	    int existingID = (Integer)tag;
		    	    
		    	    //set the original view visible again
		    	    //((View) v.getParent()).findViewById(existingID).setVisibility(View.VISIBLE);
		    	    dropped.setVisibility(View.VISIBLE);
		    	    
		    	    // Switch the texts
		    	    //String temp = ((TextView)((View) v.getParent()).findViewById(existingID)).getText();
		    	    String temp = (String) dropped.getText();
		    	    dropped.setText(dropTarget.getText());
		    	    dropTarget.setText(temp);
		    	    
		    	    //set the tag in the target view to the ID of the view being dropped
		    	    dropTarget.setTag(dropped.getId());
		    	}
		    	else
		    	{
		    		//update the text in the target view to reflect the data being dropped
		    		dropTarget.setText(dropped.getText());
		    	
		    		//make it bold to highlight the fact that an item has been dropped
		    		dropTarget.setTypeface(Typeface.DEFAULT_BOLD);
		    		
		    		//set the tag of the target as a filled box
		    		dropTarget.setTag(dropped.getId());
		    	}
		        break;
		    case DragEvent.ACTION_DRAG_ENDED:
		        //no action necessary
		        break;
		    default:
		        break;
			}
			
			return true;
		}
		 
	}
}
