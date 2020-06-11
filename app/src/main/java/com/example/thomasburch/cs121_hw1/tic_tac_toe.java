package com.example.thomasburch.cs121_hw1;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.widget.Button;
import android.media.MediaPlayer;

public class tic_tac_toe extends AppCompatActivity implements OnClickListener{
        // new game button
        Button newgame;

        // grid buttons
        ImageButton imageButton00,imageButton01, imageButton02,
                imageButton10, imageButton11, imageButton12,
                imageButton20, imageButton21, imageButton22;

        // grid buttons stored in array
        ImageButton []imageArray = null;

        // initializing media player
        MediaPlayer mediaPlayer;

        // Mute/Unmute buttons
        ImageButton volume;

        boolean turn = true; // X = true; O = false
        boolean isVolume = false; //mute = true; unmute = false
        int turn_count = 0; // turn_count = 9, tie game

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_tic_tac_toe);

            // starting media player
            mediaPlayer = MediaPlayer.create(tic_tac_toe.this, R.raw.app_music);
            mediaPlayer.start();


            imageButton00 = (ImageButton) findViewById(R.id.imageButton00);
            imageButton01 = (ImageButton) findViewById(R.id.imageButton01);
            imageButton02 = (ImageButton) findViewById(R.id.imageButton02);
            imageButton10 = (ImageButton) findViewById(R.id.imageButton10);
            imageButton11 = (ImageButton) findViewById(R.id.imageButton11);
            imageButton12 = (ImageButton) findViewById(R.id.imageButton12);
            imageButton20 = (ImageButton) findViewById(R.id.imageButton20);
            imageButton21 = (ImageButton) findViewById(R.id.imageButton21);
            imageButton22 = (ImageButton) findViewById(R.id.imageButton22);

            // image Button array
            imageArray = new ImageButton[]{imageButton00,imageButton01, imageButton02,
                    imageButton10, imageButton11, imageButton12,
                    imageButton20, imageButton21, imageButton22};

            //registering event listeners for imageArray or for Gride
            for(ImageButton b: imageArray){
                //set every button in imageArray
                b.setOnClickListener(this);
            }

            // OnClickListener & onClick for new game button
            newgame = (Button) findViewById(R.id.newgame);
            newgame.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    turn = true;
                    turn_count = 0;
                    startnewgame();
                }
            });

            // OnClickListener & onClick for mute/unmute button
            volume = (ImageButton) findViewById(R.id.volume);
            volume.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkVolume(isVolume);
                }
            });
            }

        //check to mute/unmute
        void checkVolume(boolean enable) {
            if (enable) {
                volume.setBackgroundResource(R.drawable.unmute);
                mediaPlayer.setVolume(1, 1);
                isVolume = false;
            } else {
                volume.setBackgroundResource(R.drawable.mute);
                mediaPlayer.setVolume(0, 0);
                isVolume = true;
            }
        }

        //onClick for grid
        @Override
        public void onClick(View v){
            //view >>> ImageButton
            ImageButton b = (ImageButton) v;

            if(turn){
                // X's turn
                b.setImageResource(R.drawable.satancross);
                b.setColorFilter(Color.argb(255, 255, 255, 255)); // White Tint
                b.setBackgroundResource(R.drawable.redflame);
                b.setTag("X");

            }else{
                //O's turn
                b.setImageResource(R.drawable.godcross);
                b.setColorFilter(Color.argb(255, 255, 255, 0)); // yellow Tint
                b.setBackgroundResource(R.drawable.blueflame);
                b.setTag("O");
            }

            // increment turn_count in case of tie
            turn_count++;
            //make sure button cannot be click on again.
            b.setClickable(false);
            //change the turn
            turn = !turn;

            //check for winner for every click on grid
            check_Winner();
        }

        private void check_Winner() {
            boolean there_is_a_winner = false;

            // horizontal checks:
            if (imageButton00.getTag() == imageButton01.getTag()
                    && imageButton01.getTag() == imageButton02.getTag()
                    && !imageButton00.isClickable()) {
                there_is_a_winner = true;

                imageButton10.setBackgroundResource(android.R.color.transparent);
                imageButton11.setBackgroundResource(android.R.color.transparent);
                imageButton12.setBackgroundResource(android.R.color.transparent);

                imageButton20.setBackgroundResource(android.R.color.transparent);
                imageButton21.setBackgroundResource(android.R.color.transparent);
                imageButton22.setBackgroundResource(android.R.color.transparent);

            } else if (imageButton10.getTag() == imageButton11.getTag()
                    && imageButton11.getTag() == imageButton12.getTag()
                    && !imageButton10.isClickable()) {
                there_is_a_winner = true;

                imageButton00.setBackgroundResource(android.R.color.transparent);
                imageButton01.setBackgroundResource(android.R.color.transparent);
                imageButton02.setBackgroundResource(android.R.color.transparent);

                imageButton20.setBackgroundResource(android.R.color.transparent);
                imageButton21.setBackgroundResource(android.R.color.transparent);
                imageButton22.setBackgroundResource(android.R.color.transparent);

            } else if (imageButton20.getTag() == imageButton21.getTag()
                    && imageButton21.getTag() == imageButton22.getTag()
                    && !imageButton20.isClickable()) {
                there_is_a_winner = true;

                imageButton00.setBackgroundResource(android.R.color.transparent);
                imageButton01.setBackgroundResource(android.R.color.transparent);
                imageButton02.setBackgroundResource(android.R.color.transparent);

                imageButton10.setBackgroundResource(android.R.color.transparent);
                imageButton11.setBackgroundResource(android.R.color.transparent);
                imageButton12.setBackgroundResource(android.R.color.transparent);


                // vertical checks:
            } else if (imageButton00.getTag() == imageButton10.getTag()
                    && imageButton10.getTag() == imageButton20.getTag()
                    && !imageButton00.isClickable()) {
                there_is_a_winner = true;

                imageButton01.setBackgroundResource(android.R.color.transparent);
                imageButton02.setBackgroundResource(android.R.color.transparent);

                imageButton11.setBackgroundResource(android.R.color.transparent);
                imageButton12.setBackgroundResource(android.R.color.transparent);

                imageButton21.setBackgroundResource(android.R.color.transparent);
                imageButton22.setBackgroundResource(android.R.color.transparent);

            } else if (imageButton01.getTag() == imageButton11.getTag()
                    && imageButton11.getTag() == imageButton21.getTag()
                    && !imageButton01.isClickable()) {
                there_is_a_winner = true;

                imageButton00.setBackgroundResource(android.R.color.transparent);

                imageButton02.setBackgroundResource(android.R.color.transparent);

                imageButton10.setBackgroundResource(android.R.color.transparent);

                imageButton12.setBackgroundResource(android.R.color.transparent);

                imageButton20.setBackgroundResource(android.R.color.transparent);

                imageButton22.setBackgroundResource(android.R.color.transparent);

            } else if (imageButton02.getTag() == imageButton12.getTag()
                    && imageButton12.getTag() == imageButton22.getTag()
                    && !imageButton02.isClickable()) {
                there_is_a_winner = true;

                imageButton00.setBackgroundResource(android.R.color.transparent);
                imageButton01.setBackgroundResource(android.R.color.transparent);

                imageButton10.setBackgroundResource(android.R.color.transparent);
                imageButton11.setBackgroundResource(android.R.color.transparent);

                imageButton20.setBackgroundResource(android.R.color.transparent);
                imageButton21.setBackgroundResource(android.R.color.transparent);

                // diagonal checks:
            } else if (imageButton00.getTag() == imageButton11.getTag()
                    && imageButton11.getTag() == imageButton22.getTag()
                    && !imageButton00.isClickable()){
                there_is_a_winner = true;

                imageButton01.setBackgroundResource(android.R.color.transparent);
                imageButton02.setBackgroundResource(android.R.color.transparent);

                imageButton10.setBackgroundResource(android.R.color.transparent);

                imageButton12.setBackgroundResource(android.R.color.transparent);

                imageButton20.setBackgroundResource(android.R.color.transparent);
                imageButton21.setBackgroundResource(android.R.color.transparent);

            }
            else if (imageButton02.getTag() == imageButton11.getTag()
                    && imageButton11.getTag() == imageButton20.getTag()
                    && !imageButton11.isClickable()) {
                there_is_a_winner = true;

                imageButton00.setBackgroundResource(android.R.color.transparent);
                imageButton01.setBackgroundResource(android.R.color.transparent);

                imageButton10.setBackgroundResource(android.R.color.transparent);

                imageButton12.setBackgroundResource(android.R.color.transparent);

                imageButton21.setBackgroundResource(android.R.color.transparent);
                imageButton22.setBackgroundResource(android.R.color.transparent);
            }

            if (there_is_a_winner) {

                //set entire grid to not clickable
                for(ImageButton b: imageArray){
                    b.setClickable(false);
                }

                if (!turn) {
                    //message("X wins!");
                    message("Satan Conquers Earth!", turn, turn_count);

                }else {
                    //message("O wins!");
                    message("God Liberates Earth!", turn, turn_count);

                }
            } else if (turn_count == 9) {
                //message("Tie!");
                message("Earth is Destroyed!", turn, turn_count);

            }
        }

        private void message(String text, boolean turn, int turn_count) {

            // X wins!
            if(!turn && !(turn_count == 9)) {
                Toast popup = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                View popView = popup.getView();
                popView.setBackgroundResource(R.drawable.redflame);

                popup.show();

            // O wins!
            }else if(turn && !(turn_count == 9)){
                Toast popup = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                View popView = popup.getView();
                popView.setBackgroundResource(R.drawable.blueflame1);
                popup.show();

            // Tie!
            } else if (turn_count == 9){
                Toast popup = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                View popView = popup.getView();
                popView.setBackgroundResource(R.drawable.popup_color);
                popup.show();
            }
        }

        void startnewgame() {
            // restart mediaPlayer
            mediaPlayer.seekTo(0);
            mediaPlayer.start();

            for (ImageButton b : imageArray) {
                // reset tags of imageArray
                b.setTag("");
                // reset imageArray or Grid to Clickable
                b.setClickable(true);
                if (true) {

                    b.setImageResource(android.R.color.transparent);
                    b.setBackgroundResource(R.drawable.button_boarder);
                } else {

                    b.setBackgroundResource(R.drawable.button_boarder);
                }
            }
        }
}