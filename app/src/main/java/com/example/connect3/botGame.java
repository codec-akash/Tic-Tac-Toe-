package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.Random;

public class botGame extends AppCompatActivity {
//active player ::- 5 - red, 3 - yellow, 2 - empty;


    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int activePlayer = 5;
    int num =0;
    boolean moved = false;
    int tappedCounter;
    Boolean firstMove = true;
    boolean gameActive = true;
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,4,8},{2,4,6},{0,3,6},{1,4,7},{2,5,8}};
    int[] pos = {1,3,5,7};

    public void dropIn(View view){

        ImageView counter = (ImageView) view;
        tappedCounter = Integer.parseInt(counter.getTag().toString());
        counter.setTranslationY(-1500);
        if(gameState[tappedCounter] == 2 && gameActive){

            gameState[tappedCounter] = activePlayer;
            counter.setImageResource(R.drawable.red);
            Log.i("details","gamestate"+gameState[tappedCounter] + " " +tappedCounter + " "+ moved);

            activePlayer = 3;
            moved = false;
            botPlay();

        }
        counter.animate().translationYBy(1500);

    }

    public void botPlay() {

        /*if (gameState[4] == 2 && firstMove) {

            ((ImageView) gridLayout.getChildAt(4)).setImageResource(R.drawable.yellow);
            Toast.makeText(this, "runned !!", Toast.LENGTH_SHORT).show();
            gameState[4] = 3;
            firstMove = false;

        }*/
            cornerPlay();

    }

    public void cornerPlay() {

        if (gameState[4] == 2 && firstMove) {
            yellow(4);
            Toast.makeText(this, "runned !!", Toast.LENGTH_SHORT).show();
            gameState[4] = 3;
            firstMove = false;
            activePlayer =5;
        }
        else {
            Toast.makeText(this, "runned !!", Toast.LENGTH_SHORT).show();
            outerloop:
            for (int[] wp : winningPositions) {

                int sum = (gameState[wp[0]] + gameState[wp[1]] + gameState[wp[2]]);

                if (sum == 8) {
                    Log.i("Values", "gs[wp[0 =" + wp[0] + "gs[wp[1 = " + wp[1] + " gs[wp[2 =" + wp[2]);
                    Log.i("Values", "gs[wp[0 =" + gameState[wp[0]] + "gs[wp[1 = " + gameState[wp[1]] + " gs[wp[2 =" + gameState[wp[2]]);

                    for (int i = 0; i < 3; i++) {

                        if (gameState[wp[i]] == 2) {
                            Log.i("Value", "Value of wp = " + wp[i] + " gs[wp[i] = " + gameState[wp[i]]);
                            yellow(wp[i]);
                            gameActive = false;
                            moved = true;
                            break outerloop;
                        }

                    }

                }
            }

            if ( (activePlayer == 3 ) && (moved == false)) {
                outerloop:
                for (int[] wp : winningPositions) {

                    int sum = (gameState[wp[0]] + gameState[wp[1]] + gameState[wp[2]]);
                    Log.i("before loop ", " gs[wp[0 " + gameState[wp[0]] + wp[0] + " gs[wp[1 " + gameState[wp[0]] + wp[1] + " gs[wp[2 " + gameState[wp[2]] + wp[2]);

                    if (sum == 12) {
                        Log.i("Values", "gs[wp[0 =" + wp[0] + "gs[wp[1 = " + wp[1] + " gs[wp[2 =" + wp[2]);
                        Log.i("Values", "gs[wp[0 =" + gameState[wp[0]] + "gs[wp[1 = " + gameState[wp[1]] + " gs[wp[2 =" + gameState[wp[2]]);

                        for (int i = 0; i < 3; i++) {

                            if (gameState[wp[i]] == 2) {
                                Log.i("Value", "Value of wp = " + wp[i] + " gs[wp[i] = " + gameState[wp[i]]);
                                yellow(wp[i]);
                                moved = true;
                                break outerloop;
                            }

                        }

                    }

                }

            }
            if(moved == false){

                randomnumber();
                moved = true;
                yellow(num);


            }

        }

    }


    public void yellow(int i){
        androidx.gridlayout.widget.GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        ((ImageView) gridLayout.getChildAt(i)).setImageResource(R.drawable.yellow);
        gameState[i]= activePlayer;
        activePlayer = 5;
        moved = false;
        Log.i("details","gamestate"+gameState[i] + " " +i);
    }

    public void randomnumber(){

        Random random = new Random();
        int move = random.nextInt(pos.length);
        if(gameState[pos[move]] == 2) {
            Log.i("check", "Initialised" + move);
            num = pos[move];
        }
        else{
            randomnumber();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bot_game);

    }
}
