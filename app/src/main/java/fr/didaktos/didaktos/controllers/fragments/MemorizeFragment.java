package fr.didaktos.didaktos.controllers.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.didaktos.didaktos.R;
import fr.didaktos.didaktos.models.DeckWithCards;

/**
 * A simple {@link Fragment} subclass.
 */
public class MemorizeFragment extends Fragment{

    private String TAG = "Memorize Fragment";
    OnMemorizeAnswerListener mCallback;

    @BindView(R.id.memorize_answer_btn) Button answerBtn;
    @BindView(R.id.memorize_answer_txt) TextView answerText;

    public MemorizeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.learn_memorize, container, false);
        ButterKnife.bind(this, view);

        String answer = getArguments().getString(DeckWithCards.ANSWER_KEY);
        this.configureAnswer(answer);

        return view;
    }

    //--------------------
    // CONFIGURATION
    //-------------------

    private void configureAnswer(String answer) {
        answerText.setText(answer);
        answerBtn.setVisibility(View.VISIBLE);
        answerText.setVisibility(View.INVISIBLE);
    }


    //--------------------
    // ACTION
    //-------------------

    @OnClick(R.id.memorize_answer_btn)
    public void seeAnswer(View v) {
        answerBtn.setVisibility(View.INVISIBLE);
        answerText.setVisibility(View.VISIBLE);
        mCallback.onMemorizeAnswer();
    }


    //--------------------
    // CALLBACK
    //-------------------

    public interface OnMemorizeAnswerListener {
        void onMemorizeAnswer();
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);

        try {
            mCallback = (OnMemorizeAnswerListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnAnswerListener");
        }
    }

}
