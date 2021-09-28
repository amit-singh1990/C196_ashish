package com.term_tracker.C196.DAO;

import com.term_tracker.C196.Entity.TermEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface TermDAO {

    @Query("SELECT * FROM Term_Table ORDER BY termId ASC")
    public List<TermEntity> getAllTerms();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertTerm(TermEntity termToInsert);

    @Update
    public void updateTerm(TermEntity termToUpdate);

    @Delete
    public void deleteTerm(TermEntity termToDelete);

}
