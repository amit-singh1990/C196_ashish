package com.term_tracker.C196.Database;

import android.content.Context;

import com.term_tracker.C196.DAO.AssessmentDAO;
import com.term_tracker.C196.DAO.CourseDAO;
import com.term_tracker.C196.DAO.TermDAO;
import com.term_tracker.C196.Entity.AssessmentEntity;
import com.term_tracker.C196.Entity.CourseEntity;
import com.term_tracker.C196.Entity.TermEntity;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {AssessmentEntity.class, CourseEntity.class, TermEntity.class}, version = 2, exportSchema = false)
public abstract class DatabaseBuilder extends RoomDatabase {
    public abstract TermDAO termDAO();
    public abstract CourseDAO courseDAO();
    public abstract AssessmentDAO assessmentDAO();

    private static volatile DatabaseBuilder INSTANCE;

    static DatabaseBuilder getDatabase(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DatabaseBuilder.class, "TermTracker.db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
