package jp.sumasu.u_s_k;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class UskRetweetActivity extends UskPluginActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super._self = this;
        super.onCreate(savedInstanceState);
    }
	
	@Override
	protected void startIntent(String extraText) {
        Intent twicca = new Intent();
        twicca.setClassName("jp.r246.twicca", "jp.r246.twicca.statuses.Send");
        twicca.setAction(Intent.ACTION_SEND);
        twicca.putExtra(Intent.EXTRA_TEXT, extraText);
        try {
        	this.startActivity(twicca);
        } catch(ActivityNotFoundException e) {
        	e.printStackTrace();
        	Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();
        }
        this.finish();
	}

}
