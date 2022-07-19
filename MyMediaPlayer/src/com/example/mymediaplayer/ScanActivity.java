package com.example.mymediaplayer;

import java.io.IOException;

import com.example.mymediaplayer.MainActivity;
import com.example.mymediaplayer.R;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Audio.Media;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

public class ScanActivity extends Activity {

	ListView list;
	Cursor cursor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scan);
		list=(ListView) findViewById(R.id.listview);
		ContentResolver cv=getContentResolver();
		Uri uri=MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
		cursor=cv.query(uri, null, null, null, null);
		String []from= {Media.TITLE,Media.ARTIST};
		int []to= {android.R.id.text1,android.R.id.text2};
		SimpleCursorAdapter adapter=new SimpleCursorAdapter(ScanActivity.this,android.R.layout.simple_list_item_2,cursor,from,to);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				String musicName=cursor.getString(cursor.getColumnIndex(Media.DATA));
				Uri musicUri=Uri.parse(musicName);
				MediaPlayer mp=new MediaPlayer();
				mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
				try {
					mp.setDataSource(getApplicationContext(), musicUri);
					mp.prepare();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(mp.isPlaying()){
		            mp.stop();
		        } else {
		            mp.start();
		        }
			}
			
		});
	}

	
}
