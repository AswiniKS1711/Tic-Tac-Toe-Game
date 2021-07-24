package com.example.tictactoebyaswini;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8;
    TextView headerText;

    int player_O=0;
    int player_X=1;

    int []filledPos={-1,-1,-1,-1,-1,-1,-1,-1,-1};

    int activePlayer=player_O;

    boolean isActive=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        headerText=findViewById(R.id.header_text);
        headerText.setText("O's Turn");


        btn0=findViewById(R.id.btn0);
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);
        btn4=findViewById(R.id.btn4);
        btn5=findViewById(R.id.btn5);
        btn6=findViewById(R.id.btn6);
        btn7=findViewById(R.id.btn7);
        btn8=findViewById(R.id.btn8);

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        if(!isActive)
            return;

        Button clickBtn=findViewById(v.getId());
        int clickTag=Integer.parseInt(v.getTag().toString());

        if(filledPos[clickTag]!=-1)
        {
            return;
        }

        filledPos[clickTag]=activePlayer;

        if(activePlayer==player_O)
        {
            clickBtn.setText("0");
            clickBtn.setBackground(getDrawable(android.R.color.holo_blue_bright));
            activePlayer=player_X;
            headerText.setText("X's Turn");
        }
        else
        {
            clickBtn.setText("X");
            clickBtn.setBackground(getDrawable(android.R.color.holo_orange_light));
             activePlayer = player_O;
            headerText.setText("O's Turn");
        }

        checkForWin();

    }

    private void checkForWin()
    {
        int [][]winningPos={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

        for(int i=0;i<8;i++)
        {
            int value0=winningPos[i][0];
            int value1=winningPos[i][1];
            int value2=winningPos[i][2];

            if(filledPos[value0]==filledPos[value1] && filledPos[value1]==filledPos[value2])
            {
                if(filledPos[value0] != -1)
                {
                    isActive=false;
                    if(filledPos[value0]==player_O)
                       showDialog("O is Winner !");
                    else if(filledPos[value0]==player_X)
                        showDialog("X is winner !");
                    else
                        showDialog("Its a tie !");
                }
            }
        }
    }

    private void showDialog(String winnerText)
    {
        new AlertDialog.Builder(this)
                .setTitle(winnerText)
                .setPositiveButton("Restart Game", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        restartGame();
                    }
                })
                .show();

    }

    private void restartGame()
    {
        activePlayer=player_O;
        filledPos=new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1};

        headerText.setText("O's Turn");
        btn0.setText("");
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");

        btn0.setBackground(getDrawable(android.R.color.holo_purple));
        btn1.setBackground(getDrawable(android.R.color.holo_purple));
        btn2.setBackground(getDrawable(android.R.color.holo_purple));
        btn3.setBackground(getDrawable(android.R.color.holo_purple));
        btn4.setBackground(getDrawable(android.R.color.holo_purple));
        btn5.setBackground(getDrawable(android.R.color.holo_purple));
        btn6.setBackground(getDrawable(android.R.color.holo_purple));
        btn7.setBackground(getDrawable(android.R.color.holo_purple));
        btn8.setBackground(getDrawable(android.R.color.holo_purple));

        isActive=true;
    }
}