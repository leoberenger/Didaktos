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

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.didaktos.didaktos.R;
import fr.didaktos.didaktos.models.DeckWithCards;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizFragment extends Fragment implements View.OnClickListener{

    private String TAG = "QuizFragment";
    private DeckWithCards deck;

    @BindView(R.id.quiz_question) TextView questionTextView;
    @BindView(R.id.quiz_answer_0_btn) Button answer0Btn;
    @BindView(R.id.quiz_answer_1_btn) Button answer1Btn;
    @BindView(R.id.quiz_answer_2_btn) Button answer2Btn;
    @BindView(R.id.quiz_answer_3_btn) Button answer3Btn;


    public QuizFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_quiz, container, false);
        ButterKnife.bind(this, v);

        deck = getArguments().getParcelable(DeckWithCards.DECK_KEY);
        this.configureAnswers(deck);

        return v;
    }

    private void configureAnswers(DeckWithCards deck){
        questionTextView.setText(deck.getCards().get(0).getKey());

        String [] answers = {deck.getCards().get(0).getValue(),
                deck.getCards().get(1).getValue(),
                deck.getCards().get(2).getValue(),
                "Answer 4"};

        shuffleArray(answers);

        Button [] buttons = {answer0Btn, answer1Btn, answer2Btn, answer3Btn};
        for (int i = 0; i<buttons.length; i++){
            buttons[i].setTag(i);
            buttons[i].setOnClickListener(this);
            buttons[i].setText(answers[i]);
        }
    }


    @Override
    public void onClick(View v) {
        Button b = (Button)v;
        String buttonText = b.getText().toString();

        if(buttonText == deck.getCards().get(0).getValue()){
            Log.e(TAG, "Good answer");
        }else {
            Log.e(TAG, "Wrong answer");
        }
    }

    static void shuffleArray(String[] ar)
    {
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            String a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }
}
