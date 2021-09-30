package com.example.qarapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView


class VideoOffLoading : YouTubeBaseActivity() {

    lateinit var goToActivity: Button
    lateinit var playVideo: Button
    lateinit var youTubePlayerView: YouTubePlayerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_off_loading)


        goToActivity = findViewById(R.id.goToActivity)
        youTubePlayerView = findViewById(R.id.YoutubeVideo)
        playVideo = findViewById(R.id.Play)


        playVideo.setOnClickListener(View.OnClickListener {
            youTubePlayerView.initialize(PlayerConfig.ApiKey,
                object : YouTubePlayer.OnInitializedListener {
                    override fun onInitializationSuccess(
                        provider: YouTubePlayer.Provider,
                        youTubePlayer: YouTubePlayer, b: Boolean
                    ) {
                        // do any work here to cue video, play video, etc.
                        youTubePlayer.cueVideo("_JY8XwIoIw0")
                    }

                    override fun onInitializationFailure(
                        provider: YouTubePlayer.Provider,
                        youTubeInitializationResult: YouTubeInitializationResult
                    ) {
                    }
                })
        })
        goToActivity.setOnClickListener(View.OnClickListener {
            val i = Intent(applicationContext, ImagesOfNews::class.java)
            startActivity(i)
        })
    }


}