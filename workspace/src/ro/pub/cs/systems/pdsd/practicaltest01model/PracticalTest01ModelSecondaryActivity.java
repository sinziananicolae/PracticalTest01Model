package ro.pub.cs.systems.pdsd.practicaltest01model;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PracticalTest01ModelSecondaryActivity extends Activity {
	
	private ButtonClickListener buttonClickListener = new ButtonClickListener();
	
	private class ButtonClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			if (v instanceof Button){
				switch (v.getId()){
					case R.id.ok_button:
						setResult(RESULT_OK, new Intent());
						finish();
						break;
					case R.id.cancel_button:
						setResult(RESULT_CANCELED, new Intent());
						finish();
						break;
				}
			}
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practical_test01_model_secondary);
	
		Button okButton = (Button)findViewById(R.id.ok_button);
		okButton.setOnClickListener(buttonClickListener);
		
		Button cancelButton = (Button)findViewById(R.id.cancel_button);
		cancelButton.setOnClickListener(buttonClickListener);
		
		TextView numberOfTotalClicks = (TextView)findViewById(R.id.number_of_clicks_text_view);
		
		Intent intent = getIntent();
		if(intent != null){
			String numberOfClicks = intent.getStringExtra("number_of_clicks");
			if(numberOfClicks != null){
				numberOfTotalClicks.setText(getResources().getString(R.string.number_of_clicks).replace("???", numberOfClicks));
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater()
				.inflate(R.menu.practical_test01_model_secondary, menu);
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
