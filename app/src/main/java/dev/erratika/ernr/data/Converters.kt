package dev.erratika.ernr.data

import android.annotation.SuppressLint
import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

class Converters {
    @SuppressLint("NewApi")
    @TypeConverter
    fun fromTimestamp(value: Long?): LocalDateTime? {
        return value?.let { LocalDateTime.ofInstant(Instant.ofEpochSecond(value),TimeZone.getDefault().toZoneId()) }
    }

    @SuppressLint("NewApi")
    @TypeConverter
    fun dateToTimestamp(date: LocalDateTime?): Long? {
        return date?.toEpochSecond(ZoneOffset.UTC)
    }

}