package dev.erratika.ernr

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.Nullable
import java.util.*

class GridAdapter(
    context: Context?,
    private val monthlyDates: List<Date>,
    private val currentDate: Calendar
) :
    ArrayAdapter<Any?>(context!!, R.layout.single_cell_layout) {
    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        val mDate = monthlyDates[position]
        val dateCal = Calendar.getInstance()
        dateCal.time = mDate
        val dayValue = dateCal[Calendar.DAY_OF_MONTH]
        val displayMonth = dateCal[Calendar.MONTH] + 1
        val displayYear = dateCal[Calendar.YEAR]
        val currentMonth = currentDate[Calendar.MONTH] + 1
        val currentYear = currentDate[Calendar.YEAR]
        var view = convertView
        if (view == null) {
            view = mInflater.inflate(R.layout.single_cell_layout, parent, false)
        }
        //TODO REMOVE COLOUR SETTING
        if (displayMonth == currentMonth && displayYear == currentYear) {
            view!!.setBackgroundColor(Color.parseColor("#FF5733"))
        } else {
            view!!.setBackgroundColor(Color.parseColor("#cccccc"))
        }
        //Add day to calendar
        val cellNumber =
            view.findViewById<View>(R.id.calendar_date_id) as TextView
        cellNumber.text = dayValue.toString()

        return view
    }

    override fun getCount(): Int {
        return monthlyDates.size
    }

    @Nullable
    override fun getItem(position: Int): Any? {
        return monthlyDates[position]
    }

    override fun getPosition(item: Any?): Int {
        return monthlyDates.indexOf(item)
    }
    
}
