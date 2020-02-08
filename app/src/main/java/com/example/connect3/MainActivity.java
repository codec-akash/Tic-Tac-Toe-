package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,4,8},{2,4,6},{0,3,6},{1,4,7},{2,5,8}};
    boolean gameActive = true;

    public void drop(View view){

        ImageView counter = (ImageView) view;
        TextView textView = (TextView) findViewById(R.id.textView);
        Button btn = (Button) findViewById(R.id.button);
        String msg;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter] == 2 && gameActive) {

            gameState[tappedCounter] = activePlayer;
            btn.setVisibility(View.INVISIBLE);
            counter.setTranslationY(-1500);

            Log.i("Tag", "counter" + counter.getTag().toString() + " " + gameState[tappedCounter]);

            if (activePlayer == 0) {
                activePlayer = 1;
                counter.setImageResource(R.drawable.yellow);
                msg = "Red chance";
            } else {
                activePlayer = 0;
                counter.setImageResource(R.drawable.red);
                msg = "Yellows chance";
            }
            textView.setText(msg);
            counter.animate().translationYBy(1500).setDuration(1000);

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                        && gameState[winningPosition[0]] != 2) {
                    gameActive = false;
                    Log.i("tag", "Someone wins !!");
                    if (activePlayer == 1) {
                        activePlayer = 0;
                        msg = "Yellow wins";
                    } else {
                        activePlayer = 1;
                        msg = "Red Wins !";
                    }
                    btn.setVisibility(View.VISIBLE);
                    textView.setText(msg);
                }
            }
        }
    }

    public void play(View view){
        Toast.makeText(this, "Play Again!!", Toast.LENGTH_SHORT).show();
        androidx.gridlayout.widget.GridLayout gridLayout = findViewById(R.id.gridLayout);
        Button btn = (Button) findViewById(R.id.button);
        btn.setVisibility(View.INVISIBLE);
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("Yellow Chance");
        //Toast.makeText(this, "number = "+gridLayout.getChildCount(), Toast.LENGTH_SHORT).show();
       for (int i =0;i<gameState.length;i++){
            gameState[i]=2;
        }

       for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }

        activePlayer = 0;
        gameActive = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
