package fr.didaktos.didaktos.controllers.activities.learn;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import fr.didaktos.didaktos.R;

public class MemorizeActivity extends BaseLearnActivity implements View.OnClickListener {

    Button answerBtn;

    @Override
    protected View getValueLayout() {
        return getLayoutInflater().inflate(R.layout.learn_memorize,null);
    }

    @Override
    protected int getCardNumber() {
        return 0;
    }

    @Override
    protected void configureAnswer() {
        answerBtn = (Button)findViewById(R.id.memorize_answer_btn);
        answerBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        answerBtn.setVisibility(View.INVISIBLE);

        TextView answer = (TextView)findViewById(R.id.memorize_answer_txt);
        answer.setText(deck.getCards().get(0).getValue());
        answer.setVisibility(View.VISIBLE);

        nextFab.setVisibility(View.VISIBLE);
    }
}
