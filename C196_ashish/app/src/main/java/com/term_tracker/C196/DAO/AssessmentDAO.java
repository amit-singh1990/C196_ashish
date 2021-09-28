package com.term_tracker.C196.DAO;

import com.term_tracker.C196.Entity.AssessmentEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface AssessmentDAO {

    @Query("SELECT * FROM Assessment_Table ORDER BY assessmentId ASC")
    public List<AssessmentEntity> getAllAssessments();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertAssessment(AssessmentEntity assessmentToInsert);

    @Update
    public void updateAssessment(AssessmentEntity assessmentToUpdate);

    @Delete
    public void deleteAssessment(AssessmentEntity assessmentToDelete);

}
