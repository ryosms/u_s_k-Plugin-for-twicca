package jp.sumasu.u_s_k;

import android.app.Activity;
import android.content.ComponentName;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;

public class HiddenSettingActivity extends Activity implements OnClickListener {
	private SharedPreferences pref;
	private CheckBox chkSekitoba;
	private CheckBox chkUseQtUsk;
	private CheckBox chkUseMizuki;
	private boolean useQtUks;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hidden_settings);

		pref = PreferenceManager.getDefaultSharedPreferences(this);

		chkSekitoba = (CheckBox) findViewById(R.id.chkForceSekitoba);
		if (pref.getBoolean(getString(R.string.key_force_sekitoba), false)) {
			chkSekitoba.setChecked(true);
		} else {
			chkSekitoba.setChecked(false);
		}

		chkUseMizuki = (CheckBox) findViewById(R.id.chkMizuki);
		if (pref.getBoolean(getString(R.string.key_use_mizuki), false)) {
			chkUseMizuki.setChecked(true);
		} else {
			chkUseMizuki.setChecked(false);
		}

		chkUseQtUsk = (CheckBox) findViewById(R.id.chkUseQtUsk);
		useQtUks = pref.getBoolean(getString(R.string.key_use_qt_usk), false);
		if (useQtUks) {
			chkUseQtUsk.setChecked(true);
		} else {
			chkUseQtUsk.setChecked(false);
		}

		findViewById(R.id.btnOk).setOnClickListener(this);
		findViewById(R.id.btnCancel).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnOk:
			savePreferences();
			this.finish();
		case R.id.btnCancel:
			this.finish();
			break;
		}
	}

	private void savePreferences() {
		Editor editor = pref.edit();
		editor.putBoolean(getString(R.string.key_force_sekitoba),
				chkSekitoba.isChecked());
		editor.putBoolean(getString(R.string.key_use_mizuki),
				chkUseMizuki.isChecked());
		boolean useQt = chkUseQtUsk.isChecked();
		editor.putBoolean(getString(R.string.key_use_qt_usk), useQt);
		editor.commit();
		if (useQt == useQtUks)
			return;

		PackageManager pm = this.getPackageManager();
		int state = (useQt ? PackageManager.COMPONENT_ENABLED_STATE_ENABLED
				: PackageManager.COMPONENT_ENABLED_STATE_DISABLED);
		pm.setComponentEnabledSetting(new ComponentName(this,
				UskReplyActivity.class), state, PackageManager.DONT_KILL_APP);
	}
}
