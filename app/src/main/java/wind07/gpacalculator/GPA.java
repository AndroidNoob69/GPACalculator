package wind07.gpacalculator;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Locale;

import static wind07.gpacalculator.MainActivity.modList;

public class GPA extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpa);
        Intent intent = getIntent();

        TextView lblGPA = (TextView) findViewById(R.id.lblGPA);

        double gradepoint = 0.0;
        double sumcredxgradepoint = 0.0;
        double sumcreditpoint = 0.0;
        double gpa = 0.0;

        for (int i = 0 ; i < modList.size() ; i++){

            switch (modList.get(i).grade){
                case "AD/A+/A":
                    gradepoint = 4;
                    break;
                case "B+":
                    gradepoint = 3.5;
                    break;
                case "B":
                    gradepoint = 3;
                    break;
                case "C+":
                    gradepoint = 2.5;
                    break;
                case "C":
                    gradepoint = 2;
                    break;
                case "D+":
                    gradepoint = 1.5;
                    break;
                case "F":
                    gradepoint = 0;
                    break;
            }
            sumcredxgradepoint += modList.get(i).credUnits * gradepoint;
            sumcreditpoint += modList.get(i).credUnits;
        }
        gpa = sumcredxgradepoint/sumcreditpoint;
        String gpaRounded = String.format(Locale.ENGLISH,"%.2f", gpa);
        lblGPA.setText(gpaRounded);

    }
}
