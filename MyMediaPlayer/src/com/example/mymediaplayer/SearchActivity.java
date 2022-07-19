package com.example.mymediaplayer;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SearchActivity extends Activity implements OnClickListener {

	private AutoCompleteTextView song;
	String []sn= {"hello","fade","lemon","memory"};
	private Button bts;
	private EditText e1,e2,e3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		
		song=(AutoCompleteTextView) findViewById(R.id.song);
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(SearchActivity.this,
				android.R.layout.simple_dropdown_item_1line,
				sn);
		song.setAdapter(adapter);
		bts=(Button) findViewById(R.id.bts);
		bts.setOnClickListener(this);
		e1=(EditText) findViewById(R.id.e1);
		e2=(EditText) findViewById(R.id.e2);
		e3=(EditText) findViewById(R.id.e3);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String input_song=song.getText().toString();
		if(input_song.equals("")) {
			e1.setText(" ");
			e2.setText(" ");
			e3.setText(" ");
			Toast.makeText(SearchActivity.this, "歌名不能为空", Toast.LENGTH_LONG).show();
		}
		else {
			if(input_song.equals("hello")) {
				e1.setText("歌名：hello");
				e2.setText("歌手：Adele");
				e3.setText("时长：4:55");
			}
			else if(input_song.equals("fade")) {
				e1.setText("歌名：fade");
				e2.setText("歌手：Alan Walker");
				e3.setText("时长：4:22");
			}
			else if(input_song.equals("lemon")) {
				e1.setText("歌名：lemon");
				e2.setText("歌手：Akie秋绘");
				e3.setText("时长：2:44");
			}
			else if(input_song.equals("memory")) {
				e1.setText("歌名：memory");
				e2.setText("歌手：Elaine Paige");
				e3.setText("时长：4:16");
			}
			else {
				e1.setText(" ");
				e2.setText(" ");
				e3.setText(" ");
				Toast.makeText(SearchActivity.this, "抱歉，没有搜到", Toast.LENGTH_LONG).show();
			}
		}
	}
}
