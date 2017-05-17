package excercise.myapplicationvideoandcall;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.VideoView;
public class VideoPlayerActivity extends AppCompatActivity {
    VideoView santoorVideo;
    String videoURL="http://hw15.asset.aparat.com/aparat-video/f734ef624991ef48824b603a9b388ea76848719-240p__57252.mp4?direct=1";
    BroadcastReceiver incomingCallReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        santoorVideo=(VideoView)findViewById(R.id.santoorVideo);
        santoorVideo.setMediaController(new MediaController(this));
        santoorVideo.setVideoURI(Uri.parse(videoURL));
        santoorVideo.start();
        int currentPlayed=santoorVideo.getCurrentPosition();
        if(ContextCompat.checkSelfPermission(VideoPlayerActivity.this,
                Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(VideoPlayerActivity.this,
                    new String[]{Manifest.permission.READ_PHONE_STATE},1500);
        }
        incomingCallReceiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if(santoorVideo.isPlaying()) {
                    santoorVideo.pause();
                }
            }
        };
        IntentFilter callIntentFilter=new IntentFilter("android.intent.action.PHONE_STATE");
        registerReceiver(incomingCallReceiver,callIntentFilter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(incomingCallReceiver);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1500){}
    }

}
