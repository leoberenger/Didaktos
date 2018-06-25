package fr.didaktos.didaktos.controllers.activities.learn;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import fr.didaktos.didaktos.R;

public class QuizActivity extends BaseLearnActivity implements View.OnClickListener{

    private String TAG = "QuizActivity";

    @Override
    protected View getValueLayout() {
        return getLayoutInflater().inflate(R.layout.learn_quiz,null);
    }

    @Override
    protected int getCardNumber() {
        return 0;
    }

    @Override
    protected void configureAnswer() {

        String [] answers = {deck.getCards().get(0).getValue(),
                deck.getCards().get(1).getValue(),
                deck.getCards().get(2).getValue(),
                "Answer 4"};

        shuffleArray(answers);

        Button answer0 = (Button) findViewById(R.id.quiz_answer_0_btn);
        Button answer1 = (Button) findViewById(R.id.quiz_answer_1_btn);
        Button answer2 = (Button) findViewById(R.id.quiz_answer_2_btn);
        Button answer3 = (Button) findViewById(R.id.quiz_answer_3_btn);

        Button [] buttons = {answer0, answer1, answer2, answer3};
        for (int i = 0; i<buttons.length; i++){
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


}
