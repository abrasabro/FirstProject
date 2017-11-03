package com.example.firstproject;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
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
    private View.OnClickListener mDoTokenTap;

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
        mTokenAdd = Arrays.asList(10, 20, 25, 30, 33, 36, 38, 40);
        mLayout = (ConstraintLayout) findViewById(R.id.mainLayout);
        mDoTokenTap = new View.OnClickListener()
        {
            public void onClick(View view) {
                tokenTap(view);
            }
        };
    }



    public void tokenTap(View view) {
        mScore++;
        mScoreView.setText("score: " + mScore);
        if(mTokenAdd.contains(mScore) || mScore >= 42)
        {
            mTokenView.add( new ImageButton(this));
            mTokenView.get(mTokenView.size()-1).setLayoutParams(mTokenView.get(0).getLayoutParams());
            mLayout.addView(mTokenView.get(mTokenView.size()-1), mTokenView.get(0).getLayoutParams());
            mTokenView.get(mTokenView.size()-1).setImageResource(R.drawable.token);
            mTokenView.get(mTokenView.size()-1).setOnClickListener(mDoTokenTap);
        }
        for(View token : mTokenView)
        {
            token.setX(mRando.nextInt(mDisplay.widthPixels - token.getWidth()) + 1);
            token.setY(mRando.nextInt(mDisplay.heightPixels - token.getHeight()-200) + 1);
        }
    }


}

