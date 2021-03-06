package ro.pub.cs.systems.pdsd.practicaltest01model;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest01ModelMainActivity extends Activity {

	protected ButtonClickListener buttonListener = new ButtonClickListener();
	private final static int SECONDARY_ACTIVITY_REQUEST_CODE = 1;

	protected class ButtonClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			EditText leftEditText = (EditText) findViewById(R.id.left_edit_text);
			EditText rightEditText = (EditText) findViewById(R.id.right_edit_text);

			if (v instanceof Button) {
				switch (v.getId()) {
				case R.id.left_button:
					int leftClicksNumber = Integer.parseInt(leftEditText
							.getText().toString());
					leftClicksNumber += 1;
					leftEditText.setText(String.valueOf(leftClicksNumber));
					break;
				case R.id.right_button:
					int rightClicksNumber = Integer.parseInt(rightEditText
							.getText().toString());
					rightClicksNumber += 1;
					rightEditText.setText(String.valueOf(rightClicksNumber));
					break;
				case R.id.navigate_to_secondary_activity_button:
					Intent intent = new Intent(
							"ro.pub.cs.systems.pdsd.intent.action.PracticalTest01ModelSecondaryActivity");
					intent.putExtra("number_of_clicks", String.valueOf(Integer
							.parseInt(leftEditText.getText().toString())
							+ Integer.parseInt(rightEditText.getText()
									.toString())));
					startActivityForResult(intent,
							SECONDARY_ACTIVITY_REQUEST_CODE);
					break;
				}
			}

		}
	}

	protected class TextWatcherListener implements TextWatcher {

		private View view;

		private TextWatcherListener(View view) {
			this.view = view;
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			switch (view.getId()) {
				case R.id.watcher_edit_text:
					int numberOfLetters;
					if (s == null) {
						numberOfLetters = 0;
					} else {
						numberOfLetters = s.toString().length();
					}
					TextView numberOfLettersTextView = (TextView) findViewById(R.id.number_of_letters);
					numberOfLettersTextView.setText(getResources().getString(
							R.string.number_of_letters).replace("???",
							Integer.toString(numberOfLetters)));
					break;
				case R.id.change_button_color:
					Button colorButton = (Button)findViewById(R.id.color_button);
					
					int colorId = getResources().getIdentifier(s.toString().toLowerCase(), "color", getPackageName());
					if (colorId > 0){
						colorButton.setBackgroundColor(getResources().getColor(colorId));
					}
					else {
						colorButton.setBackgroundColor(getResources().getColor(R.color.grey));
					}
					break;
			}
		}

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub

		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practical_test01_model_main);

		Button leftButton = (Button) findViewById(R.id.left_button);
		leftButton.setOnClickListener(buttonListener);

		Button rightButton = (Button) findViewById(R.id.right_button);
		rightButton.setOnClickListener(buttonListener);

		Button navigateToSecondaryActivityButton = (Button) findViewById(R.id.navigate_to_secondary_activity_button);
		navigateToSecondaryActivityButton.setOnClickListener(buttonListener);

		EditText watcherTest = (EditText) findViewById(R.id.watcher_edit_text);
		watcherTest.addTextChangedListener(new TextWatcherListener(watcherTest));
		
		EditText changeButtonColor = (EditText) findViewById(R.id.change_button_color);
		changeButtonColor.addTextChangedListener(new TextWatcherListener(changeButtonColor));

		EditText leftEditText = (EditText) findViewById(R.id.left_edit_text);
		EditText rightEditText = (EditText) findViewById(R.id.right_edit_text);

		if (savedInstanceState != null) {
			String leftCounter = savedInstanceState.getString("leftCounter");
			if (leftCounter != null) {
				leftEditText.setText(leftCounter);
			} else {
				leftEditText.setText(String.valueOf(0));
			}

			String rightCounter = savedInstanceState.getString("rightCounter");
			if (rightCounter != null) {
				rightEditText.setText(rightCounter);
			} else {
				rightEditText.setText(String.valueOf(0));
			}
		} else {
			leftEditText.setText(String.valueOf(0));
			rightEditText.setText(String.valueOf(0));
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {

		EditText leftEditText = (EditText) findViewById(R.id.left_edit_text);
		EditText rightEditText = (EditText) findViewById(R.id.right_edit_text);

		outState.putString("leftCounter", leftEditText.getText().toString());
		outState.putString("rightCounter", rightEditText.getText().toString());
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Toast.makeText(this, "The activity returned with result " + resultCode,
				Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.practical_test01_model_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
