package com.term_tracker.C196.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.term_tracker.C196.Database.DBRepository;
import com.term_tracker.C196.Entity.AssessmentEntity;
import com.term_tracker.C196.Entity.CourseEntity;
import com.term_tracker.C196.Entity.TermEntity;
import com.term_tracker.scheduler.R;

public class MainActivity extends AppCompatActivity {

    public static int numAlert;
    public static int numAlert2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBRepository repository = new DBRepository(getApplication());
        TermEntity term1 = new TermEntity(1,"FALL", "09/29/2021", "12/24/2021");
        TermEntity term2 = new TermEntity(2,"WINTER", "01/01/2022", "02/027/2022");
        repository.insert(term1);
        repository.insert(term2);
        CourseEntity collegeAlgebra = new CourseEntity(1, "MATH", "09/29/2021", "10/12/2021", "Plan To Take", "MATTHEW", "9514796325", "MATTHEW@WGU.EDU", "WELCOME TO MATH FALL.", 1);
        CourseEntity trigonometry = new CourseEntity(2, "BIOLOGY", "01/11/2022", "01/23/2022", "Completed", "ALLISON", "9093536896", "ALLISON@wgu.edu", "WECLOME TO BIOLOGY FALL.", 1);
        repository.insert(collegeAlgebra);
        repository.insert(trigonometry);
        AssessmentEntity algebraAssessment = new AssessmentEntity(1, "Linear Inequalities", "10/08/2021", "Graph exam", "10/25/2021", 1);
        AssessmentEntity algebraAssessment2 = new AssessmentEntity(2, "ANATOMY", "01/15/2022", "QUIZ", "01/25/2022", 1);
        repository.insert(algebraAssessment);
        repository.insert(algebraAssessment2);
    }

    public void enterTermsListScreen(View view) {
        Intent intent = new Intent(MainActivity.this, TermsActivity.class);
        startActivity(intent);

    }
}