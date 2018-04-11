package com.dapetoo.ttt;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //This will open the Game Level where the user will be able to select the level to play
        //which is either 3 by 3 board which is the Easy Level or
        //4 by 4 board which is the medium level
        //or 5 by 5 board which is the hard level
        ((Button) findViewById(R.id.playGame)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View V) {
                Intent intent = new Intent(MainActivity.this, LevelActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        //This will open the Instruction activity which will guide the user on how to play the game
        ((Button) findViewById(R.id.gameInstruction)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View V) {
                Intent intent = new Intent(MainActivity.this, GameInstruction.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    /**
     * This is a dialogue that will ask whether the User really want to Exit the app
     * when click on Exit Button
     */
    public void exitApp(View view) {
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);

        dlgAlert.setMessage("Do you really want to exit");
        dlgAlert.setTitle("Exit");

        dlgAlert.setCancelable(true);
        dlgAlert.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        System.exit(0);
                    }
                });
        dlgAlert.create().show();
    }
}
