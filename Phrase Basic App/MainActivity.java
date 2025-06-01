package com.anushka.phrasesbasic;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void buttonTapped(View view){

        int id = view.getId();
        String ourId = "";

        ourId = view.getResources().getResourceEntryName(id);

        int resourecId = getResources().getIdentifier(ourId, "raw", "com.anushka.phrasesbasic");

        MediaPlayer mplayer = MediaPlayer.create(this, resourecId);
        mplayer.start();
        Log.i("button tapped", Integer.toString(view.getId()));

    }
}