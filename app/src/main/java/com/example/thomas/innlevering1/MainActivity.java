package com.example.thomas.innlevering1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        final EditText playerOne = (EditText)findViewById(R.id.player1);
        final EditText playerTwo = (EditText) findViewById(R.id.player2);
        final Button play = (Button)findViewById(R.id.launchGame);

        play.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(context, SecondActivity.class);
                String inputPlayerOne = playerOne.getText().toString();
                String inputPlayerTwo = playerTwo.getText().toString();
                intent.putExtra("playerOne",inputPlayerOne);
                intent.putExtra("playerTwo",inputPlayerTwo);
                startActivity(intent);
            }
        });

    }
}
