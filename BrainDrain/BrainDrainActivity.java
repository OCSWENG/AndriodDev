package com.example.braindrain;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.GregorianCalendar;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ListIterator;
import java.util.Random;



public class BrainDrainActivity extends AppCompatActivity {
    private Random  rdm;
    private int currentAns;
    private float score;
    private float totalQuestions;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button startStopButton;
    private EditText timer;
    private TextView scoreCard;
    private TextView problem;
    private TextView status;
    private String operand;
    private boolean startState;
    private CountDownTimer countDown;
    public int generateNumber () {
        return (rdm.nextInt(100));
    }

    public ArrayList<Integer> generateOrder (){
        HashSet<Integer> choices = new HashSet<>();
        ArrayList<Integer> order = new ArrayList<Integer>();
        while ( choices.size() != 4) {
            int choice = rdm.nextInt(4);
            if (!choices.contains(choice)) {
                order.add(choice);
                choices.add(choice);
            }
        }
        return order;
    }

    public ArrayList<Integer> generatePossibleAnswers(int answer){
        ArrayList<Integer> answers =  new ArrayList<Integer> ();

        do {
            int possAns = generateNumber();
            if (possAns != answer ){
                answers.add(possAns);
            }
        } while (answers.size() < 3);
        return answers;
    }

    public int obtainAnswer (int op1, int op2, String operand){
        int answer = 0;
        if (operand == " + ")
        {
            answer = op1 + op2;
        }
        else if (operand == " - ") {

        }
        else if (operand == " / "){

        }
        else if (operand == " * ") {

        }
        else {
//            throw (new Exception ( "Unkown operand " + operand));
        }
        return answer;
    }

    public void generateProblem () {
        // compute a problem
        int op1 = generateNumber();
        int op2 = generateNumber();

        // obtain the answer
        currentAns = obtainAnswer (op1 , op2, operand);
        ArrayList<Integer> possAnswers = new ArrayList<>();
        possAnswers.add(currentAns);
        // obtain 3 possible answers

        for ( int itr=0; itr < 3; ++itr) {
            int op1a = 0;
            int op2a = 0;
            int anotherAnswer = 0;
            do {
                op1a = op1 - (rdm.nextInt(5) + 1);
                op2a = op2 + (rdm.nextInt(5) + 1);
                anotherAnswer = obtainAnswer (op1a , op2a, operand);
            } while( anotherAnswer == currentAns);

            possAnswers.add(anotherAnswer);
        }

        // display problem and answers
        String problemStatement = Integer.toString(op1);
        problemStatement += operand;
        problemStatement += op2;

        ArrayList<Integer> order = generateOrder();

        ListIterator<Integer> iter = order.listIterator();
        int possAnswersArray[] = new int[4];

        int count = 0;
        while (iter.hasNext()) {
            int choice = iter.next();
            int possAnswer = possAnswers.get(choice);
            possAnswersArray[count] = possAnswer;
            count++;
        }

        button1.setText(Integer.toString(possAnswersArray[0]));
        button2.setText(Integer.toString(possAnswersArray[1]));
        button3.setText(Integer.toString(possAnswersArray[2]));
        button4.setText(Integer.toString(possAnswersArray[3]));
        problem.setText(problemStatement);
        totalQuestions += 1;
    }


    public void onStartClick (View view) {


        // while
        if (startState == false) {
            generateProblem ();
            startState = true;
            startStopButton.setText("STOP");
            scoreCard.setText("0.0");
            score = 0.0f;
            totalQuestions = 0.0f;
            countDown = new CountDownTimer(30100, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {

                    timer.setText(String.valueOf(millisUntilFinished / 1000));

                }

                @Override
                public void onFinish() {
                    timer.setText("0");
                    scoreCard.setText("Your score: " + Float.toString(score) + "/" + Float.toString(totalQuestions));

                }
            }.start();
            timer.setVisibility(View.VISIBLE);

        }
        else {
            startStopButton.setText("START");
            startState = false;
            timer.setText("0");
            countDown.cancel();
            timer.setVisibility(View.INVISIBLE);
        }

    }

    public void checkAnswer ( View view) {
        int timerVal = 1;

        // while
        if (timerVal > 0 && startState == false) {
            int id = view.getId();

            Button button = (Button) view;
            int guess = Integer.parseInt(button.getText().toString());

            if ( currentAns == guess){
                score +=1;
                status.setBackgroundColor(Color.GREEN);
                status.setText("CORRECT");

            }
            else {
                status.setBackgroundColor(Color.RED);
                status.setText("WRONG");
            }

            float precent = Math.round(score/totalQuestions*100);

            scoreCard.setText(Float.toString(precent));
            generateProblem ();
            status.setBackgroundColor(Color.WHITE);

        }
        else {
            startStopButton.setText("START");
            startState = false;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brain_drain);

        startState = false;
        score = 0.0f;
        totalQuestions = 0.0f;

        GregorianCalendar time = new GregorianCalendar();
        rdm = new Random();
        rdm.setSeed(time.getTimeInMillis());

        button1 = (Button) findViewById(R.id.Answer1);
        button2 = (Button) findViewById(R.id.Answer2);
        button3 = (Button) findViewById(R.id.Answer3);
        button4 = (Button) findViewById(R.id.Answer4);
        startStopButton = (Button) findViewById(R.id.StartStop);
        timer = (EditText) findViewById(R.id.Timer);
        scoreCard = (TextView) findViewById(R.id.ScoreCard);
        problem = (TextView) findViewById(R.id.Problem);

        status = (TextView)findViewById(R.id.Status);
        timer.setText("0");
        operand = " + ";
    }
}
