package com.example.mymediaplayer;

import com.example.mymediaplayer.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MyActivity extends Activity implements OnClickListener {

	Button more;
    Intent intent;
    Uri uri;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my);
		
        more=(Button) findViewById(R.id.more);
        more.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		intent=new Intent();
		intent.setAction(Intent.ACTION_VIEW);
		intent.setData(Uri.parse("https://y.qq.com/"));
		startActivity(intent);
	}

	
}
