package fr.didaktos.didaktos.controllers.activities.learn;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import fr.didaktos.didaktos.R;

public class TestActivity extends BaseLearnActivity implements View.OnClickListener{

    private String TAG = "TestActivity";

    @Override
    protected View getValueLayout() {
        return getLayoutInflater().inflate(R.layout.learn_test,null);
    }

    @Override
    protected int getCardNumber() {
        return 0;
    }

    @Override
    protected void configureAnswer() {
        Button checkAnswerBtn = (Button)findViewById(R.id.test_check);
        checkAnswerBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        EditText answerEditText = (EditText)findViewById(R.id.test_answer);

        if(answerEditText.getText().toString().equals(deck.getCards().get(0).getValue())){
            Log.e(TAG, "Good answer");
        }else {
            Log.e(TAG, "Wrong answer");
        }
    }
}
