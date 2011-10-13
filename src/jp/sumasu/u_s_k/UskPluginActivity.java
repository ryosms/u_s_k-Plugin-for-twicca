package jp.sumasu.u_s_k;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class UskPluginActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        ArrayList<String> items = new ArrayList<String>();
        if(pref.getBoolean("use_usk", true)) items.add("#u_s_k");
        if(pref.getBoolean("use_sekitoba", true)) items.add("#sekitoba");
        if(pref.getBoolean("use_hidaka", true)) items.add("#ひだか");
        if(items.size() == 0) {
        	Toast.makeText(this, "no options. plz check settings", Toast.LENGTH_SHORT).show();
        	this.finish();
        	return;
        } else if(items.size() == 1) {
        	goTweet(items.get(0));
        	return;
        }
        final String[] selections = (String[])items.toArray(new String[0]);
        new AlertDialog.Builder(this).setTitle("").setItems(selections, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				goTweet(selections[which].toString());
			}
		}).show();
    }
    
    private void goTweet(String prefix) {
        Intent intent = getIntent();
        String tweet = intent.getStringExtra(Intent.EXTRA_TEXT);
        String screenName = intent.getStringExtra("user_screen_name");

        Intent twicca = new Intent();
        twicca.setClassName("jp.r246.twicca", "jp.r246.twicca.statuses.Send");
        twicca.setAction(Intent.ACTION_SEND);
        twicca.putExtra(Intent.EXTRA_TEXT, String.format("%s RT @%s: %s", prefix, screenName, tweet));
        try {
        	this.startActivity(twicca);
        } catch(ActivityNotFoundException e) {
        	e.printStackTrace();
        	Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();
        }
        this.finish();
    }
}