package dev.erratika.ernr.data

import androidx.room.*

@Entity
data class JobWithShifts(
    @Embedded val job: Job,
    @Relation(
        parentColumn = "id",
        entityColumn = "jobId"
    )
    val shift: Shift
)