package com.term_tracker.C196.UI;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.term_tracker.C196.Database.DBRepository;
import com.term_tracker.C196.Entity.AssessmentEntity;
import com.term_tracker.C196.Entity.CourseEntity;
import com.term_tracker.C196.HelperClasses.MyReceiverEndCourse;
import com.term_tracker.C196.HelperClasses.MyReceiverStartCourse;
import com.term_tracker.C196.HelperClasses.StringToCalendarConverterClass;
import com.term_tracker.scheduler.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CourseDetailsActivity extends AppCompatActivity {
    private DBRepository repository;
    private int passedCourseId;
    private static int numAlert;
    private String courseTitle;
    private String startDate;
    private String endDate;
    private String courseStatus;
    private String instructorName;
    private String instructorPhone;
    private String instructorEmail;
    private String courseNotes;
    private int termId;
    private EditText editCourseTitle;
    private EditText editStartDate;
    private EditText editEndDate;
    private EditText editInstructorName;
    private EditText editInstructorPhone;
    private EditText editInstructorEmail;
    private EditText editCourseNotes;
    private List<CourseEntity> allCourses;
    private CourseEntity currentCourse;
    private int newTermId;
    private RecyclerView assignmentRecyclerView;
    public static int numAssessments;
    private FloatingActionButton addAssessmentFloatingButton;
    private Spinner statusSpinner;
    private CourseAdapter courseAdapter;
    private AssessmentAdapter assessmentAdapter;
    private int Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        passedCourseId = getIntent().getIntExtra("courseId", -1);
        repository = new DBRepository(getApplication());
        allCourses = repository.getAllCourses();
        for(CourseEntity element:allCourses) {
            if(element.getCourseId() == passedCourseId) {
                currentCourse = element;
            }
        }

        courseTitle = getIntent().getStringExtra("courseTitle");
        startDate = getIntent().getStringExtra("startDate");
        endDate = getIntent().getStringExtra("endDate");
        courseStatus = getIntent().getStringExtra("courseStatus");
        instructorName = getIntent().getStringExtra("instructorName");
        instructorPhone = getIntent().getStringExtra("instructorPhone");
        instructorEmail = getIntent().getStringExtra("instructorEmail");
        courseNotes = getIntent().getStringExtra("courseNotes");
        termId = getIntent().getIntExtra("termId", -1);

        newTermId = getIntent().getIntExtra("newTermId", -1);

        editCourseTitle = findViewById(R.id.editCourseTitle);
        editStartDate = findViewById(R.id.editCourseStart);
        editEndDate = findViewById(R.id.editCourseEnd);
        editInstructorName = findViewById(R.id.editInstructorName);
        editInstructorPhone = findViewById(R.id.editInstructorPhone);
        editInstructorEmail = findViewById(R.id.editInstructorEmail);
        editCourseNotes = findViewById(R.id.editCourseNotes);
        addAssessmentFloatingButton = findViewById(R.id.addAssessmentFloatingButton);
        addAssessmentFloatingButton.setEnabled(false);
        statusSpinner = findViewById(R.id.statusSpinner);
        String[] courseStatusArray = {"In Progress", "Completed", "Dropped", "Plan To Take"};
        ArrayAdapter<String> statusAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, courseStatusArray);
        statusSpinner.setAdapter(statusAdapter);


        if(passedCourseId != -1) {
            editCourseTitle.setText(courseTitle);
            editStartDate.setText(startDate);
            editEndDate.setText(endDate);
            int statusPosition = statusAdapter.getPosition(courseStatus);
            statusSpinner.setSelection(statusPosition);
            editInstructorName.setText(instructorName);
            editInstructorPhone.setText(instructorPhone);
            editInstructorEmail.setText(instructorEmail);
            editCourseNotes.setText(courseNotes);
            addAssessmentFloatingButton.setEnabled(true);
        }
        assignmentRecyclerView = findViewById(R.id.assignmentsRecyclerView);

        assessmentAdapter= new AssessmentAdapter(this);

        assignmentRecyclerView.setAdapter(assessmentAdapter);
        assignmentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<AssessmentEntity> filteredAssessments = new ArrayList<>();
        for(AssessmentEntity element:repository.getAllAssessments()) {
            if(element.getCourseId() == passedCourseId) {
                filteredAssessments.add(element);
            }
        }
        numAssessments = filteredAssessments.size();
        assessmentAdapter.setAssessments(filteredAssessments);


        editStartDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Calendar startCourseCalendar = StringToCalendarConverterClass.stringToCalendar(editStartDate);
                openDatePickerDialogStartCourse(startCourseCalendar);
            }
        });

        editEndDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Calendar endCourseCalendar = StringToCalendarConverterClass.stringToCalendar(editEndDate);
                openDatePickerDialogEndCourse(endCourseCalendar);
            }
        });
    }


    public void openDatePickerDialogStartCourse(Calendar givenCalendar){

        DatePickerDialog startDateDialog = new DatePickerDialog(CourseDetailsActivity.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                CourseDetailsActivity.this.editStartDate.setText((month + 1) + "/" + day + "/" + year);
            }
        }, givenCalendar.get(Calendar.YEAR), givenCalendar.get(Calendar.MONTH), givenCalendar.get(Calendar.DAY_OF_MONTH));
        startDateDialog.show();
    }

    public void openDatePickerDialogEndCourse(Calendar givenCalendar){

        DatePickerDialog endDateDialog = new DatePickerDialog(CourseDetailsActivity.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                CourseDetailsActivity.this.editEndDate.setText((month + 1) + "/" + day + "/" + year);
            }
        }, givenCalendar.get(Calendar.YEAR), givenCalendar.get(Calendar.MONTH), givenCalendar.get(Calendar.DAY_OF_MONTH));
        endDateDialog.show();
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_course, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.refreshAssessmentButton) {
            refreshAssignments();
        }
        if(item.getItemId() == R.id.courseSharingSMS) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, editCourseNotes.getText().toString());
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
        }
        if(item.getItemId() == R.id.deleteCourseButton) {
            if(numAssessments == 0) {
                repository.delete(currentCourse);
                Toast.makeText(getApplicationContext(), "Course has been deleted!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CourseDetailsActivity.this, CoursesActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Can't Delete Course. Please delete all its assignments first.", Toast.LENGTH_LONG).show();
            }
        }
        if(item.getItemId() == R.id.notifyCourse) {
             Intent intent = new Intent(CourseDetailsActivity.this, MyReceiverStartCourse.class);
            intent.putExtra("courseTitle", editCourseTitle.getText().toString());

            Long triggerStart = StringToCalendarConverterClass.stringToCalendar(editStartDate).getTimeInMillis();
            PendingIntent senderStart = PendingIntent.getBroadcast(CourseDetailsActivity.this, ++MainActivity.numAlert, intent, 0);
            AlarmManager alarmManagerStart = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            alarmManagerStart.set(AlarmManager.RTC_WAKEUP, triggerStart, senderStart);

            Intent intent2 = new Intent(CourseDetailsActivity.this, MyReceiverEndCourse.class);
            intent2.putExtra("courseTitle", editCourseTitle.getText().toString());
            Long triggerEnd = StringToCalendarConverterClass.stringToCalendar(editEndDate).getTimeInMillis();
            PendingIntent senderEnd = PendingIntent.getBroadcast(CourseDetailsActivity.this, ++MainActivity.numAlert, intent2, 0);
            AlarmManager alarmManagerEnd = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            alarmManagerEnd.set(AlarmManager.RTC_WAKEUP, triggerEnd, senderEnd);
            Toast.makeText(getApplicationContext(), "Notification created for the start and end dates for this course!", Toast.LENGTH_LONG).show();
        }
        if(item.getItemId() == android.R.id.home) {
            this.finish();
        }
        return true;
    }

    public void saveCourse(View view) {
        if(passedCourseId == -1) {
            CourseEntity newCourse = new CourseEntity(0, editCourseTitle.getText().toString(), editStartDate.getText().toString(), editEndDate.getText().toString(), statusSpinner.getSelectedItem().toString(), editInstructorName.getText().toString(), editInstructorPhone.getText().toString(), editInstructorEmail.getText().toString(), editCourseNotes.getText().toString(), newTermId);

            repository.insert(newCourse);

        }
        else {
            CourseEntity newCourse = new CourseEntity(passedCourseId, editCourseTitle.getText().toString(), editStartDate.getText().toString(), editEndDate.getText().toString(), statusSpinner.getSelectedItem().toString(), editInstructorName.getText().toString(), editInstructorPhone.getText().toString(), editInstructorEmail.getText().toString(), editCourseNotes.getText().toString(), termId);
            repository.update(newCourse);
        }
        Toast.makeText(getApplicationContext(), "Course has been saved!", Toast.LENGTH_LONG).show();

        this.finish();

    }

    public void refreshAssignments() {
        assignmentRecyclerView = findViewById(R.id.assignmentsRecyclerView);

        assessmentAdapter= new AssessmentAdapter(this);

        assignmentRecyclerView.setAdapter(assessmentAdapter);
        assignmentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<AssessmentEntity> filteredAssessments = new ArrayList<>();
        for(AssessmentEntity element:repository.getAllAssessments()) {
            if(element.getCourseId() == passedCourseId) {
                filteredAssessments.add(element);
            }
        }
        numAssessments = filteredAssessments.size();
        assessmentAdapter.setAssessments(filteredAssessments);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        refreshAssignments();
    }

    public void assessmentsAddScreen(View view) {
        Intent intent = new Intent(CourseDetailsActivity.this, AssessmentDetailsActivity.class);
        intent.putExtra("courseId", passedCourseId);
        startActivity(intent);
    }
}