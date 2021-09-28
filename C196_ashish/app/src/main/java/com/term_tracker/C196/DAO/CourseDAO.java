package com.term_tracker.C196.DAO;

import com.term_tracker.C196.Entity.CourseEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface CourseDAO {

    @Query("SELECT * FROM Course_Table ORDER BY courseId ASC")
    public List<CourseEntity> getAllCourses();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertCourse(CourseEntity courseToInsert);

    @Update
    public void updateCourse(CourseEntity courseToUpdate);

    @Delete
    public void deleteCourse(CourseEntity courseToDelete);

}
