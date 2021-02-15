package dev.erratika.ernr.data

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * The Data Access Object for the [Job] class.
 */
@Dao
interface JobDao {
    @Query("SELECT * FROM Job")
    fun getJobs() : LiveData<List<Job>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertJob(job:Job)

    @Delete
    suspend fun deleteJob(job:Job)

}