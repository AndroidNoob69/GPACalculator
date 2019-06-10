package wind07.gpacalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.TextView;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        String buildID = Integer.toString(BuildConfig.VERSION_CODE);
        String verionNum = BuildConfig.VERSION_NAME;;
        TextView lblVersionCode = (TextView) findViewById(R.id.lblVersionCode);
        TextView lblVersionName = (TextView) findViewById(R.id.lblVersionName);
        String versionCode = getString(R.string.build_id, buildID);
        String versionName = getString(R.string.version_num, verionNum);
        lblVersionCode.setText(versionCode);
        lblVersionName.setText(versionName);
    }
}
