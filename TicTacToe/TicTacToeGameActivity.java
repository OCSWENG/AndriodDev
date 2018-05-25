package com.example.someDirectory.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class TicTacToeGameActivity extends  AppCompatActivity{

    final int yellowPlayer = 0;
    final int redPlayer = 1;
    final int unplayed = 2;
    int redScore = 0;
    int yellowScore = 0;

    boolean gameIsActive = false;

    enum player {UNPLAYED, YELLOW, RED};

    // user is yellow,  AI or  player2 is red
    player activePlayer = player.YELLOW;

    player [] gameState = {player.UNPLAYED, player.UNPLAYED, player.UNPLAYED,
            player.UNPLAYED, player.UNPLAYED, player.UNPLAYED,
            player.UNPLAYED, player.UNPLAYED, player.UNPLAYED};


    int [] winPos1 = {0,1,2};
    int [] winPos2 = {3,4,5};
    int [] winPos3 = {6,7,8};

    int [] winPos4 = {0,3,6};
    int [] winPos5 = {1,4,7};
    int [] winPos6 = {2,5,8};

    int [] winPos7 = {0,4,8};
    int [] winPos8 = {2,4,6};
    /*      GRID

        0   1   2

        3   4   5

        6   7   8
     */

    int[][] winningPositions = {winPos1, winPos2, winPos3, winPos4, winPos5, winPos6, winPos7, winPos8};

    public void changePlayer (View view) {}
    public void startStop (View view) {
        changeStartStop ( );
        restart(view);
    }

    private boolean changeGameSwitch (){
        if ( gameIsActive == true) {
            gameIsActive = false;
        }
        else {
            gameIsActive = true;
        }
        return gameIsActive;
    }

    private void changeStartStop ( ) {
        Switch startStop  = (Switch)findViewById(R.id.player);

        startStop.setChecked(changeGameSwitch());

    }

    public void topLeftActOn (View view) {
        ImageButton box = (ImageButton) view;
        actOn(box , 0);
    }

    public void topCenterActOn (View view) {
        ImageButton box = (ImageButton) view;
        actOn(box , 1);
    }

    public void topRightActOn (View view) {
        ImageButton box = (ImageButton) view;
        actOn(box , 2);
    }

    public void centerLeftActOn (View view) {
        ImageButton box = (ImageButton) view;
        actOn(box , 3);
    }

    public void centerActOn (View view) {
        ImageButton box = (ImageButton) view;
        actOn(box , 4);
    }

    public void centerRightActOn (View view) {
        ImageButton box = (ImageButton) view;
        actOn(box , 5);
    }

    public void bottomLeftActOn (View view) {
        ImageButton box = (ImageButton) view;
        actOn(box , 6);
    }

    public void bottomCenterActOn (View view) {
        ImageButton box = (ImageButton) view;
        actOn(box , 7);
    }

    public void bottomRightActOn (View view) {
        ImageButton box = (ImageButton) view;
        actOn(box , 8);
    }

    public void actOn(ImageButton box , int tappedId) {

        // 0 to 8

        if (gameIsActive && gameState[tappedId] == player.UNPLAYED) {

            gameState[tappedId] = activePlayer;
            // move image to the top of the screen !:? why not alpha to 0.
            box.animate().alpha(0f).setDuration(300);

            if (activePlayer == player.YELLOW) {
                box.setImageResource(R.drawable.yellow);
                activePlayer = player.RED;

            } else {
                box.setImageResource(R.drawable.red);
                activePlayer = player.YELLOW;
            }

            box.animate().alpha(1f).setDuration(300);

            for (int[] winningPosition : winningPositions) {
                player val1 = gameState[winningPosition[0]];
                player val2 = gameState[winningPosition[1]];
                player val3 = gameState[winningPosition[2]];


                if ( val1 == val2 &&
                        val2 == val3 &&
                        val1 != player.UNPLAYED) {

                    // Someone has won!
                    // gameIsActive = false;
                    changeStartStop();

                    String winner = "Red";
                    if (val1 == player.YELLOW) {
                        winner = "Yellow";
                        yellowScore += 1;
                    }
                    else {
                        redScore += 1;
                    }


                    TextView scoreCard = (TextView) findViewById(R.id.scoreCard);
                    int scoreDifference = yellowScore - redScore;

                    scoreCard.setText(Integer.toString(scoreDifference));

                    String resultMsg = "The Winner is : " + winner;
                    Toast.makeText(this, resultMsg,Toast.LENGTH_LONG).show();
                } else {

                    boolean gameIsOver = true;

                    for (player counterState : gameState) {
                        if (counterState == player.UNPLAYED) gameIsOver = false;
                    }

                    if (gameIsOver) {

                        TextView scoreCard = (TextView) findViewById(R.id.scoreCard);
                        int scoreDifference = yellowScore - redScore;

                        scoreCard.setText(Integer.toString(scoreDifference));
                        String resultMsg = "The Result is : Draw";
                        Toast.makeText(this, resultMsg,Toast.LENGTH_LONG).show();
                    }

                }

            }
        }


    }


    public void restart(View view) {

        gameIsActive = true;

        ImageView  bl = (ImageView)findViewById(R.id.bottomLeft);
        ImageView  bc = (ImageView)findViewById(R.id.bottomCenter);
        ImageView  br = (ImageView)findViewById(R.id.bottomRight);
        ImageView  cl = (ImageView)findViewById(R.id.centerLeft);
        ImageView  c  = (ImageView)findViewById(R.id.center);
        ImageView  cr = (ImageView)findViewById(R.id.centerRight);
        ImageView  tl = (ImageView)findViewById(R.id.topLeft);
        ImageView  tc = (ImageView)findViewById(R.id.topCenter);
        ImageView  tr = (ImageView)findViewById(R.id.topRight);

        bl.animate().alpha(0f).setDuration(350);
        bc.animate().alpha(0f).setDuration(350);
        br.animate().alpha(0f).setDuration(350);
        cl.animate().alpha(0f).setDuration(325);
        c.animate().alpha(0f).setDuration(325);
        cr.animate().alpha(0f).setDuration(325);
        tl.animate().alpha(0f).setDuration(300);
        tc.animate().alpha(0f).setDuration(300);
        tr.animate().alpha(0f).setDuration(300);

        bl.setImageResource(R.drawable.blank);
        bc.setImageResource(R.drawable.blank);
        br.setImageResource(R.drawable.blank);

        cl.setImageResource(R.drawable.blank);
        c.setImageResource(R.drawable.blank);
        cr.setImageResource(R.drawable.blank);

        tl.setImageResource(R.drawable.blank);
        tc.setImageResource(R.drawable.blank);
        tr.setImageResource(R.drawable.blank);

        activePlayer = player.YELLOW;

        for (int i = 0; i < gameState.length; ++i) {

            gameState[i] = player.UNPLAYED;
        }

        bl.animate().alpha(1f).setDuration(350);
        bc.animate().alpha(1f).setDuration(350);
        br.animate().alpha(1f).setDuration(350);
        cl.animate().alpha(1f).setDuration(325);
        c.animate().alpha(1f).setDuration(325);
        cr.animate().alpha(1f).setDuration(325);
        tl.animate().alpha(1f).setDuration(300);
        tc.animate().alpha(1f).setDuration(300);
        tr.animate().alpha(1f).setDuration(300);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe_game);
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    */
}
