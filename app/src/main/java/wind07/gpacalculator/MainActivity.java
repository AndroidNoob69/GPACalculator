package wind07.gpacalculator;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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
        lblModCount.setText(Integer.toString(modCount));
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
            // case blocks for other MenuItems (if any)
        }
        return false;
    }
    public void onAddModuleClick(View view){
        String modName = txtModName.getText().toString().trim();
        double credUnits = 0;
        int validation = 1;
        if (modName == null || modName.length() == 0 ){
            txtModName.setError("Module Name CANNOT be empty");
            validation = 0;
        }
        try {
            credUnits = Double.parseDouble(txtCredUnits.getText().toString());
        }
        catch(NumberFormatException nfe){
            txtCredUnits.setError("Credit Units CANNOT be empty");
            validation = 0;
        }
        if(credUnits < 1 || credUnits>35){
            txtCredUnits.setError("Credit Units is INVALID! Please check");
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
            lblModCount.setText(Integer.toString(modCount));
            lblAddMod.setText("Module " + modName + " has been successfully added!");
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
            CharSequence text = "You have not added any modules!";
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
        lblModCount.setText(Integer.toString(modCount));
    }
    @Override
    public void onBackPressed() {
        //Clean up code
        modList.clear();
        modCount=0;
        finish();
    }
}
