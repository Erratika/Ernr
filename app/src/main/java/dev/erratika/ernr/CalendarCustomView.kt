package dev.erratika.ernr

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.GridView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class CalendarCustomView : LinearLayout {
    private lateinit var previousButton: ImageView
    private lateinit var nextButton: ImageView
    private lateinit var currentDate: TextView
    private lateinit var calendarGridView: GridView
    private lateinit var dayDisplay: LinearLayout

    private var viewModel: CalendarViewModel = CalendarViewModel()

    private var mAdapter: GridAdapter? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init()
    }


    private fun init() {
        initializeUILayout()
        drawDayDisplay()
        setUpCalendarAdapter()
        setPreviousButtonClickEvent()
        setNextButtonClickEvent()
        setGridCellClickEvents()
    }


    private fun initializeUILayout() {
        val inflater =
            context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.calendar_layout, this)
        previousButton =
            view.findViewById<View>(R.id.previous_month) as ImageView
        nextButton =
            view.findViewById<View>(R.id.next_month) as ImageView
        currentDate = view.findViewById<View>(R.id.display_current_date) as TextView
        calendarGridView = view.findViewById<View>(R.id.calendar_grid) as GridView
        dayDisplay = view.findViewById<View>(R.id.day_display) as LinearLayout
    }

    private fun setPreviousButtonClickEvent() {
        previousButton.setOnClickListener {
            //TODO CHANGE ON THE MODEL
            viewModel.previousMonth()
            setUpCalendarAdapter()
        }
    }

    private fun setNextButtonClickEvent() {
        nextButton.setOnClickListener {
            //TODO CHANGE ON THE MODEL
            viewModel.nextMonth()
            setUpCalendarAdapter()
        }
    }

    private fun setGridCellClickEvents() {
        calendarGridView.onItemClickListener =
            OnItemClickListener { parent, view, position, id ->
                TODO("Open new Fragment related to date.")
            }
    }

    private fun setUpCalendarAdapter() {
        val dayValueInCells = viewModel.getDates()
        currentDate.text = viewModel.currentDate()
        mAdapter = GridAdapter(context, dayValueInCells, viewModel.cal)
        calendarGridView.adapter = mAdapter
    }

    private fun drawDayDisplay() {
        val firstDayOfTheWeek = viewModel.dayOfWeekStarting.value
        var dayOfTheWeek = firstDayOfTheWeek
        do {
            val dayTextView = TextView(context)

            //Set Properties
            val lp = LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT, 1f
            )
            dayTextView.textAlignment = View.TEXT_ALIGNMENT_CENTER
            dayTextView.layoutParams = lp
            dayTextView.text = when (dayOfTheWeek) {
                1 -> "Sun"
                2 -> "Mon"
                3 -> "Tue"
                4 -> "Wed"
                5 -> "Thu"
                6 -> "Fri"
                7 -> "Sat"
                else -> throw Error()
            }
            dayDisplay.addView(dayTextView)
            dayOfTheWeek = dayOfTheWeek.rem(7) + 1
        } while (dayOfTheWeek != firstDayOfTheWeek)


    }
}




