package com.example.zerokaata;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    boolean gameactive=true;
    //    player repressentation
//     0-X
//    1-0
    int activeplayer=0;
    int[] gamestate={2,2,2,2,2,2,2,2,2};
    //    state meaning
//    0-X
//    1-0
//    2-null
    int [][] winpositions={{0,1,2},{3,4,5} ,{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};


    public void playertap(View view){
        ImageView img=(ImageView) view;
        int tappedimage= Integer.parseInt(img.getTag().toString());
        if(!gameactive){
            gamereset(view);
        }
        if(gamestate[tappedimage]==2 && gameactive){
            gamestate[tappedimage]=activeplayer;
            img.setTranslationY(-1000f);
            if (activeplayer==0) {
                img.setImageResource(R.drawable.xmodified);
                activeplayer = 1;
                TextView Status=findViewById(R.id.Status);
                Status.setText("O's Turn");
            }
            else{
                img.setImageResource(R.drawable.omodified);

                activeplayer=0;
                TextView Status=findViewById(R.id.Status);
                Status.setText("X's Turn");
            }

            img.animate().translationYBy(1000f).setDuration(30);
        }
//        check if sm1 won

        for (int[] winning:winpositions){
            if (gamestate[winning[0]]==gamestate[winning[1]] && gamestate[winning[1]]==gamestate[winning[2]] && gamestate[winning[0]]!=2){
//               some body has won -find x or 0
                String winnerStr;
                gameactive=false;
                if(gamestate[winning[0]]==0){
                    winnerStr="X has WON";



                }
                else{
                    winnerStr="O has WON";

                }

//               update the status bar for announcement
                TextView Status=findViewById(R.id.Status);
                Status.setText(winnerStr);




            }
            boolean emptySquare = false;
            for (int squareState : gamestate) {
                if (squareState == 2) {
                    emptySquare = true;
                    break;
                }
            }
            if (!emptySquare && gameactive) {
                // Game is a draw
                gameactive = false;
                String winnerStr;
                winnerStr = "No one won";
                TextView status = findViewById(R.id.Status);
                status.setText(winnerStr);

            }


        }

    }



    public void gamereset(View view){
        gameactive=true;
        activeplayer=0;
        for (int i =0;i<gamestate.length;i++){
            gamestate[i]=2;
        }
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView10)).setImageResource(0);

        TextView Status=findViewById(R.id.Status);
        Status.setText("X's Turn- Tap to play");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}