package com.example.mymediaplayer;

import com.example.mymediaplayer.MainActivity;
import com.example.mymediaplayer.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class MainActivity extends Activity implements OnClickListener {

	private final static String TABLE_NAME="table";
	private AutoCompleteTextView name;
	String []nm= {"aabbcc","annasua"};
	private EditText pass;
	private CheckBox cbRemember;
	private SharedPreferences mySP;
	private SharedPreferences.Editor editor;
	private Button bt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mySP=getSharedPreferences(TABLE_NAME, Activity.MODE_PRIVATE);
		editor=mySP.edit();
		name=(AutoCompleteTextView) findViewById(R.id.name);
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(MainActivity.this,
				android.R.layout.simple_dropdown_item_1line,
				nm);
		name.setAdapter(adapter);
		pass=(EditText) findViewById(R.id.pass);
		cbRemember=(CheckBox) findViewById(R.id.cbRemember);
		bt=(Button) findViewById(R.id.bt);
		String getName=mySP.getString("name", "");
		String getPass=mySP.getString("pass", "");
		Boolean isRemember=mySP.getBoolean("remember",false);
		name.setText(getName);
		pass.setText(getPass);
		cbRemember.setChecked(isRemember);
		bt.setOnClickListener(this);
	}
	protected void onStop() {
		super.onStop();
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String input_name=name.getText().toString();
		String input_pass=pass.getText().toString();
		Boolean isRemember=cbRemember.isChecked();
		if(isRemember) {
			
		    editor.putString("name", input_name);
		    editor.putString("pass", input_pass);
		    editor.putBoolean("remember", isRemember);
		    editor.commit();
		    Toast.makeText(MainActivity.this, "保存成功", Toast.LENGTH_LONG).show();
	    }
		Intent intent=new Intent();
		intent.setAction("com.example.mymediaplayer.Self_Action");
		startActivity(intent);
	}
}