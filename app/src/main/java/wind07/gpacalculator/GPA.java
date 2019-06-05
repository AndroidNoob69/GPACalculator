package wind07.gpacalculator;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Locale;

import static wind07.gpacalculator.MainActivity.modList;

public class GPA extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpa);
        Intent intent = getIntent();
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        TextView lblGPA = (TextView) findViewById(R.id.lblGPA);

        double gradepoint = 0.0;
        double sumcredxgradepoint = 0.0;
        double sumcreditpoint = 0.0;
        double gpa = 0.0;

        for (int i = 0 ; i < modList.size() ; i++){
            if (modList.get(i).grade.equals("AD/A+/A"))
                gradepoint = 4;
            else if (modList.get(i).grade.equals("B+"))
                gradepoint = 3.5;
            else if (modList.get(i).grade.equals("B"))
                gradepoint = 3;
            else if (modList.get(i).grade.equals("C+"))
                gradepoint = 2.5;
            else if (modList.get(i).grade.equals("C"))
                gradepoint = 2;
            else if (modList.get(i).grade.equals("D+"))
                gradepoint = 1.5;
            else if (modList.get(i).grade.equals("D"))
                gradepoint = 1;
            else if (modList.get(i).grade.equals("F"))
                gradepoint = 0;
            sumcredxgradepoint += modList.get(i).credUnits * gradepoint;
            sumcreditpoint += modList.get(i).credUnits;
        }
        gpa = sumcredxgradepoint/sumcreditpoint;
        String gpaRounded = String.format(Locale.ENGLISH,"%.2f", gpa);
        lblGPA.setText(gpaRounded);

    }
}
