package dev.erratika.ernr.data

class JobRepository (private val jobDao: JobDao){
    fun getJobs() = jobDao.getJobs()
    suspend fun deleteJob(job:Job) = jobDao.deleteJob(job)
    suspend fun insertJob(job:Job) = jobDao.insertJob(job)

}
