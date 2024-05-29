package com.example.video_player_app;

import static com.example.video_player_app.VideoAdapter.videoFiles;

import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaExtractor;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class PlayerActivity extends AppCompatActivity {

    PlayerView playerView;
    SimpleExoPlayer simpleExoPlayer;
    int position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreenMethod();
       setContentView(R.layout.activity_player);
       getSupportActionBar().hide();
       playerView = findViewById(R.id.exoplayer_movie);
       position = getIntent().getIntExtra("position", -1);
       String path = videoFiles.get(position).getPath();

       if (path != null)
       {
           Uri uri = Uri.parse(path);
           simpleExoPlayer = new SimpleExoPlayer.Builder(this).build();
           DataSource.Factory factory = new DefaultDataSourceFactory(this, Util.getUserAgent(this, "My app Name"));
           ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
           MediaSource mediaSource = new ProgressiveMediaSource.Factory(factory, extractorsFactory).createMediaSource(MediaItem.fromUri(uri));
           playerView.setPlayer(simpleExoPlayer);
           playerView.setKeepScreenOn(true);
           simpleExoPlayer.prepare(mediaSource);
           simpleExoPlayer.setPlayWhenReady(true);

       }
    }

    private void setFullScreenMethod() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}