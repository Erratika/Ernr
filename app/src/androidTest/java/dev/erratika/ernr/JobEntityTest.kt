package dev.erratika.ernr

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.erratika.ernr.data.AppDatabase
import dev.erratika.ernr.data.JobDao
import dev.erratika.ernr.data.ShiftDao
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class JobEntityTest {
    private lateinit var shiftDao: ShiftDao
    private lateinit var jobDao: JobDao
    private lateinit var db: AppDatabase
    @Before
    fun createDb(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java).build()
        jobDao = db.jobDao()
        shiftDao = db.shiftDao()
    }
    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun jobInsertTest() {

    }

}