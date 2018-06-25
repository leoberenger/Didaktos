package fr.didaktos.didaktos.controllers.fragments.learn;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.didaktos.didaktos.R;
import fr.didaktos.didaktos.models.DeckWithCards;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizFragment extends Fragment {

    private String TAG = "QuizFragment";

    @BindView(R.id.quiz_question) TextView questionTextView;
    @BindView(R.id.quiz_answer_1_btn) Button answer1Btn;
    @BindView(R.id.quiz_answer_2_btn) Button answer2Btn;
    @BindView(R.id.quiz_answer_3_btn) Button answer3Btn;
    @BindView(R.id.quiz_answer_4_btn) Button answer4Btn;


    public QuizFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_quiz, container, false);
        ButterKnife.bind(this, v);

        DeckWithCards deck = getArguments().getParcelable(DeckWithCards.DECK_KEY);
        this.configureAnswers(deck);

        return v;
    }

    private void configureAnswers(DeckWithCards deck){
        questionTextView.setText(deck.getCards().get(0).getKey());

        answer1Btn.setText(deck.getCards().get(0).getValue());
        answer2Btn.setText(deck.getCards().get(1).getValue());
        answer3Btn.setText(deck.getCards().get(2).getValue());
        answer4Btn.setText("Answer 4");
    }

}
