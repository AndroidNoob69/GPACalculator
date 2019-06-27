package wind07.gpacalculator;

import android.content.Intent;
import android.content.pm.ActivityInfo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Locale;

import static wind07.gpacalculator.MainActivity.modCount;
import static wind07.gpacalculator.MainActivity.modList;



public class ViewModules extends AppCompatActivity {
    LinearLayout linearLayout;
    EditText txtRemoveMod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_modules);
        Intent intent = getIntent();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        populateModules();
    }

    public void populateModules(){
        linearLayout = (LinearLayout)findViewById(R.id.linearLayout);

        if (modCount == 0){
            TextView msg = new TextView(this);
            msg.setText(getString(R.string.no_modules_found));
            msg.setGravity(Gravity.CENTER);
            linearLayout.addView(msg);
        }

        for (int i = 0; i<modList.size(); i++){
            TextView modCount = new TextView(this);
            TextView modName = new TextView(this);
            TextView credUnits = new TextView(this);
            TextView grade = new TextView(this);
            TextView blank = new TextView(this);

            String dispModID = String.format(Locale.ENGLISH,"%d",i+1);
            String dispModName = modList.get(i).modName;
            String dispModCred = String.format(Locale.ENGLISH,"%.1f",modList.get(i).credUnits);
            String dispGrade = modList.get(i).grade;

            modCount.setText(getString(R.string.module_id, dispModID));
            modName.setText(getString(R.string.module_name, dispModName));
            credUnits.setText(getString(R.string.module_credits, dispModCred));
            grade.setText(getString(R.string.module_grade, dispGrade));
            blank.setText("");
            linearLayout.addView(modCount);
            linearLayout.addView(modName);
            linearLayout.addView(credUnits);
            linearLayout.addView(grade);
            linearLayout.addView(blank);
        }
    }

    public void removeModule(View view){
        int removeMod = 0;
        txtRemoveMod = (EditText)findViewById(R.id.txtRemoveMod);
        if (txtRemoveMod.length() == 0){
            txtRemoveMod.setError(getString(R.string.empty_module_id));
            return;
        }
        try {
            removeMod = (Integer.parseInt(txtRemoveMod.getText().toString())) - 1;
            }
        catch (NumberFormatException nfe) {
                txtRemoveMod.setError(getString(R.string.invalid_module_id_nfe));
                return;
            }

        if (removeMod < 0 || removeMod >= modList.size()){
            txtRemoveMod.setError(getString(R.string.invalid_module_id));
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
