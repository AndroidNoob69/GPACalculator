package wind07.gpacalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.CompoundButton;
import android.widget.Switch;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);

        Switch swDarkMode = (Switch) findViewById(R.id.switchDarkMode);

        String darkTheme = settings.getString("dark_theme", "");

        if (darkTheme.equals("true")) {
            swDarkMode.setChecked(true);
        }
        else if (darkTheme.equals("false")) {
            swDarkMode.setChecked(false);
        }
        else {
            int currentNightMode = getResources().getConfiguration().uiMode
                    & Configuration.UI_MODE_NIGHT_MASK;
            switch (currentNightMode) {
                case Configuration.UI_MODE_NIGHT_NO:
                    swDarkMode.setChecked(false);
                    break;
                case Configuration.UI_MODE_NIGHT_YES:
                    swDarkMode.setChecked(true);
                    break;
                case Configuration.UI_MODE_NIGHT_UNDEFINED:
                    swDarkMode.setChecked(false);
                    break;
            }
        }

        swDarkMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String darkTheme = "true";
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("dark_theme", darkTheme);
                    editor.apply();
                } else {
                    String darkTheme = "false";
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("dark_theme", darkTheme);
                    editor.apply();
                }
            }
        });
    }
}
