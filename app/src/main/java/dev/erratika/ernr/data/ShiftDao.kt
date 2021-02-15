package dev.erratika.ernr.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import java.time.LocalDateTime

/**
 *
 */
@Dao
interface ShiftDao {
    @Query("SELECT  * FROM Shift WHERE startTime > strftime('%s','now') ORDER BY startTime ASC LIMIT 1")
    fun getNextShift():Shift

    @Insert
    fun insertShift(shift: Shift)

    @Insert
    fun insertShifts(shifts: List<Shift>)

    @Query("SELECT * FROM SHIFT WHERE startTime >=")
    fun getShiftsBetween(startDate:LocalDateTime, endDate:LocalDateTime):List<Shift>
}