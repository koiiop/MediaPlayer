package com.example.mymediaplayer;

import java.text.SimpleDateFormat;

import com.example.mymediaplayer.MainActivity;
import com.example.mymediaplayer.MusicService;
import com.example.mymediaplayer.R;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class PlayActivity extends Activity implements OnClickListener {

    private MusicService musicService;
    private SeekBar seekBar;
    private TextView musicStatus, musicTime;
    private Button btnPlayOrPause, btnStop, btnQuit;
    private SimpleDateFormat time = new SimpleDateFormat("m:ss");
    private ServiceConnection sc = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            musicService = ((MusicService.MyBinder)iBinder).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            musicService = null;
        }
    };
    private void bindServiceConnection() {
        Intent intent = new Intent(PlayActivity.this, MusicService.class);
        startService(intent);
        bindService(intent, sc, this.BIND_AUTO_CREATE);
    }
    public android.os.Handler handler = new android.os.Handler();
    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if(musicService.mp.isPlaying()) {
                musicStatus.setText(getResources().getString(R.string.playing));
                btnPlayOrPause.setText(getResources().getString(R.string.pause).toUpperCase());
            } else {
                musicStatus.setText(getResources().getString(R.string.pause));
                btnPlayOrPause.setText(getResources().getString(R.string.play).toUpperCase());
            }
            musicTime.setText(time.format(musicService.mp.getCurrentPosition()) + "/"
                    + time.format(musicService.mp.getDuration()));
            seekBar.setProgress(musicService.mp.getCurrentPosition());
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if (fromUser) {
                        musicService.mp.seekTo(seekBar.getProgress());
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
            handler.postDelayed(runnable, 100);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        Log.d("hint", "ready to new MusicService");
        musicService = new MusicService();
        Log.d("hint", "finish to new MusicService");
        bindServiceConnection();

        seekBar = (SeekBar) findViewById(R.id.MusicSeekBar);
        seekBar.setProgress(musicService.mp.getCurrentPosition());
        seekBar.setMax(musicService.mp.getDuration());

        musicStatus = (TextView) findViewById(R.id.MusicStatus);
        musicTime = (TextView) findViewById(R.id.MusicTime);

        btnPlayOrPause = (Button) findViewById(R.id.BtnPlayorPause);

        Log.d("hint", Environment.getExternalStorageDirectory().getAbsolutePath()+"/You.mp3");
    }

    @Override
    protected void onResume() {
        if(musicService.mp.isPlaying()) {
            musicStatus.setText(getResources().getString(R.string.playing));
        } else {
            musicStatus.setText(getResources().getString(R.string.pause));
        }

        seekBar.setProgress(musicService.mp.getCurrentPosition());
        seekBar.setMax(musicService.mp.getDuration());
        handler.post(runnable);
        super.onResume();
        Log.d("hint", "handler post runnable");
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.BtnPlayorPause:
                musicService.playOrPause();
                break;
            case R.id.BtnStop:
                musicService.stop();
                seekBar.setProgress(0);
                break;
            case R.id.BtnQuit:
                handler.removeCallbacks(runnable);
                unbindService(sc);
                try {
                    System.exit(0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btnPre:
                musicService.preMusic();
                break;
            case R.id.btnNext:
                musicService.nextMusic();
                break;
            default:
                break;
        }
    }

    @Override
    public void onDestroy() {
        unbindService(sc);
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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