package fr.didaktos.didaktos.controllers.activities.learn;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import fr.didaktos.didaktos.R;
import fr.didaktos.didaktos.injections.Injection;
import fr.didaktos.didaktos.injections.ViewModelFactory;
import fr.didaktos.didaktos.models.Card;
import fr.didaktos.didaktos.views.DeckViewModel;

public class TestActivity extends BaseLearnActivity implements View.OnClickListener{

    private String TAG = "TestActivity";

    //Database
    private DeckViewModel deckViewModel;

    private Button checkAnswerBtn;
    private EditText answerEditText;
    private TextView answerTextView;

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
        checkAnswerBtn = (Button)findViewById(R.id.test_check);
        checkAnswerBtn.setOnClickListener(this);
        checkAnswerBtn.setVisibility(View.VISIBLE);

        answerTextView = (TextView)findViewById(R.id.test_answer_txt);
        answerTextView.setVisibility(View.INVISIBLE);

        answerEditText = (EditText)findViewById(R.id.test_answer);
        answerEditText.setBackgroundColor(0);
        answerEditText.setText("");

        nextFab.setOnClickListener(this);
        nextFab.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.test_check :
                checkAnswerBtn.setVisibility(View.INVISIBLE);
                nextFab.setVisibility(View.VISIBLE);

                String correctAnswer = deck.getCards().get(cardNumber).getValue();

                if(answerEditText.getText().toString().equals(correctAnswer)){
                    answerEditText.setBackgroundColor(Color.GREEN);
                    deck.getCards().get(cardNumber).setStatus(2);
                    //Update status in database
                    this.deckViewModel.updateCard(deck.getCards().get(cardNumber));
                }else {
                    answerEditText.setBackgroundColor(Color.RED);
                    answerTextView.setVisibility(View.VISIBLE);
                    answerTextView.setText(correctAnswer);
                    deck.getCards().get(cardNumber).setStatus(0);
                }

                break;

            case R.id.fab:
                if(cardNumber>0){
                    showNextCard();
                }else{
                    endOfDeck();
                }
                break;
        }
    }

    private void configureViewModel(){
        ViewModelFactory viewModelFactory = Injection.provideViewModelFactory(this);
        this.deckViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(DeckViewModel.class);
        this.deckViewModel.init();
    }
}
