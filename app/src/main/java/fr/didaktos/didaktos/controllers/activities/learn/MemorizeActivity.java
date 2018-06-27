package fr.didaktos.didaktos.controllers.activities.learn;

import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import fr.didaktos.didaktos.R;

public class MemorizeActivity extends BaseLearnActivity implements View.OnClickListener {

    private String TAG = "Memorize Activity";

    private Button answerBtn;
    private TextView answer;

    @Override
    protected View getValueLayout() {
        return getLayoutInflater().inflate(R.layout.learn_memorize,null);
    }

    @Override
    protected void configureAnswer() {
        answerBtn = (Button)findViewById(R.id.memorize_answer_btn);
        answerBtn.setOnClickListener(this);

        nextFab.setOnClickListener(this);

        answer = (TextView)findViewById(R.id.memorize_answer_txt);
        answer.setText(deck.getCards().get(cardNumber).getValue());

        answerBtn.setVisibility(View.VISIBLE);
        answer.setVisibility(View.INVISIBLE);
        nextFab.setVisibility(View.INVISIBLE);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.memorize_answer_btn :
                answerBtn.setVisibility(View.INVISIBLE);
                answer.setVisibility(View.VISIBLE);
                nextFab.setVisibility(View.VISIBLE);
                break;

            case R.id.next_fab:
                if(cardNumber>0){
                    showNextCard();
                }else{
                    endOfDeck();
                }
                break;
        }
    }
}
