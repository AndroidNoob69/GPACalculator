package wind07.gpacalculator;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import static wind07.gpacalculator.MainActivity.modCount;
import static wind07.gpacalculator.MainActivity.modList;



public class ViewModules extends AppCompatActivity {
    private LinearLayout linearLayout;
    private EditText txtRemoveMod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_modules);
        Intent intent = getIntent();
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        linearLayout = (LinearLayout)findViewById(R.id.linearLayout);

        if (modCount == 0){
            TextView msg = new TextView(this);
            msg.setText("There are no modules added");
            msg.setGravity(Gravity.CENTER);
            linearLayout.addView(msg);
        }

        for (int i = 0; i<modList.size(); i++){
            TextView modCount = new TextView(this);
            TextView modName = new TextView(this);
            TextView credUnits = new TextView(this);
            TextView grade = new TextView(this);
            TextView blank = new TextView(this);
            //modCount.setTextColor(Color.rgb(0,0,0));
            //modName.setTextColor(Color.rgb(0,0,0));
            //grade.setTextColor(Color.rgb(0,0,0));
            //credUnits.setTextColor(Color.rgb(0,0,0));

            modCount.setText("Module " + Integer.toString(i+1));
            modName.setText("Name: " + modList.get(i).modName);
            credUnits.setText("Credit Units: " + Double.toString(modList.get(i).credUnits));
            grade.setText("Grade: " + modList.get(i).grade);
            blank.setText("");
            linearLayout.addView(modCount);
            linearLayout.addView(modName);
            linearLayout.addView(credUnits);
            linearLayout.addView(grade);
            linearLayout.addView(blank);
        }
    }

    public void removeModule(View view){
        int validation = 1;
        int removeMod = 0;
        txtRemoveMod = (EditText)findViewById(R.id.txtRemoveMod);
        if (txtRemoveMod == null || txtRemoveMod.length() == 0){
            txtRemoveMod.setError("Module Name CANNOT be empty");
            return;
        }
        while (true) {
            try {
                removeMod = (Integer.parseInt(txtRemoveMod.getText().toString())) - 1;
                break;
            } catch (NumberFormatException nfe) {
                txtRemoveMod.setError("What a genius! You take so many modules?");
                return;
            }
        }
        if (removeMod < 0 || removeMod >= modList.size()){
            txtRemoveMod.setError("Invalid ID");
        }
        else {
            modList.remove(removeMod);
            modCount -= 1;
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
        }
    }
}
