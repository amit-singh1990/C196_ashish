package com.term_tracker.C196.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "Assessment_Table")
public class AssessmentEntity {

    @PrimaryKey(autoGenerate = true)
    private int assessmentId;
    private String performanceTitle;
    private String performanceDate;
    private String objectiveTitle;
    private String objectiveDate;
    private int courseId;

    public AssessmentEntity(int assessmentId, String performanceTitle, String performanceDate, String objectiveTitle, String objectiveDate, int courseId) {
        this.assessmentId = assessmentId;
        this.performanceTitle = performanceTitle;
        this.performanceDate = performanceDate;
        this.objectiveTitle = objectiveTitle;
        this.objectiveDate = objectiveDate;
        this.courseId = courseId;
    }

    public int getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(int assessmentId) {
        this.assessmentId = assessmentId;
    }

    public String getPerformanceTitle() {
        return performanceTitle;
    }

    public void setPerformanceTitle(String performanceTitle) {
        this.performanceTitle = performanceTitle;
    }

    public String getPerformanceDate() {
        return performanceDate;
    }

    public void setPerformanceDate(String performanceDate) {
        this.performanceDate = performanceDate;
    }




    public String getObjectiveTitle() {
        return objectiveTitle;
    }

    public void setObjectiveTitle(String objectiveTitle) {
        this.objectiveTitle = objectiveTitle;
    }

    public String getObjectiveDate() {
        return objectiveDate;
    }

    public void setObjectiveDate(String objectiveDate) {
        this.objectiveDate = objectiveDate;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
