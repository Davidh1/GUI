package com.Blue.GUI;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;

public class GUI extends Activity {
	AutoCompleteTextView searchAutoCompleteTextView;
	Spinner mainSpinner;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		searchAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.searchAutoCompleteTextView);
		mainSpinner = (Spinner) findViewById(R.id.mainSpinner);

		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mainSpinner.setAdapter(adapter);
		searchAutoCompleteTextView.setAdapter(adapter);

		SQLiteDatabase myDataBase = SQLiteDatabase.openDatabase(
				DbConstants.DB_PATH + DbConstants.DB_NAME, null,
				SQLiteDatabase.OPEN_READONLY);
		
		Cursor cursor = myDataBase.rawQuery("select * from Buildings order by "
				+ DbConstants.NAME, null);
		cursor.moveToFirst();
		while (cursor.isAfterLast() == false) {
			adapter.add(cursor.getString(1));
			cursor.moveToNext();
		}
		cursor.close();

		

	}

}
