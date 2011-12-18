package jp.sumasu.u_s_k;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

public abstract class UskPluginActivity extends Activity {
	private static final int[][] PREFERENCE_KEYS = {
			{ R.string.key_use_usk, R.string.hash_usk },
			{ R.string.key_use_sekitoba, R.string.hash_sekitoba },
			{ R.string.key_use_hidaka, R.string.hash_hidaka },
			{ R.string.key_use_kakenavi, R.string.hash_kakenavi },
			{ R.string.key_use_miguse, R.string.hash_miguse } };

	private String tweet;
	private String screenName;
	protected Activity _self;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Intent intent = getIntent();
		tweet = intent.getStringExtra(Intent.EXTRA_TEXT);
		screenName = intent.getStringExtra("user_screen_name");

		SharedPreferences pref = PreferenceManager
				.getDefaultSharedPreferences(this);

		if (pref.getBoolean(getString(R.string.key_force_sekitoba), false)
				&& screenName.equals("sekitoba")) {
			goTweet(getString(R.string.hash_sekitoba));
			return;
		}

		ArrayList<String> items = new ArrayList<String>();
		for (int i = 0; i < PREFERENCE_KEYS.length; i++) {
			if (pref.getBoolean(getString(PREFERENCE_KEYS[i][0]), true))
				items.add(getString(PREFERENCE_KEYS[i][1]));
		}
		if (items.size() == 0) {
			Toast.makeText(this, "no options. plz check settings",
					Toast.LENGTH_SHORT).show();
			_self.finish();
			return;
		} else if (items.size() == 1) {
			goTweet(items.get(0));
			return;
		}
		final String[] selections = (String[]) items.toArray(new String[0]);
		new AlertDialog.Builder(this).setTitle("")
				.setItems(selections, new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						goTweet(selections[which].toString());
					}
				}).setOnCancelListener(new OnCancelListener() {
					@Override
					public void onCancel(DialogInterface dialog) {
						_self.finish();
					}
				}).show();
	}

	private void goTweet(String prefix) {
		String extraText = String.format("%s RT @%s: %s", prefix, screenName,
				tweet);
		startIntent(extraText);
	}

	protected abstract void startIntent(String extraText);

}