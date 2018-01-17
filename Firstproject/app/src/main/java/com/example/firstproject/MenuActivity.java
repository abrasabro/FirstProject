package com.example.firstproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MenuActivity extends AppCompatActivity {

    static final String STATE_HISCORE = "hiScore";
    static final String STATE_LASTSCORE = "lastScore";
    private int mLastScore;
    private int mHiScore;
    private TextView mLastScoreView;
    private TextView mHiScoreView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Log.i("a", "oncreate");
        if(savedInstanceState == null){
            Log.i("a", "null");
            mLastScore = 0;
            mHiScore = 0;
        }
        else{
            Log.i("a", "not null");
            mHiScore = savedInstanceState.getInt(STATE_HISCORE);
            mLastScore = (int) savedInstanceState.getSerializable("lastScore");
            if(mLastScore > mHiScore)
                mHiScore = mLastScore;
        }
        mLastScoreView = findViewById(R.id.lastScore_TextView);
        mHiScoreView = findViewById(R.id.hiScore_TextView);
        mLastScoreView.setText("" + mLastScore);
        mHiScoreView.setText("" + mHiScore);
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putInt(STATE_HISCORE, mHiScore);
        super.onSaveInstanceState(savedInstanceState);
        Log.i("a", "save instance state");
    }


    public void clickPlay(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
