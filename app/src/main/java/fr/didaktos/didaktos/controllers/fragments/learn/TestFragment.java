package fr.didaktos.didaktos.controllers.fragments.learn;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.didaktos.didaktos.R;
import fr.didaktos.didaktos.models.Card;
import fr.didaktos.didaktos.models.DeckWithCards;

import static android.view.View.GONE;

/**
 * A simple {@link Fragment} subclass.
 */
public class TestFragment extends Fragment implements TextView.OnEditorActionListener {

    private String TAG = "Test Fragment";

    OnTestAnswerListener mCallback;
    private String answer;

    @BindView(R.id.test_answer) EditText answerEditText;
    @BindView(R.id.test_answer_txt) TextView answerTextView;
    @BindView(R.id.test_wrong_answer_txt) TextView wrongAnswerTextView;

    public TestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.learn_test, container, false);
        ButterKnife.bind(this, view);

        answer = getArguments().getString(DeckWithCards.ANSWER_KEY);
        this.configureAnswer();

        return view;
    }

    //--------------------
    // CONFIGURATION
    //-------------------

    private void configureAnswer() {
        answerTextView.setVisibility(GONE);
        wrongAnswerTextView.setVisibility(GONE);

        answerEditText.setVisibility(View.VISIBLE);
        answerEditText.setText("");
        answerEditText.setOnEditorActionListener(this);
    }

    private void showCorrectAnswer(){
        answerEditText.setVisibility(GONE);

        answerTextView.setVisibility(View.VISIBLE);
        String goodAnswer = answer + " V";
        answerTextView.setText(goodAnswer);
    }

    private void showWrongAnswer(){
        answerEditText.setVisibility(GONE);

        wrongAnswerTextView.setVisibility(View.VISIBLE);
        String wrongAnswer = answerEditText.getText().toString() + " X";
        wrongAnswerTextView.setText(wrongAnswer);

        answerTextView.setVisibility(View.VISIBLE);
        answerTextView.setText(answer);
    }


    //--------------------
    // ACTION
    //-------------------

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                actionId == EditorInfo.IME_ACTION_DONE ||
                event != null &&
                        event.getAction() == KeyEvent.ACTION_DOWN &&
                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {

            if (event == null || !event.isShiftPressed()) {

                if (answerEditText.getText().toString().equals(answer)) {
                    showCorrectAnswer();
                    mCallback.onTestAnswer(true);
                } else {
                    showWrongAnswer();
                    mCallback.onTestAnswer(false);
                }

                return true;
            }
        }
        return false;
    }


    //--------------------
    // CALLBACK
    //-------------------

    public interface OnTestAnswerListener {
        void onTestAnswer(boolean success);
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);

        try {
            mCallback = (OnTestAnswerListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnAnswerListener");
        }
    }

}
