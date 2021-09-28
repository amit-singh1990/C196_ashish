package com.term_tracker.C196.Database;

import android.app.Application;

import com.term_tracker.C196.DAO.AssessmentDAO;
import com.term_tracker.C196.Entity.CourseEntity;
import com.term_tracker.C196.DAO.CourseDAO;
import com.term_tracker.C196.DAO.TermDAO;
import com.term_tracker.C196.Entity.AssessmentEntity;
import com.term_tracker.C196.Entity.TermEntity;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DBRepository {
    private AssessmentDAO mAssessmentDAO;
    private CourseDAO mCourseDAO;
    private TermDAO mTermDAO;
    private int termId;
    private List<AssessmentEntity> mAllAssessments;
    private List<CourseEntity> mAllCourses;
    private List<TermEntity> mAllTerms;
    private static int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public DBRepository(Application application) {
        DatabaseBuilder db = DatabaseBuilder.getDatabase(application);
        mAssessmentDAO = db.assessmentDAO();
        mCourseDAO = db.courseDAO();
        mTermDAO = db.termDAO();
    }

    public List<AssessmentEntity> getAllAssessments() {
        databaseWriteExecutor.execute(() -> {
            mAllAssessments = mAssessmentDAO.getAllAssessments();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllAssessments;
    }

    public List<CourseEntity> getAllCourses() {
        databaseWriteExecutor.execute(() -> {
            mAllCourses = mCourseDAO.getAllCourses();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllCourses;
    }

    public List<TermEntity> getAllTerms() {
        databaseWriteExecutor.execute(() -> {
            mAllTerms = mTermDAO.getAllTerms();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllTerms;
    }

    public void insert(AssessmentEntity assessmentEntity) {
        databaseWriteExecutor.execute(()-> {
            mAssessmentDAO.insertAssessment(assessmentEntity);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void insert(CourseEntity courseEntity) {
        databaseWriteExecutor.execute(()-> {
            mCourseDAO.insertCourse(courseEntity);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void insert(TermEntity termEntity) {
//        Log.i("titleadded", termEntity.getTermTitle());
//        Log.i("titlestart", termEntity.getTermStart());
//        Log.i("titleend", termEntity.getTermEnd());
//        Log.i("newtermid", String.valueOf(termEntity.getTermId()));
        databaseWriteExecutor.execute(()-> {
            mTermDAO.insertTerm(termEntity);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(AssessmentEntity assessmentEntity) {
        databaseWriteExecutor.execute(()->{
            mAssessmentDAO.updateAssessment(assessmentEntity);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(CourseEntity courseEntity) {
        databaseWriteExecutor.execute(()->{
            mCourseDAO.updateCourse(courseEntity);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(TermEntity termEntity) {
        databaseWriteExecutor.execute(()->{
            mTermDAO.updateTerm(termEntity);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(AssessmentEntity assessmentEntity) {
        databaseWriteExecutor.execute(()->{
            mAssessmentDAO.deleteAssessment(assessmentEntity);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(CourseEntity courseEntity) {
        databaseWriteExecutor.execute(()->{
            mCourseDAO.deleteCourse(courseEntity);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(TermEntity termEntity) {
        databaseWriteExecutor.execute(()->{
            mTermDAO.deleteTerm(termEntity);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
