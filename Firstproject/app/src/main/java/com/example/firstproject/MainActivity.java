package com.example.firstproject;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Integer mScore;
    private Random mRando;
    private DisplayMetrics mDisplay;
    private ArrayList<ImageButton> mTokenView; //handle for all the tokens that will be created
    private TextView mScoreView;
    private List<Integer> mTokenAdd; //scores for which to add a new token
    private ConstraintLayout mLayout;
    private LayoutInflater mInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mScore = 0;
        mScoreView = ((TextView) findViewById(R.id.scoreText));
        mScoreView.setText("score: " + mScore);
        mRando = new Random();
        mDisplay = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(mDisplay);
        mTokenView = new ArrayList<ImageButton>();
        mTokenView.add((ImageButton)findViewById(R.id.token));
        mTokenAdd = Arrays.asList(5, 10, 15, 20, 25, 30, 33, 36, 38, 40);
        mLayout = (ConstraintLayout) findViewById(R.id.mainLayout);
        mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }



    public void tokenTap(View view) {
        mScore++;
        mScoreView.setText("score: " + mScore);
        if(mTokenAdd.contains(mScore) || mScore >= 42)
        {
            if(mRando.nextBoolean())
                mTokenView.add( (ImageButton) mInflater.inflate(R.layout.token_imagebutton, null));
            else
                mTokenView.add( (ImageButton) mInflater.inflate(R.layout.spike_imagebutton, null));
            mLayout.addView(mTokenView.get(mTokenView.size()-1), mTokenView.get(0).getLayoutParams());
        }
        for(View token : mTokenView)
        {
            token.setX(mRando.nextInt(mDisplay.widthPixels - token.getWidth()) + 1);
            token.setY(mRando.nextInt(mDisplay.heightPixels - token.getHeight()-200) + 1);
            if(token.getTag().equals("token")){
                token.setScaleX(token.getScaleX()*.95f);
                token.setScaleY(token.getScaleY()*.95f);
            }
            else if(token.getTag().equals("spike")){
                token.setScaleX(token.getScaleX()*1.05f);
                token.setScaleY(token.getScaleY()*1.05f);
            }
        }
    }

    public void spikeTap(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra("lastScore", mScore);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }


}

