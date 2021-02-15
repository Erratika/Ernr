package dev.erratika.ernr


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.erratika.ernr.data.Job
import dev.erratika.ernr.databinding.ListItemJobBinding

/**
 * [RecyclerView.Adapter] that can display a [Job].
 * TODO: Replace the implementation with code for your data type.
 */
class JobAdapter : ListAdapter<Job, RecyclerView.ViewHolder>(JobDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return JobViewHolder(
            ListItemJobBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val job = getItem(position)
        (holder as JobViewHolder).bind(job)
    }

    class JobViewHolder(
        private val binding: ListItemJobBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.job?.let { job ->
                    navigateToJob(job, it)
                }
            }
        }

        private fun navigateToJob(
            job: Job,
            view: View
        ) {
//            val direction =
//                HomeViewPagerFragmentDirections.actionViewPagerFragmentToJobDetailFragment(
//                    job.id
//                )
//            view.findNavController().navigate(direction)
        }

        fun bind(item: Job) {
            binding.apply {
                job = item
                executePendingBindings()
            }
        }
    }
}

private class JobDiffCallback : DiffUtil.ItemCallback<Job>() {

    override fun areItemsTheSame(oldItem: Job, newItem: Job): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Job, newItem: Job): Boolean {
        return oldItem == newItem
    }
}