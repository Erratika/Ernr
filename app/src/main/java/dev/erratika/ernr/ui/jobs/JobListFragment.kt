package dev.erratika.ernr.ui.jobs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import dev.erratika.ernr.JobAdapter
import dev.erratika.ernr.JobDetailFragment
import dev.erratika.ernr.R
import dev.erratika.ernr.databinding.FragmentJobListBinding

class JobListFragment : Fragment() {

    private lateinit var viewModel: JobListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(JobListViewModel::class.java)

        val binding = FragmentJobListBinding.inflate(inflater,container,false)
        context?: return binding.root

        val adapter = JobAdapter()
        binding.jobList.adapter = adapter
        subscribeUi(adapter)

        binding.addJobFab.setOnClickListener {
            navigateToJob()
        }

        return binding.root

    }
    private fun navigateToJob(){
        findNavController().navigate(R.id.action_navigation_jobs_to_navigation_job)
    }

    private fun subscribeUi(adapter: JobAdapter) {
        viewModel.jobs.observe(viewLifecycleOwner) { jobs ->
            adapter.submitList(jobs)
        }
    }






}