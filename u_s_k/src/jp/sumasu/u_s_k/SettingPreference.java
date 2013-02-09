package jp.sumasu.u_s_k;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class SettingPreference extends PreferenceActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings);
	}
}
