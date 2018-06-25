package fr.didaktos.didaktos.controllers.activities.learn;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;

import fr.didaktos.didaktos.R;

public class QuizActivity extends BaseLearnActivity implements View.OnClickListener{

    private String TAG = "QuizActivity";

    private final Button [] buttons = new Button[4];

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

        buttons[0] = (Button) findViewById(R.id.quiz_answer_0_btn);
        buttons[1] = (Button) findViewById(R.id.quiz_answer_1_btn);
        buttons[2] = (Button) findViewById(R.id.quiz_answer_2_btn);
        buttons[3] = (Button) findViewById(R.id.quiz_answer_3_btn);

        for (int i = 0; i<buttons.length; i++){
            buttons[i].setOnClickListener(this);
            buttons[i].setTag(i);
            buttons[i].setText(answers[i]);
        }
    }

    @Override
    public void onClick(View v) {

        for(Button button : buttons){
            if(button.getText().toString().equals(deck.getCards().get(0).getValue())){
                button.setBackgroundColor(Color.GREEN);
            }else {
                button.setBackgroundColor(Color.RED);
            }
        }

        nextFab.setVisibility(View.VISIBLE);
    }


}
