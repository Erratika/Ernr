package dev.erratika.ernr.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.*

@Entity
data class Shift(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val jobId: Int,
    val beenPaid: Boolean = false
)
