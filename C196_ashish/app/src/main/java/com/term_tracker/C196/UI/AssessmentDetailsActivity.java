package com.term_tracker.C196.UI;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.term_tracker.C196.Database.DBRepository;
import com.term_tracker.C196.Entity.AssessmentEntity;
import com.term_tracker.C196.HelperClasses.MyReceiverAssessment;
import com.term_tracker.C196.HelperClasses.StringToCalendarConverterClass;
import com.term_tracker.scheduler.R;

import java.util.Calendar;
import java.util.List;

public class AssessmentDetailsActivity extends AppCompatActivity {

    private int passedCourseId;
    private DBRepository repository;
    private int assessmentId;
    private String performanceTitle;
    private String performanceDate;
    private String objectiveTitle;
    private String objectiveDate;
    private List<AssessmentEntity> allAssessments;
    private AssessmentEntity currentAssessment;
    private EditText editPerformanceTitle;
    private EditText editPerformanceDate;
    private EditText editObjectiveTitle;
    private EditText editObjectiveDate;
    private TextView courseIdTextView;
    private int courseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_details);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        courseId = getIntent().getIntExtra("courseId", -1);
        assessmentId = getIntent().getIntExtra("assessmentId", -1);
        repository = new DBRepository(getApplication());
        allAssessments = repository.getAllAssessments();
        for(AssessmentEntity element:allAssessments) {
            if(element.getAssessmentId() == assessmentId) {
                currentAssessment = element;
            }
        }
        performanceTitle = getIntent().getStringExtra("assessmentPerformanceTitle");
        performanceDate = getIntent().getStringExtra("assessmentPerformanceDate");
        objectiveTitle = getIntent().getStringExtra("assessmentObjectiveTitle");
        objectiveDate = getIntent().getStringExtra("assessmentObjectiveDate");
        editPerformanceTitle = findViewById(R.id.editPerformanceTitle);
        editPerformanceDate = findViewById(R.id.editPerformanceDate);
        editObjectiveTitle = findViewById(R.id.editObjectiveTitle);
        editObjectiveDate = findViewById(R.id.editObjectiveDate);
        courseIdTextView = findViewById(R.id.courseIdTextView);
        if (assessmentId != -1) {
            editPerformanceTitle.setText(performanceTitle);
            editPerformanceDate.setText(performanceDate);
            editObjectiveTitle.setText(objectiveTitle);
            editObjectiveDate.setText(objectiveDate);
            courseIdTextView.setText(String.valueOf(courseId));
        }

        //Setting onClick on performance EditText
        editPerformanceDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //openDatePickerDialog();
                Calendar performanceDateCalendar = StringToCalendarConverterClass.stringToCalendar(editPerformanceDate);
                openDatePickerDialogPerformance(performanceDateCalendar);
            }
        });

             editObjectiveDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar objectiveDateCalendar = StringToCalendarConverterClass.stringToCalendar(editObjectiveDate);
                openDatePickerDialogObjective(objectiveDateCalendar);
            }
        });

    }

    public void openDatePickerDialogPerformance(Calendar givenCalendar){

        DatePickerDialog performanceDatePicker = new DatePickerDialog(AssessmentDetailsActivity.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                AssessmentDetailsActivity.this.editPerformanceDate.setText((month + 1) + "/" + day + "/" + year);
            }
        }, givenCalendar.get(Calendar.YEAR), givenCalendar.get(Calendar.MONTH), givenCalendar.get(Calendar.DAY_OF_MONTH));
        performanceDatePicker.show();
    }

    public void openDatePickerDialogObjective(Calendar givenCalendar){

        DatePickerDialog objectiveDatePicker = new DatePickerDialog(AssessmentDetailsActivity.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                AssessmentDetailsActivity.this.editObjectiveDate.setText((month + 1) + "/" + day + "/" + year);
            }
        }, givenCalendar.get(Calendar.YEAR), givenCalendar.get(Calendar.MONTH), givenCalendar.get(Calendar.DAY_OF_MONTH));
        objectiveDatePicker.show();
    }


    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_assessment, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.deleteAssessmentButton) {
                repository.delete(currentAssessment);
                Toast.makeText(getApplicationContext(), "Assessment has been deleted!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(AssessmentDetailsActivity.this, CoursesActivity.class);
                startActivity(intent);
        }
        if(item.getItemId() == R.id.notifyAssessment) {
            Intent intentPerformance = new Intent(AssessmentDetailsActivity.this, MyReceiverAssessment.class);
            intentPerformance.putExtra("assessmentTitle", editPerformanceTitle.getText().toString());


            Long triggerPerformance = StringToCalendarConverterClass.stringToCalendar(editPerformanceDate).getTimeInMillis();
            PendingIntent senderPerformance = PendingIntent.getBroadcast(AssessmentDetailsActivity.this, ++MainActivity.numAlert, intentPerformance, 0);
            AlarmManager alarmManagerPerformance = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            alarmManagerPerformance.set(AlarmManager.RTC_WAKEUP, triggerPerformance, senderPerformance);

            Intent intentObjective = new Intent(AssessmentDetailsActivity.this, MyReceiverAssessment.class);
            intentObjective.putExtra("assessmentTitle", editObjectiveTitle.getText().toString());


            Long triggerObjective = StringToCalendarConverterClass.stringToCalendar(editObjectiveDate).getTimeInMillis();
            PendingIntent senderObjective = PendingIntent.getBroadcast(AssessmentDetailsActivity.this, ++MainActivity.numAlert, intentObjective, 0);
            AlarmManager alarmManagerObjective = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            alarmManagerObjective.set(AlarmManager.RTC_WAKEUP, triggerObjective, senderObjective);

            Toast.makeText(getApplicationContext(), "Notification created for the Objective and Performance due dates!", Toast.LENGTH_LONG).show();

        }
        if(item.getItemId() == android.R.id.home) {
            this.finish();
        }
        return true;
    }

    public void saveAssessment(View view) {

        if(assessmentId == -1) {
            AssessmentEntity newAssessment = new AssessmentEntity(0, editPerformanceTitle.getText().toString(), editPerformanceDate.getText().toString(), editObjectiveTitle.getText().toString(), editObjectiveDate.getText().toString(), courseId);
            repository.insert(newAssessment);

        }
        else {
            AssessmentEntity newAssessment = new AssessmentEntity(assessmentId, editPerformanceTitle.getText().toString(), editPerformanceDate.getText().toString(), editObjectiveTitle.getText().toString(), editObjectiveDate.getText().toString(), courseId);
            repository.update(newAssessment);
        }
        Toast.makeText(getApplicationContext(), "Assessment has been saved!", Toast.LENGTH_LONG).show();
        this.finish();
    }
}