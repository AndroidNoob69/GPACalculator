package wind07.gpacalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        String buildID = Integer.toString(BuildConfig.VERSION_CODE);
        String verionNum = BuildConfig.VERSION_NAME;
        TextView lblVersionCode = findViewById(R.id.lblVersionCode);
        TextView lblVersionName = findViewById(R.id.lblVersionName);
        String versionCode = getString(R.string.build_id, buildID);
        String versionName = getString(R.string.version_num, verionNum);
        lblVersionCode.setText(versionCode);
        lblVersionName.setText(versionName);
    }
}
