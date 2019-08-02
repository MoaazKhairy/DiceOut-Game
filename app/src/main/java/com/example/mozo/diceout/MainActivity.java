// Author: Moaz Khairy Hussein
// Date: 3/8/2019
// Project Name: DiceOut Game
// Project Description: Game consists of 3 dies, and roll button to generate random numbers of 3 dies in each time,
// also can use Fab to do that, then if any 2 dies have same value then score will increase by 50,
// and any 3 dies have value ones then score will increase by 100, if equals twos then will increase by 200 , and so on

package com.example.mozo.diceout;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //define field for hold TextView
    TextView rollResult;

    //define field for hold button
    Button rollButton;

    // define field for hold score
    int score;

    //define field for hold random number generator
    Random rand;

    //define field to hold dies value
    int die1;
    int die2;
    int die3;

    //define arraylist for dice to hold all values of 3 dies
    ArrayList<Integer> dice;

    //define arraylist to hold all images of 3 dies
    ArrayList<ImageView> imageViews;

    //Field to hold the score TextView
    TextView scoreText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollDice(view);
            }
        });

        //initiallize score
        score = 0;
        Toast.makeText(getApplicationContext(), "Welcome to DiceOut Game :)", Toast.LENGTH_SHORT).show();

        //link between defined rollResult above and rollResult in activity layout XML
        rollResult = findViewById(R.id.rollResult);

        //link between defined rollButton above and rollButton in activity layout XML
        rollButton = findViewById(R.id.rollButton);

        //initialize random number generator
        rand = new Random();

        //initialize arraylist
        dice = new ArrayList<Integer>();

        //assign actual images to defined imageViews above
        ImageView die1Image = findViewById(R.id.die1Image);
        ImageView die2Image = findViewById(R.id.die2Image);
        ImageView die3Image = findViewById(R.id.die3Image);

        //initialize arraylist of images
        imageViews = new ArrayList<ImageView>();

        //add dieImages into imageViews arrayList
        imageViews.add(die1Image);
        imageViews.add(die2Image);
        imageViews.add(die3Image);

        //link between defined scoreText above and scoreText in activity layout XML
        scoreText = findViewById(R.id.scoreText);

    }

    public void rollDice (View v) {
        /*
        rollResult.setText("Clicked :)");

        //put random value integer into field
        int num = rand.nextInt(6)+1;

        //put wanted phrase into string field
        String  randomValue = "Number generator" + num;

        //show phrase by Toast
        Toast.makeText(getApplicationContext(), randomValue, Toast.LENGTH_SHORT).show();
        */

        //put random value integer into 3 dies
        die1 = rand.nextInt(6)+1;
        die2 = rand.nextInt(6)+1;
        die3 = rand.nextInt(6)+1;

        rollResult.setText("You rolled a "+ die1 + ", a " + die2 + " & a " + die3);

        //set Values in dice arrayList
        dice.clear();  //to clear previous values
        dice.add(die1);
        dice.add(die2);
        dice.add(die3);

        for (int dieOfSet =0; dieOfSet < 3; dieOfSet++){
            //build string of name of die images
            String imageName = "die_" + dice.get(dieOfSet) + ".png" ;

            try {
                InputStream stream = getAssets().open(imageName);  //Access Asset image which is dynamic
                Drawable d = Drawable.createFromStream(stream, null);  //create temporary drawable img from asset image to view it in imageView
                imageViews.get(dieOfSet).setImageDrawable(d); //assign new image replace of previous image
            }
            catch (IOException e){
                e.printStackTrace();
            }

        }

        String msg;

        if (die1 == die2 && die1 == die3)
        {
            //Triples
            score += (100 * die1);
            scoreText.setText("Score : " + score);


        } else if (die1 == die2 || die1 == die3 || die2 == die3)
        {
            //douples
            score += 50;
            scoreText.setText("Score : " + score);

        } else
        {
            Toast.makeText(getApplicationContext(), "Try Agian due to not douple and not triples", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
