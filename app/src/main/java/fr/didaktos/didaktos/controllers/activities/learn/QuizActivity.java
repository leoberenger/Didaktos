package fr.didaktos.didaktos.controllers.activities.learn;

import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
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
    protected void configureAnswer() {

        String [] answers = {
                deck.getCards().get(0).getValue(),
                deck.getCards().get(1).getValue(),
                deck.getCards().get(2).getValue(),
                deck.getCards().get(cardNumber).getValue()};

        shuffleArray(answers);

        buttons[0] = (Button) findViewById(R.id.quiz_answer_0_btn);
        buttons[1] = (Button) findViewById(R.id.quiz_answer_1_btn);
        buttons[2] = (Button) findViewById(R.id.quiz_answer_2_btn);
        buttons[3] = (Button) findViewById(R.id.quiz_answer_3_btn);

        for (int i = 0; i<buttons.length; i++){
            buttons[i].setBackgroundColor(0);
            buttons[i].setOnClickListener(this);
            buttons[i].setText(answers[i]);
        }

        nextFab.setOnClickListener(this);
        nextFab.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.quiz_answer_0_btn: case R.id.quiz_answer_1_btn:
            case R.id.quiz_answer_2_btn: case R.id.quiz_answer_3_btn:

                for(Button button : buttons){
                    if(button.getText().toString().equals(deck.getCards().get(cardNumber).getValue())){
                        button.setBackgroundColor(Color.GREEN);
                    }else {
                        button.setBackgroundColor(Color.RED);
                    }
                }
                nextFab.setVisibility(View.VISIBLE);
                break;

            case R.id.fab :
                showNextCard();
                break;
        }
    }
}
