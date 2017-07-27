package wind07.gpacalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import wind07.gpacalculator.BuildConfig;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        TextView lblVersionCode = (TextView) findViewById(R.id.lblVersionCode);
        TextView lblVersionName = (TextView) findViewById(R.id.lblVersionName);
        String versionCode = "Build " + Integer.toString(BuildConfig.VERSION_CODE);
        String versionName = "Version " + BuildConfig.VERSION_NAME;
        lblVersionCode.setText(versionCode);
        lblVersionName.setText(versionName);
    }
}
