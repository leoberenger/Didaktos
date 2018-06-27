package fr.didaktos.didaktos.controllers.activities.learn;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import fr.didaktos.didaktos.R;
import fr.didaktos.didaktos.injections.Injection;
import fr.didaktos.didaktos.injections.ViewModelFactory;
import fr.didaktos.didaktos.models.Card;
import fr.didaktos.didaktos.views.DeckViewModel;

import static android.view.View.GONE;

public class TestActivity extends BaseLearnActivity
        implements View.OnClickListener, EditText.OnEditorActionListener{

    private String TAG = "TestActivity";

    //Database
    private DeckViewModel deckViewModel;

    private EditText answerEditText;
    private TextView answerTextView;
    private TextView wrongAnswerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.configureViewModel();

    }
    @Override
    protected View getValueLayout() {
        return getLayoutInflater().inflate(R.layout.learn_test,null);
    }

    @Override
    protected void configureAnswer() {
        answerTextView = (TextView) findViewById(R.id.test_answer_txt);
        answerTextView.setVisibility(GONE);

        wrongAnswerTextView = (TextView)findViewById(R.id.test_user_wrong_answer_txt);
        wrongAnswerTextView.setVisibility(GONE);

        answerEditText = (EditText) findViewById(R.id.test_answer);
        answerEditText.setVisibility(View.VISIBLE);
        answerEditText.setText("");
        answerEditText.setOnEditorActionListener(this);

        nextFab.setOnClickListener(this);
        nextFab.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                if (cardNumber > 0) {
                    showNextCard();
                } else {
                    endOfDeck();
                }
                break;
        }
    }

    private void configureViewModel() {
        ViewModelFactory viewModelFactory = Injection.provideViewModelFactory(this);
        this.deckViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(DeckViewModel.class);
        this.deckViewModel.init();
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                actionId == EditorInfo.IME_ACTION_DONE ||
                event != null &&
                event.getAction() == KeyEvent.ACTION_DOWN &&
                event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {

            if (event == null || !event.isShiftPressed()) {

                nextFab.setVisibility(View.VISIBLE);

                String correctAnswer = deck.getCards().get(cardNumber).getValue();

                if (answerEditText.getText().toString().equals(correctAnswer)) {

                    answerEditText.setVisibility(GONE);

                    answerTextView.setVisibility(View.VISIBLE);
                    String goodAnswer = correctAnswer + " V";
                    answerTextView.setText(goodAnswer);

                    //Update status in database
                    Card successfulCard = deck.getCards().get(cardNumber);
                    successfulCard.setStatus(2);
                    successfulCard.setDeckId(deck.getId());
                    deckViewModel.updateCard(successfulCard);

                } else {
                    answerEditText.setVisibility(GONE);
                    wrongAnswerTextView.setVisibility(View.VISIBLE);
                    String wrongAnswer = answerEditText.getText().toString() + " X";
                    wrongAnswerTextView.setText(wrongAnswer);

                    answerTextView.setVisibility(View.VISIBLE);
                    answerTextView.setText(correctAnswer);

                    deck.getCards().get(cardNumber).setStatus(0);
                }

                return true; // consume.
            }
        }
        return false; // pass on to other listeners.
    }
}