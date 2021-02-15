package dev.erratika.ernr

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

class CalendarViewModel: ViewModel() {

    val cal : Calendar = Calendar.getInstance(Locale.getDefault())
    val dayOfWeekStarting: MutableLiveData<Int> = MutableLiveData(Calendar.MONDAY)
    private val formatter = SimpleDateFormat("MMMM yyyy", Locale.getDefault())

    fun currentDate(): String {
        return formatter.format(cal.time)
    }



    fun previousMonth(){
        cal.add(Calendar.MONTH, -1)
    }
    fun nextMonth(){
        cal.add(Calendar.MONTH, 1)
    }

    fun getDates():MutableList<Date> {
        val dayValueInCells: MutableList<Date> =
            ArrayList()
        val mCal = cal.clone() as Calendar


        //Set Calendar to the 1st day of current month.
        mCal[Calendar.DAY_OF_MONTH] = 1

        /*
            Day of the week is 1 based starting from Sunday.
            Sunday = 1
            Monday = 2
            ...
            Sat = 7

            Kotlin modulus returns negative on a negative parameter. So use Math.floorMod instead.
         */

        val firstDayOfTheMonth = Math.floorMod(mCal[Calendar.DAY_OF_WEEK] - (1 + dayOfWeekStarting.value!!), 7) + 1

        //Set the current calendar to remaining days of the previous month.
        mCal.add(Calendar.DAY_OF_MONTH, -firstDayOfTheMonth)
        while (dayValueInCells.size < MAX_CALENDAR_COLUMN) {
            dayValueInCells.add(mCal.time)
            mCal.add(Calendar.DAY_OF_MONTH, 1)
        }
        return dayValueInCells
    }
    companion object {

        /*
            Maximum amount of columns required to represent a month.
            For example take the last day of the week being the start of the new month.
            7 days required to represent the previous month.
            31 days required to represent current month.
            (31+7)%7 = 3. 7-3 = 4 Days left to represent next month.
         */
        private const val MAX_CALENDAR_COLUMN = 42
    }

}