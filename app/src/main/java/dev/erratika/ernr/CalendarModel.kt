package dev.erratika.ernr
import java.text.SimpleDateFormat
import java.util.*

class CalendarModel{
    private val cal : Calendar = Calendar.getInstance(Locale.getDefault())
    private val dayOfWeekStarting: Int = Calendar.SUNDAY

    private val formatter =
        SimpleDateFormat("MMMM yyyy", Locale.getDefault())

    fun previousMonth(){
        cal.add(Calendar.MONTH, -1)
    }
    fun nextMonth(){
        cal.add(Calendar.MONTH, 1)
    }

    fun getDates() {
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

        val firstDayOfTheMonth = Math.floorMod(mCal[Calendar.DAY_OF_WEEK] - 3, 7) + 1

        //Set the current calendar to remaining days of the previous month.
        mCal.add(Calendar.DAY_OF_MONTH, -firstDayOfTheMonth)

        //Loop through each day in calendar
        while (dayValueInCells.size < MAX_CALENDAR_COLUMN) {
            dayValueInCells.add(mCal.time)
            mCal.add(Calendar.DAY_OF_MONTH, 1)
        }
    }


    companion object {
        /*
        Maximum amount of columns required to represent a month in a 7 column arrangement.
        For example take the last day of the week being the start of the new month.
        6 days required to represent the previous month.
        31 days required to represent current month.
        (31+6)%7 = 2. 7-2 = 5 Days left to represent next month.
        31+6+5 = 42
     */
        private const val MAX_CALENDAR_COLUMN = 42
    }
}