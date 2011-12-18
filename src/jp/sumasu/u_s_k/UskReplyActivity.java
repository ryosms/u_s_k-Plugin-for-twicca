package jp.sumasu.u_s_k;

import java.net.URLEncoder;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class UskReplyActivity extends UskPluginActivity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super._self = this;
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void startIntent(String extraText) {
		Intent myIntent = getIntent();
		String id = myIntent.getStringExtra("id");
		
		String url = String.format("https://twitter.com/intent/tweet?text=%s&in_reply_to=%s"
									, URLEncoder.encode(extraText), id);
		Uri uri = Uri.parse(url);
		
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_VIEW);
		intent.setData(uri);
		try {
			this.startActivity(intent);
		} catch(ActivityNotFoundException e) {
		}
		this.finish();
	}

}
