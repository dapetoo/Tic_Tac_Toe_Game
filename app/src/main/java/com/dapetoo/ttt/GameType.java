package com.dapetoo.ttt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameType extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_type);

        String level  = getIntent().getExtras().getString("level");


        if(level.equals("easy")){

            ((Button) findViewById(R.id.onePlayer)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View V) {
                    Intent intent = new Intent(GameType.this, EasyActivity.class);
                    intent.putExtra("gameType", true);
                    startActivityForResult(intent, 0);
                }
            });

            ((Button) findViewById(R.id.twoPlayer)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View V) {
                    Intent intent = new Intent(GameType.this, EasyActivity.class);
                    intent.putExtra("gameType", false);
                    startActivityForResult(intent, 0);
                }
            });

        } else if(level.equals("medium")){

            ((Button) findViewById(R.id.onePlayer)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View V) {
                    Intent intent = new Intent(GameType.this, MediumActivity.class);
                    intent.putExtra("gameType", true);
                    startActivityForResult(intent, 0);
                }
            });

            ((Button) findViewById(R.id.twoPlayer)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View V) {
                    Intent intent = new Intent(GameType.this, MediumActivity.class);
                    intent.putExtra("gameType", false);
                    startActivityForResult(intent, 0);
                }
            });

        } else{

            ((Button) findViewById(R.id.onePlayer)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View V) {
                    Intent intent = new Intent(GameType.this, HardLevel.class);
                    intent.putExtra("gameType", true);
                    startActivityForResult(intent, 0);
                }
            });

            ((Button) findViewById(R.id.twoPlayer)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View V) {
                    Intent intent = new Intent(GameType.this, HardLevel.class);
                    intent.putExtra("gameType", false);
                    startActivityForResult(intent, 0);
                }
            });
        }

    }
}