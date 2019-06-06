package wind07.gpacalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

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
