package dev.erratika.ernr.ui.jobs

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import dev.erratika.ernr.data.AppDatabase
import dev.erratika.ernr.data.Job
import dev.erratika.ernr.data.JobRepository

class JobListViewModel(application: Application) : AndroidViewModel(application) {
    // TODO: Implement the ViewModel
    private val jobRepository:JobRepository

    val jobs: LiveData<List<Job>>

    init {

        val jobDao = AppDatabase.getDatabase(application).jobDao()
        jobRepository = JobRepository(jobDao)
        jobs = jobRepository.getJobs()
    }
}

