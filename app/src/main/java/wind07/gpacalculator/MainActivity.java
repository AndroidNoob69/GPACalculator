package wind07.gpacalculator;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

// Define all crap
public class MainActivity extends AppCompatActivity {
    public static int modCount = 0;
    public static List<Module> modList = new ArrayList<>();
    private EditText txtModName;
    private EditText txtCredUnits;
    private TextView lblModCount;
    private TextView lblAddMod;
    private Spinner ddlGrade;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        String darkTheme = settings.getString("dark_theme", "");
        if (darkTheme.isEmpty()) {
            return;
        }
        else if (darkTheme.equals("true")){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        txtModName = (EditText)findViewById(R.id.txtModName);
        txtCredUnits = (EditText)findViewById(R.id.txtCredUnits);
        lblModCount = (TextView)findViewById(R.id.lblModCount);
        lblAddMod = (TextView)findViewById((R.id.lblAddMod));
        ddlGrade = (Spinner) findViewById(R.id.ddlGrade);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.grade_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ddlGrade.setAdapter(adapter);
        if(modList.isEmpty()) {
            modCount = 0;
        }
        lblModCount.setText(String.format(Locale.ENGLISH,"%d", modCount));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about: {
                Intent intent = new Intent(this, About.class);
                startActivity(intent);
                break;
            }
            case R.id.action_settings: {
                Intent intent = new Intent(this, Settings.class);
                startActivity(intent);
                break;
            }
            // case blocks for other MenuItems (if any)
        }
        return false;
    }
    public void onAddModuleClick(View view){
        String modName = txtModName.getText().toString().trim();
        double credUnits = 0;
        int validation = 1;
        if (modName.length() == 0 ){
            txtModName.setError(getString(R.string.empty_module_name));
            validation = 0;
        }
        try {
            credUnits = Double.parseDouble(txtCredUnits.getText().toString());
        }
        catch(NumberFormatException nfe){
            txtCredUnits.setError(getString(R.string.empty_module_credits));
            validation = 0;
        }
        if(credUnits < 1 || credUnits>35){
            txtCredUnits.setError(getString(R.string.invalid_module_credits));
            validation = 0;
        }
        if(validation == 0){
            return;
        }
        else {
            String grade = ddlGrade.getSelectedItem().toString();
            Module newModule = new Module(modName, credUnits, grade);
            modList.add(newModule);
            modCount += 1;
            lblModCount.setText(String.format(Locale.ENGLISH,"%d",modCount));
            lblAddMod.setText(getString(R.string.module_added, modName));
            lblAddMod.setTextColor(Color.rgb(50,255,50));
            txtModName.setText("");
            txtCredUnits.setText("");
        }
    }
    public void viewModules(View view){
        Intent intent = new Intent(this, ViewModules.class);
        startActivity(intent);
    }

    public void computeGPA(View view){
        if (modCount == 0){
            Context context = getApplicationContext();
            CharSequence text = getString(R.string.no_modules_added);
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else {
            Intent intent = new Intent(this, GPA.class);
            startActivity(intent);
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        lblModCount.setText(String.format(Locale.ENGLISH,"%d",modCount));
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder exitDialog = new AlertDialog.Builder(this);
        exitDialog.setMessage(getString(R.string.exit_confirmation));
        exitDialog.setCancelable(true);
        exitDialog.setPositiveButton(
                getString(R.string.yes),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        exitApp();
                    }
                });
        exitDialog.setNegativeButton(
                getString(R.string.no),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog exitAlert = exitDialog.create();
        exitAlert.show();
    }

    public void exitApp(){
        //Clean up code
        modList.clear();
        modCount=0;
        finish();
    }
}
