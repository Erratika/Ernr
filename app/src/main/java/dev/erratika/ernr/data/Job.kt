package dev.erratika.ernr.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Job(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val jobName:String,
    val standardPay:Float) {

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
}