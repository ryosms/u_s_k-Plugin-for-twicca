package jp.sumasu.u_s_k;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class UskPluginActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Intent intent = getIntent();
        String tweet = intent.getStringExtra(Intent.EXTRA_TEXT);
        String screenName = intent.getStringExtra("user_screen_name");

        Intent twicca = new Intent();
        twicca.setClassName("jp.r246.twicca", "jp.r246.twicca.statuses.Send");
        twicca.setAction(Intent.ACTION_SEND);
        twicca.putExtra(Intent.EXTRA_TEXT, String.format("#u_s_k RT @%s: %s", screenName, tweet));
        try {
        	this.startActivity(twicca);
        } catch(ActivityNotFoundException e) {
        	e.printStackTrace();
        	Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();
        }
        this.finish();
    }
}