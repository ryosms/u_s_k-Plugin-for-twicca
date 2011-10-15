package jp.sumasu.u_s_k;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class HiddenReceiver extends BroadcastReceiver {
	
	private static final String SECRET_CODE_ACTION = "android.provider.Telephony.SECRET_CODE";

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d("u_s_k", "test");
		if(intent.getAction().equals(SECRET_CODE_ACTION)) {
			Intent secretIntent = new Intent(context, HiddenSettingActivity.class);
			secretIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(secretIntent);
		}
	}

}
