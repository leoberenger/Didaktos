package fr.didaktos.didaktos.controllers.activities.learn;

import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import fr.didaktos.didaktos.R;

public class TestActivity extends BaseLearnActivity implements View.OnClickListener{

    private String TAG = "TestActivity";

    private Button checkAnswerBtn;
    private EditText answerEditText;
    private TextView answerTextView;


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
                }else {
                    answerEditText.setBackgroundColor(Color.RED);
                    answerTextView.setVisibility(View.VISIBLE);
                    answerTextView.setText(correctAnswer);
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
}
