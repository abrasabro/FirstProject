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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        score = 0;
        scoreView = ((TextView) findViewById(R.id.scoreText));
        scoreView.setText("score: " + score);
        rando = new Random();
        display = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(display);
        tokenView = new ArrayList<ImageButton>();
        tokenView.add((ImageButton)findViewById(R.id.token));
        tokenAdd = new int[]{10, 20, 25, 30, 33, 36, 38, 40};
        layout = (ConstraintLayout) findViewById(R.id.mainLayout);
        DoTokenTap = new View.OnClickListener()
        {
            public void onClick(View view) {
                TokenTap(view);
            }
        };
    }

    private int score; //score
    private Random rando; //random number generator
    private DisplayMetrics display; //display metric
    private ArrayList<ImageButton> tokenView; //handle for the token view
    private TextView scoreView; //handle for the score text view
    private int tokenAdd[]; //scores for which to add a new token
    private ConstraintLayout layout; //handler for the parent layout
    private View.OnClickListener DoTokenTap;

    public void TokenTap(View view) {
        score++;
        scoreView.setText("score: " + score);
        if(Arrays.asList(tokenAdd).contains(score) || score >= 42)
        {
            tokenView.add( new ImageButton(this));
            tokenView.get(tokenView.size()-1).setLayoutParams(tokenView.get(0).getLayoutParams());
            layout.addView(tokenView.get(tokenView.size()-1), tokenView.get(0).getLayoutParams());
            tokenView.get(tokenView.size()-1).setImageResource(R.drawable.token);
            tokenView.get(tokenView.size()-1).setOnClickListener(DoTokenTap);
        }
        //getContext().getResources().getDisplayMetrics();
        for(View token : tokenView)
        {
            token.setX(rando.nextInt(display.widthPixels - token.getWidth()) + 1);
            //token.setX((display.widthPixels - token.getWidth()) + 1);
            token.setY(rando.nextInt(display.heightPixels - token.getHeight()-200) + 1);
            //token.setY((display.heightPixels - token.getHeight()) + 1-200);
        }
    }
    //private View.OnClickListener mCorkyListener = new View.OnClickListener() {


}

