package com.example.pvideoplayer

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.VideoView
import android.widget.ViewFlipper
import com.khizar1556.mkvideoplayer.MKPlayerActivity
import net.alhazmy13.mediapicker.Video.VideoPicker

class MainActivity : AppCompatActivity() {
    private lateinit var videoPlayer: VideoView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        val playVideo = findViewById<Button>(R.id.btn_play_video)
        videoPlayer = findViewById<VideoView>(R.id.video_player)
        playVideo.setOnClickListener {
            pickVideo()
        }
    }

    private fun pickVideo() {
        val videoPicker = VideoPicker.Builder(this@MainActivity)
            .mode(VideoPicker.Mode.GALLERY)
            .directory(VideoPicker.Directory.DEFAULT)
            .extension(VideoPicker.Extension.MP4)
            .enableDebuggingMode(true)
        videoPicker.build()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == VideoPicker.VIDEO_PICKER_REQUEST_CODE && resultCode == RESULT_OK) {
            val videoPaths = data!!.getStringArrayListExtra(VideoPicker.EXTRA_VIDEO_PATH)
            //MKPlayerActivity.configPlayer(this@MainActivity).play(videoPaths!![0])
            videoPlayer.setVideoURI(Uri.parse(videoPaths!![0]))
            videoPlayer.start()

        }
    }
}