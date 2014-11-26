/**
 * 
 */
package com.catbird.presidents.quiz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.catbird.presidents.Presidents;
import com.catbird.presidents.R;
import com.catbird.presidents.Utils;

/**
 * @author allisonklopp
 *
 */
public class MultipleChoiceQuiz {
	
	LayoutInflater inflater;
	ViewGroup container;
	View mcQuestion;
	RadioGroup radios;
	int correctAnswer;
	List<Integer> answers;
	// TODO default
	String questionStr = "";
	// TODO more questions!!!
	List<String> questions = Arrays.asList("Chronologically, who was president #");
	
	public MultipleChoiceQuiz (LayoutInflater inflater, ViewGroup container)
	{
		this.inflater = inflater;
		this.container = container;
		correctAnswer = Utils.randInt(0, (Presidents.NAMES.length - 1));
		
		answers = new ArrayList<Integer>();
		answers.add(correctAnswer);
		
		while(answers.size() < 4)
		{
			int newAnswer = Utils.randInt(0, (Presidents.NAMES.length - 1));
			if(!answers.contains(newAnswer))
			{
				answers.add(newAnswer);
			}
		}
	}

	public View getView()
	{
		mcQuestion = (View) inflater.inflate(R.layout.question_multiple_choice, container, false);

		TextView questionNo = (TextView) mcQuestion.findViewById(R.id.questionNo);
		questionNo.setText(questionNo.getText() + "1");

		TextView questionText = (TextView) mcQuestion.findViewById(R.id.questionText);
		questionText.setText(questionStr + (correctAnswer+1) +"?");
		RadioButton mc1 = (RadioButton) mcQuestion.findViewById(R.id.mc1);
		mc1.setText(Presidents.NAMES[answers.get(0)]);
		RadioButton mc2 = (RadioButton) mcQuestion.findViewById(R.id.mc2);
		mc2.setText(Presidents.NAMES[answers.get(1)]);
		RadioButton mc3 = (RadioButton) mcQuestion.findViewById(R.id.mc3);
		mc3.setText(Presidents.NAMES[answers.get(2)]);
		RadioButton mc4 = (RadioButton) mcQuestion.findViewById(R.id.mc4);
		mc4.setText(Presidents.NAMES[answers.get(3)]);
		
		// Initialize radios object for later reference
		radios = (RadioGroup) mcQuestion.findViewById(R.id.mcRG);

		return mcQuestion;
	}
	
	/**
	 * TODO
	 * @return
	 */
	public boolean isCompleted()
	{
		if(radios.getCheckedRadioButtonId() != -1)
			return true;
		
		return false;
	}
	
	/**
	 * TODO
	 * @return
	 */
	public boolean isCorrect()
	{
		RadioButton chosenRB = (RadioButton) mcQuestion.findViewById(radios.getCheckedRadioButtonId());

		if(chosenRB.getText().equals(Presidents.NAMES[correctAnswer]))
			return true;
			
		return false;
	}
}
