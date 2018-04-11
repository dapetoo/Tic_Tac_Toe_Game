package com.dapetoo.ttt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MediumActivity extends AppCompatActivity {

    // Represents the internal state of the game
    private RandomUtilMedium mGame;

    // Buttons making up the board
    private Button mBoardButtons[];
    private Button mNewGame;

    // Various text displayed
    private TextView mInfoTextView;
    private TextView mPlayerOneCount;
    private TextView mTieCount;
    private TextView mPlayerTwoCount;
    private TextView mPlayerOneText;
    private TextView mPlayerTwoText;

    // Counters for the wins and draws
    private int mPlayerOneCounter = 0;
    private int mTieCounter = 0;
    private int mPlayerTwoCounter = 0;

    // Bools needed to see if player one goes first
    // if the gameType is to be single or local multiplayer
    // if it is player one's turn
    // and if the game is over
    private boolean mPlayerOneFirst = true;
    private boolean mIsSinglePlayer = false;
    private boolean mIsPlayerOneTurn = true;
    private boolean mGameOver = false;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medium);

        boolean mGameType = getIntent().getExtras().getBoolean("gameType");

        //This will make the game to exit the game to the main screen
        ((Button) findViewById(R.id.exitGame)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediumActivity.this, MainActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        // Initialize the buttons
        mBoardButtons = new Button[mGame.getBOARD_SIZE()];
        mBoardButtons[0] =  findViewById(R.id.button1);
        mBoardButtons[1] =  findViewById(R.id.button2);
        mBoardButtons[2] =  findViewById(R.id.button3);
        mBoardButtons[3] =  findViewById(R.id.button4);
        mBoardButtons[4] =  findViewById(R.id.button5);
        mBoardButtons[5] =  findViewById(R.id.button6);
        mBoardButtons[6] =  findViewById(R.id.button7);
        mBoardButtons[7] =  findViewById(R.id.button8);
        mBoardButtons[8] =  findViewById(R.id.button9);
        mBoardButtons[9] =  findViewById(R.id.button10);
        mBoardButtons[10] =  findViewById(R.id.button11);
        mBoardButtons[11] =  findViewById(R.id.button12);
        mBoardButtons[12] =  findViewById(R.id.button13);
        mBoardButtons[13] =  findViewById(R.id.button14);
        mBoardButtons[14] =  findViewById(R.id.button15);
        mBoardButtons[15] =  findViewById(R.id.button16);
        addListenerOnButton();

        // setup the textviews
        mInfoTextView =  findViewById(R.id.information);
        mPlayerOneCount =  findViewById(R.id.playerCount);
        mTieCount =  findViewById(R.id.drawCount);
        mPlayerTwoCount =  findViewById(R.id.computerCount);
        mPlayerOneText =  findViewById(R.id.human);
        mPlayerTwoText =  findViewById(R.id.computer);

        // set the initial counter display values
        mPlayerOneCount.setText(Integer.toString(mPlayerOneCounter));
        mTieCount.setText(Integer.toString(mTieCounter));
        mPlayerTwoCount.setText(Integer.toString(mPlayerTwoCounter));

        // create a new game object
        mGame = new RandomUtilMedium();

        // start a new game
        startNewGame(mGameType);

    }


    public void addListenerOnButton(){

        mNewGame =  findViewById(R.id.NewGame);

        mNewGame.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                startNewGame(mIsSinglePlayer);
            }
        });
    }

    // start a new game
    // clears the board and resets all buttons / text
    // sets game over to be false
    private void startNewGame(boolean isSingle)
    {

        this.mIsSinglePlayer = isSingle;

        mGame.clearBoard();

        for (int i = 0; i < mBoardButtons.length; i++)
        {
            mBoardButtons[i].setText("");
            mBoardButtons[i].setEnabled(true);
            mBoardButtons[i].setOnClickListener(new ButtonClickListener(i));
        }


        if (mIsSinglePlayer)
        {
            mPlayerOneText.setText("Human:");
            mPlayerTwoText.setText("Computer:");

            if (mPlayerOneFirst)
            {
                mInfoTextView.setText(R.string.first_human);
                mPlayerOneFirst = false;
            }
            else
            {
                mInfoTextView.setText(R.string.turn_computer);
                int move = mGame.getComputerMove();
                setMove(mGame.PLAYER_TWO, move);
                mPlayerOneFirst = true;
            }
        }
        else
        {
            mPlayerOneText.setText("Player One:");
            mPlayerTwoText.setText("Player Two:");

            if (mPlayerOneFirst)
            {
                mInfoTextView.setText(R.string.turn_player_one);
                mPlayerOneFirst = false;
            }
            else
            {
                mInfoTextView.setText(R.string.turn_player_two);
                mPlayerOneFirst = true;
            }
        }

        mGameOver = false;
    }

    private class ButtonClickListener implements View.OnClickListener
    {
        int location;

        private ButtonClickListener(int location)
        {
            this.location = location;
        }

        public void onClick(View view)
        {
            if (!mGameOver)
            {
                if (mBoardButtons[location].isEnabled())
                {
                    if (mIsSinglePlayer)
                    {
                        setMove(mGame.PLAYER_ONE, location);

                        int winner = mGame.checkForWinner();

                        if (winner == 0)
                        {
                            mInfoTextView.setText(R.string.turn_computer);
                            int move = mGame.getComputerMove();
                            setMove(mGame.PLAYER_TWO, move);
                            winner = mGame.checkForWinner();
                        }

                        if (winner == 0)
                            mInfoTextView.setText(R.string.turn_human);
                        else if (winner == 1)
                        {
                            mInfoTextView.setText(R.string.result_tie);
                            mTieCounter++;
                            mTieCount.setText(Integer.toString(mTieCounter));
                            mGameOver = true;
                        }
                        else if (winner == 2)
                        {
                            mInfoTextView.setText(R.string.result_human_wins);
                            mPlayerOneCounter++;
                            mPlayerOneCount.setText(Integer.toString(mPlayerOneCounter));
                            mGameOver = true;
                        }
                        else
                        {
                            mInfoTextView.setText(R.string.result_android_wins);
                            mPlayerTwoCounter++;
                            mPlayerTwoCount.setText(Integer.toString(mPlayerTwoCounter));
                            mGameOver = true;
                        }
                    }
                    else
                    {
                        if (mIsPlayerOneTurn)
                            setMove(mGame.PLAYER_ONE, location);
                        else
                            setMove(mGame.PLAYER_TWO, location);

                        int winner = mGame.checkForWinner();

                        if (winner == 0)
                        {
                            if (mIsPlayerOneTurn)
                            {
                                mInfoTextView.setText(R.string.turn_player_two);
                                mIsPlayerOneTurn = false;
                            }
                            else
                            {
                                mInfoTextView.setText(R.string.turn_player_one);
                                mIsPlayerOneTurn = true;
                            }
                        }
                        else if (winner == 1)
                        {
                            mInfoTextView.setText(R.string.result_tie);
                            mTieCounter++;
                            mTieCount.setText(Integer.toString(mTieCounter));
                            mGameOver = true;
                        }
                        else if (winner == 2)
                        {
                            mInfoTextView.setText(R.string.result_player_one_wins);
                            mPlayerOneCounter++;
                            mPlayerOneCount.setText(Integer.toString(mPlayerOneCounter));
                            mGameOver = true;
                            mIsPlayerOneTurn = false;
                        }
                        else
                        {
                            mInfoTextView.setText(R.string.result_player_two_wins);
                            mPlayerTwoCounter++;
                            mPlayerTwoCount.setText(Integer.toString(mPlayerTwoCounter));
                            mGameOver = true;
                            mIsPlayerOneTurn = true;
                        }
                    }
                }
            }
        }
    }

    // set move for the current player
    private void setMove(char player, int location)
    {
        mGame.setMove(player, location);
        mBoardButtons[location].setEnabled(false);
        if (player == mGame.PLAYER_ONE)
            mBoardButtons[location].setText(getText(R.string.X));
        else
            mBoardButtons[location].setText(getText(R.string.Y));
    }

}
