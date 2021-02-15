package dev.erratika.ernr.data

import androidx.room.Query
import androidx.room.Transaction

interface JobWithShiftDao {
    @Transaction
    @Query("SELECT * FROM Job")
    fun getJobsWithShifts():List<JobWithShifts>

    //Join Shift and job, Get shifts that haven't been paid prior to current date time Multiply job pay rate by shift hours
//    @Query("SELECT SUM() FROM SHIFT WHERE beenPaid AND endTime < datetime('now') AND jobId = :jobId")
//    fun getDuePay(jobId:String)
}