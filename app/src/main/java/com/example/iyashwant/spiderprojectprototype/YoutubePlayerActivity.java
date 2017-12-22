package com.example.iyashwant.spiderprojectprototype;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.io.InputStream;
import java.net.URL;

public class YoutubePlayerActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

        private static final int RECOVERY_REQUEST = 1;
        private YouTubePlayerView youTubeView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_youtube_player);

                youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
                youTubeView.initialize(Config.YOUTUBE_API_KEY, this);
        }

        @Override
        public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
                if (!wasRestored) {

                        player.loadVideo("eGCM444_mN0"); // Plays https://www.youtube.com/watch?v=eGCM444_mN0
                }
        }

        @Override
        public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason) {
                if (errorReason.isUserRecoverableError()) {
                        errorReason.getErrorDialog(this, RECOVERY_REQUEST).show();
                } else {
                        String error = String.format(getString(R.string.player_error), errorReason.toString());
                        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
                }
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                if (requestCode == RECOVERY_REQUEST) {
                        // Retry initialization if user performed a recovery action
                        getYouTubePlayerProvider().initialize(Config.YOUTUBE_API_KEY, this);
                }
        }

        protected YouTubePlayer.Provider getYouTubePlayerProvider() {
                return youTubeView;
        }




}