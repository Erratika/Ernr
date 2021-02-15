package dev.erratika.ernr

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.erratika.ernr.data.*
import junit.framework.TestCase.assertNull
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.Test
import java.io.IOException
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

@RunWith(AndroidJUnit4::class)
class ShiftEntityTest {
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
    @Test
    @Throws(Exception::class)
    fun testGetNextShiftValid(){
        val now = LocalDateTime.now().withNano(0)

        val beforeShift = Shift(1, now.minus(1,ChronoUnit.DAYS),now.minus(1,ChronoUnit.DAYS).plus(8,ChronoUnit.HOURS),1)
        val nextShift = Shift(2, now.plus(1,ChronoUnit.DAYS),now.plus(1,ChronoUnit.DAYS).plus(8,ChronoUnit.HOURS),1)
        val afterShift = Shift(3, now.plus(2,ChronoUnit.DAYS),now.plus(2,ChronoUnit.DAYS).plus(8,ChronoUnit.HOURS),1)

        shiftDao.insertShifts(listOf(beforeShift,nextShift,afterShift))

        val result = shiftDao.getNextShift()
        assert(result == nextShift)
    }

    /**
     *
     */
    @Test
    @Throws(Exception::class)
    fun testGetNextShiftNone(){
        val now = LocalDateTime.now().withNano(0)

        val beforeShift = Shift(1, now.minus(1,ChronoUnit.DAYS),now.minus(1,ChronoUnit.DAYS).plus(8,ChronoUnit.HOURS),1)

        shiftDao.insertShift(beforeShift)

        val result = shiftDao.getNextShift()
        assertNull(result)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }
}