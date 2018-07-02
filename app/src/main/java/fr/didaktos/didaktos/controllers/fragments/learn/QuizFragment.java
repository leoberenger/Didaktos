package fr.didaktos.didaktos.controllers.fragments.learn;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.didaktos.didaktos.R;
import fr.didaktos.didaktos.models.DeckWithCards;
import fr.didaktos.didaktos.utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizFragment extends Fragment
implements View.OnClickListener{

    private String TAG = "Quiz Fragment";

    OnQuizAnswerListener mCallback;

    private boolean success = false;
    private String answer;
    private ArrayList<String> alternatives;

    private Button [] buttons;
    @BindView(R.id.quiz_answer_0_btn) Button button0;
    @BindView(R.id.quiz_answer_1_btn) Button button1;
    @BindView(R.id.quiz_answer_2_btn) Button button2;
    @BindView(R.id.quiz_answer_3_btn) Button button3;


    public QuizFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.learn_quiz, container, false);
        ButterKnife.bind(this, view);

        answer = getArguments().getString(DeckWithCards.ANSWER_KEY);
        alternatives = getArguments().getStringArrayList(DeckWithCards.ALTERNATIVES_KEY);

        this.configureAnswer(answer, alternatives);

        return view;
    }

    //--------------------
    // CONFIGURATION
    //-------------------

    private void configureAnswer(String answer, ArrayList<String> alternatives) {
        String [] answers = new String[4];
        answers[0] = answer;

        for(int i = 1; i<answers.length; i++){
            answers[i] = alternatives.get(i-1);
        }

        Utils.shuffleArray(answers);

        buttons = new Button[]{button0, button1, button2, button3};
        for (int i = 0; i<buttons.length; i++){
            buttons[i].setOnClickListener(this);
            buttons[i].setText(answers[i]);
        }
    }


    //--------------------
    // ACTION
    //-------------------

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.quiz_answer_0_btn: case R.id.quiz_answer_1_btn:
            case R.id.quiz_answer_2_btn: case R.id.quiz_answer_3_btn:

                for(Button button : buttons){
                    if(button.getText().toString().equals(answer)){
                        button.setBackgroundColor(Color.WHITE);
                        button.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                        success = true;
                    }else{
                        button.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        button.setTextColor(getResources().getColor(R.color.colorGrey));
                        success = false;
                    }
                }
                mCallback.onQuizAnswer(success);
                break;
        }
    }


    //--------------------
    // CALLBACK
    //-------------------

    public interface OnQuizAnswerListener {
        void onQuizAnswer(boolean success);
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);

        try {
            mCallback = (OnQuizAnswerListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnAnswerListener");
        }
    }

}
