package com.example.thomas.innlevering1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DateFormat;
import java.util.Date;

/**
 * Created by thomas on 09.02.2016.
 */
public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    private ResultsFromTicTacToe result = new ResultsFromTicTacToe();
    private boolean playerTurn = true;
    private boolean winner = false;
    private int playersCount = 0;
    private int playerOneScore = 0;
    private int playerTwoScore = 0;
    private Button btn1, btn2 , btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    private Button [] btnArray;
    private Button btnPlayAgain, btnHistory;
    private String playerOneNameField, playerTwoNameField;
    private TextView playerOneScoreBoard, playerTwoScoreBoard;
    private Intent intentHistory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        intentHistory = new Intent(this,ThirdActivity.class);

        initTicTacToeButtons();
        initPlayerNameFields();

        playerOneScoreBoard = (TextView)findViewById(R.id.playerOneScore);
        playerTwoScoreBoard = (TextView)findViewById(R.id.playerTwoScore);

        btnPlayAgain = (Button)findViewById(R.id.btnPlayAgain);
        btnPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newGame();
            }
        });

        btnHistory = (Button)findViewById(R.id.btnHistory);
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentHistory);
            }
        });
    }

    private void initPlayerNameFields() {
        TextView playerOne = (TextView)findViewById(R.id.playerOneName);
        TextView playerTwo = (TextView)findViewById(R.id.playerTwoName);
        playerOneNameField = getIntent().getExtras().getString("playerOne");
        playerTwoNameField = getIntent().getExtras().getString("playerTwo");
        String playerX = playerOneNameField + "  " + getString(R.string.X);
        String playerO = playerTwoNameField + "  " + getString(R.string.O);
        playerOne.setText(playerX);
        playerTwo.setText(playerO);
    }

    private void initTicTacToeButtons() {
        btn1 = (Button)findViewById(R.id.btnOne);
        btn2 = (Button)findViewById(R.id.btnTwo);
        btn3 = (Button)findViewById(R.id.btnThree);
        btn4 = (Button)findViewById(R.id.btnFour);
        btn5 = (Button)findViewById(R.id.btnFive);
        btn6 = (Button)findViewById(R.id.btnSix);
        btn7 = (Button)findViewById(R.id.btnSeven);
        btn8 = (Button)findViewById(R.id.btnEight);
        btn9 = (Button)findViewById(R.id.btnNine);
        btnArray = new Button []{btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9};

        for(Button btn : btnArray) {
            btn.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        Button b = (Button)v;
        buttonCliked(b);

    }

    public void buttonCliked(Button btn) {
        if (playerTurn) {
            btn.setText("X");
            btn.setClickable(false);
            playerTurn = false;
        } else {
            btn.setText("O");
            btn.setClickable(false);
            playerTurn = true;
        }
        playersCount++;
        hasPlayerThreeInARow();
        gameStatus();
    }

    public void hasPlayerThreeInARow(){
        if(horizontal()){
            winner = true;
        }else if(vertical()){
            winner = true;
        }else if(aCross()){
            winner = true;
        }
    }

    public boolean horizontal(){
        if(btn1.getText() == btn2.getText() && btn2.getText() == btn3.getText() && !btn1.isClickable()){
            return true;
        }else if(btn4.getText() == btn5.getText() && btn5.getText() == btn6.getText() && !btn4.isClickable()){
            return true;
        }else if(btn7.getText() == btn8.getText() && btn8.getText() == btn9.getText() && !btn7.isClickable()){
            return true;
        }
        return false;
    }

    public boolean vertical(){
        if(btn1.getText() == btn4.getText() && btn4.getText() == btn7.getText() && !btn1.isClickable()){
            return true;
        }else if(btn2.getText() == btn5.getText() && btn5.getText() == btn8.getText() && !btn5.isClickable()){
            return true;
        }else if(btn3.getText() == btn6.getText() && btn6.getText() == btn9.getText() && !btn9.isClickable()){
            return true;
        }
        return false;
    }

    public boolean aCross(){
        if(btn1.getText() == btn5.getText() && btn5.getText() == btn9.getText() && !btn1.isClickable()){
            return true;
        }else if (btn7.getText() == btn5.getText() && btn5.getText() == btn3.getText() && !btn5.isClickable()){
            return true;
        }
        return false;
    }

    public void gameStatus() {
        if (winner) {
           showButtons();
            if (!playerTurn) {
               playerOneWins();
            } else {
                playerTwoWins();
            }
        }else if(playersCount == 9){
            gameIsDraw();
        }
    }

    public void playerOneWins(){
        Toast.makeText(SecondActivity.this, playerOneNameField + "  " + getString(R.string.winner), Toast.LENGTH_LONG).show();
        disableButtonsWhenGameIsOver();
        updatePlayerOneScore();
        updateResultHistory(playerOneNameField);
        playerTurn = false;
    }

    public void playerTwoWins(){
        Toast.makeText(SecondActivity.this, playerTwoNameField + "  " + getString(R.string.winner), Toast.LENGTH_LONG).show();
        disableButtonsWhenGameIsOver();
        updatePLayerTwoScore();
        updateResultHistory(playerTwoNameField);
        playerTurn = true;
    }

    public void gameIsDraw(){
        Toast.makeText(SecondActivity.this, getString(R.string.drawGame), Toast.LENGTH_LONG).show();
        updateResultHistory(getString(R.string.drawGame));
        showButtons();
    }
    public void disableButtonsWhenGameIsOver(){
        for(Button x : btnArray){
            if(x.isClickable()){
                x.setClickable(false);
            }
        }
    }

    public void newGame(){
        playersCount = 0;
        winner = false;
        hideButtons();
        for(Button x: btnArray){
            x.setClickable(true);
            x.setText("");
        }
    }

    public void showButtons(){
        btnPlayAgain.setVisibility(View.VISIBLE);
        btnHistory.setVisibility(View.VISIBLE);
    }

    public void hideButtons(){
        btnPlayAgain.setVisibility(View.INVISIBLE);
        btnHistory.setVisibility(View.INVISIBLE);
    }

    public void updateResultHistory(String status){
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        String historyInfo = status + "," + currentDateTimeString.toString();
        result.updateList(historyInfo);

    }

    public void updatePlayerOneScore(){
        playerOneScore += 1;
        String score = Integer.toString(playerOneScore);
        playerOneScoreBoard.setText(score);
    }

    public void updatePLayerTwoScore(){
        playerTwoScore += 1;
        String score = Integer.toString(playerTwoScore);
        playerTwoScoreBoard.setText(score);
    }
}


