package jp.sumasu.u_s_k;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;

public class HiddenSettingActivity extends Activity implements OnClickListener {
	private SharedPreferences pref;
	private CheckBox chkSekitoba;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hidden_settings);
		
		pref = PreferenceManager.getDefaultSharedPreferences(this);
		
		chkSekitoba = (CheckBox)findViewById(R.id.chkForceSekitoba);
		if(pref.getBoolean(getString(R.string.key_force_sekitoba), false)) {
			chkSekitoba.setChecked(true);
		} else {
			chkSekitoba.setChecked(false);
		}
		
		findViewById(R.id.btnOk).setOnClickListener(this);
		findViewById(R.id.btnCancel).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
			case R.id.btnOk :
				Editor editor = pref.edit();
				editor.putBoolean(getString(R.string.key_force_sekitoba), chkSekitoba.isChecked());
				editor.commit();
			case R.id.btnCancel :
				this.finish();
				break;
		}
	}
}
