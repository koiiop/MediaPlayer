package com.example.mymediaplayer;

import com.example.mymediaplayer.R;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MenuActivity extends Activity implements OnClickListener {

	private Button bt1,bt2,bt3,bt4;
	Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		bt1=(Button) findViewById(R.id.bt1);
		bt2=(Button) findViewById(R.id.bt2);
		bt3=(Button) findViewById(R.id.bt3);
		bt4=(Button) findViewById(R.id.bt4);
		bt1.setOnClickListener(this);
		bt2.setOnClickListener(this);
		bt3.setOnClickListener(this);
		bt4.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()) {
		case R.id.bt1:
			intent=new Intent();
			intent.setAction("com.example.mymediaplayer.Search_Action");
			startActivity(intent);
			break;
		case R.id.bt2:
			intent=new Intent();
			intent.setAction("com.example.mymediaplayer.Scan_Action");
			startActivity(intent);
			break;
		case R.id.bt3:
			intent=new Intent();
			intent.setAction("com.example.mymediaplayer.Play_Action");
			startActivity(intent);
			break;
		case R.id.bt4:
			intent=new Intent();
			intent.setAction("com.example.mymediaplayer.My_Action");
			startActivity(intent);
			break;
		}
	}
	


	
}
