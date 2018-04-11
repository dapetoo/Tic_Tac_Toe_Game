package com.dapetoo.ttt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LevelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);


        //This will open the 3 by 3 board which is the Easy Level
        ((Button) findViewById(R.id.easy)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View V) {
                Intent intent = new Intent(LevelActivity.this,  GameType.class);
                intent.putExtra("level", "easy");
                startActivityForResult(intent, 0);
            }
        });


        //This will open the 4 by 4 board which is the Medium Level
        ((Button) findViewById(R.id.medium)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View V) {
                Intent intent = new Intent(LevelActivity.this, GameType.class);
                intent.putExtra("level", "medium");
                startActivityForResult(intent, 0);
            }
        });


        //This will open the 5 by 5 board which is the Hard Level
        ((Button) findViewById(R.id.hard)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View V) {
                Intent intent = new Intent(LevelActivity.this, GameType.class);
                intent.putExtra("level", "hard");
                startActivityForResult(intent, 0);
            }
        });

        ((Button) findViewById(R.id.back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LevelActivity.this, MainActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }
}
