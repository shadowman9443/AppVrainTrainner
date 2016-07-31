package com.example.shadow.braintrainerapp;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends Activity {
    Button startButton;
    TextView resultextView,pointsTextView,sumTextView,timerTextView,scoreTextView;
    ArrayList<Integer> answer=new ArrayList<Integer>();
    int posofCorrectAnswer;
    Button buttonOne,buttonTwo,buttonThree,buttonFour,playAgainButton;
    RelativeLayout gameLayout;

    int score=0;

    int numberOfQuestion;

    public void playAgain(View view){
        score=0;
        numberOfQuestion=0;

        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(RelativeLayout.VISIBLE);
        scoreTextView.setVisibility(View.INVISIBLE);
        generateQuestion();

        new CountDownTimer(20100,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);
                scoreTextView.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                scoreTextView.setText("Your score"+Integer.toString(score)+"/"+Integer.toString(numberOfQuestion));
                gameLayout.setVisibility(RelativeLayout.INVISIBLE);

            }
        }.start();

    }

    public void generateQuestion(){
        Random rand=new Random();

        int a=rand.nextInt(21);
        int b=rand.nextInt(21);

        sumTextView.setText(Integer.toString(a)+"+"+Integer.toString(b));

        posofCorrectAnswer=rand.nextInt(4);

        answer.clear();

        int inCorrectAnswer;
        for(int i=0;i<4;i++){

            if(i==posofCorrectAnswer){
                answer.add(a+b);
            }else{
                inCorrectAnswer=rand.nextInt(41);
                while (inCorrectAnswer==a+b){
                    inCorrectAnswer=rand.nextInt(41);
                }
                answer.add(inCorrectAnswer);
            }
        }
        buttonOne.setText(Integer.toString(answer.get(0)));
        buttonTwo.setText(Integer.toString(answer.get(1)));
        buttonThree.setText(Integer.toString(answer.get(2)));
        buttonFour.setText(Integer.toString(answer.get(3)));
    }

    public void chooseAnswer(View view){

        if(view.getTag().toString().equals(Integer.toString(posofCorrectAnswer))){
            Log.i("tag","correct");
            score++;
            resultextView.setText("Correct");
        }else {
            resultextView.setText("Incorrect");
        }
        numberOfQuestion++;
        pointsTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestion));
        generateQuestion();
    }

    public void start(View view){

        startButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(RelativeLayout.VISIBLE);
        playAgain(findViewById(R.id.buttonPLayAgain));
        scoreTextView.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton= (Button) findViewById(R.id.startbutton);
        sumTextView= (TextView) findViewById(R.id.sumtextView);
        buttonOne= (Button) findViewById(R.id.buttonOne);
        buttonTwo= (Button) findViewById(R.id.buttonTwo);
        buttonThree= (Button) findViewById(R.id.buttonThree);
        buttonFour= (Button) findViewById(R.id.buttonFour);
        resultextView= (TextView) findViewById(R.id.correcttextView);
        pointsTextView= (TextView) findViewById(R.id.pointstextView);
        timerTextView= (TextView) findViewById(R.id.timertextView);
        playAgainButton= (Button) findViewById(R.id.buttonPLayAgain);
        gameLayout= (RelativeLayout) findViewById(R.id.gameRelativeLayout);
        scoreTextView= (TextView) findViewById(R.id.scoreTextView);
    }
}
