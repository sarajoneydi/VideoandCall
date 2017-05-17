package excercise.myapplicationvideoandcall;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by LENOVO on 5/17/2017.
 */

public class IncomingCallReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"incoming call during video play!",Toast.LENGTH_SHORT).show();
    }
}
