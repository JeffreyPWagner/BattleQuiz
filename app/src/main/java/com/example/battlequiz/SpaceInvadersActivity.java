// NOTE: All space invaders content was copied from http://gamecodeschool.com/android/coding-a-space-invaders-game/ with the exception of integration tweaks

package com.example.battlequiz;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Display;

import org.parceler.Parcels;

// SpaceInvadersActivity is the entry point to the game.
// It will handle the lifecycle of the game by calling
// methods of spaceInvadersView when prompted to so by the OS.
public class SpaceInvadersActivity extends Activity {

    // spaceInvadersView will be the view of the game
    // It will also hold the logic of the game
    // and respond to screen touches as well
    SpaceInvadersView spaceInvadersView;

    Quiz quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get a Display object to access screen details
        Display display = getWindowManager().getDefaultDisplay();
        // Load the resolution into a Point object
        Point size = new Point();
        display.getSize(size);

        Intent startIntent = getIntent();
        int gameLives = startIntent.getIntExtra("gameLives",1);
        if(gameLives == 0){
            gameLives = 1;
        }

        Parcelable quizPar = startIntent.getParcelableExtra("quiz");
        quiz = Parcels.unwrap(quizPar);

        // Initialize gameView and set it as the view
        spaceInvadersView = new SpaceInvadersView(this, size.x, size.y, gameLives, quiz);
        setContentView(spaceInvadersView);

    }

    // This method executes when the player starts the game
    @Override
    protected void onResume() {
        super.onResume();

        // Tell the gameView resume method to execute
        spaceInvadersView.resume();
    }

    // This method executes when the player quits the game
    @Override
    protected void onPause() {
        super.onPause();

        // Tell the gameView pause method to execute
        spaceInvadersView.pause();
    }
}
