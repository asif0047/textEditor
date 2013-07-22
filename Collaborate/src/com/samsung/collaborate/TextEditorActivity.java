package com.samsung.collaborate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.samsung.chord.ChordManager;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class TextEditorActivity extends FragmentActivity {

	public CharSequence filename = "storage/sdcard0/collaborate/test.txt";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_text_editor);
		Toast.makeText(this,
				Environment.getExternalStorageDirectory().toString(),
				Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.text_editor, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.save:
			// i will create a dialog box here which will prompt the user to
			// input a file name here.
			showSaveDialog();
			saveFile(filename);
			Toast.makeText(this,
					"Text File saved in storage/sdcard0/collaborate/test.txt",
					Toast.LENGTH_SHORT).show();
			return true;
		case R.id.open:
			showOpenDialog();
			openFile(filename);
			Toast.makeText(this, "Text File should be opened",
					Toast.LENGTH_SHORT).show();
			return true;
		case R.id.newfile:
			createNewFile();
			Toast.makeText(this, "Text File should be created",
					Toast.LENGTH_SHORT).show();
			return true;
		case R.id.StartChord:
			
			ChordManager chordmanager = ChordManager.getInstance(this);
			chordmanager.setTempDirectory(Environment.getExternalStorageDirectory()
            .getAbsolutePath() + "/Chord");
			chordmanager.setHandleEventLooper(getMainLooper());
			List<Integer> interfacetypes = chordmanager.getAvailableInterfaceTypes();			
			
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void showOpenDialog() {
		// TODO Auto-generated method stub
		FragmentTransaction ft = getSupportFragmentManager()
				.beginTransaction();
		OpenDialogFragment openDialog = new OpenDialogFragment();
		openDialog.show(ft, "Dialog");
	}

	private void showSaveDialog() {
		// TODO Auto-generated method stub
		FragmentTransaction ft = getSupportFragmentManager()
				.beginTransaction();
		SaveDialogFragment saveDialog = new SaveDialogFragment();
		saveDialog.show(ft, "dialog");
	}

	private void createNewFile() {
		// TODO Auto-generated method stub

	}

	private void openFile(CharSequence filename) {
		// TODO Auto-generated method stub
		EditText writtentext = (EditText) findViewById(R.id.editText1);
		StringBuffer result = new StringBuffer();
		String content = "";

		try {
			FileReader f = new FileReader(filename.toString());
			File file = new File(filename.toString());

			if (f == null) {
				throw (new FileNotFoundException());
			}

			if (file.isDirectory()) {
				throw (new IOException());
			}

			if (file.length() != 0 && !file.isDirectory()) {
				char[] buffer;
				buffer = new char[1100]; // made it bigger just in case

				int read = 0;

				do {
					read = f.read(buffer, 0, 1000);

					if (read >= 0) {
						result.append(buffer, 0, read);
					}
				} while (read >= 0);
			}

		} catch (FileNotFoundException e) {
			// file not found
			e.printStackTrace();
		} catch (IOException e) {
			// error reading file
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		content = result.toString();
		writtentext.setText(content);
		writtentext.requestFocus();
	}

	private void saveFile(CharSequence filename) {
		// TODO Auto-generated method stub
		EditText writtentext = (EditText) findViewById(R.id.editText1);
		File f = new File(filename.toString());

		// create file
		try {
			FileWriter fstream = new FileWriter(filename.toString());
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(writtentext.getText().toString());
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writtentext.requestFocus();
	}
}
