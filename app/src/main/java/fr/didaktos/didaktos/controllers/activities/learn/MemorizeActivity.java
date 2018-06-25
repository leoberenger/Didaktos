package fr.didaktos.didaktos.controllers.activities.learn;

import android.view.View;
import android.widget.TextView;

import fr.didaktos.didaktos.R;

public class MemorizeActivity extends BaseLearnActivity {

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
        TextView answer = (TextView)findViewById(R.id.memorize_answer);
        answer.setText(deck.getCards().get(0).getValue());
    }

}
