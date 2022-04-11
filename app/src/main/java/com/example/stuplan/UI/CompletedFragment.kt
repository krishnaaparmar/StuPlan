package com.example.stuplan.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stuplan.adapters.CompletedTaskAdapter
import com.example.stuplan.databinding.FragmentCompletedBinding
import com.example.stuplan.model.CourseModel
import com.example.stuplan.model.TaskModel
import com.example.stuplan.sqlite.DatabaseHelper

class CompletedFragment : Fragment() {

    var taskList = ArrayList<TaskModel>()
    var courseList = ArrayList<CourseModel>()
    private lateinit var dbHelper : DatabaseHelper
    private lateinit var binding : FragmentCompletedBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCompletedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dbHelper = DatabaseHelper(requireContext())
        taskList = dbHelper.getAllCompletedTask()

        if (taskList.isNullOrEmpty()){
            binding.tvNoCompletedTask.visibility=View.VISIBLE
        } else{
            binding.tvNoCompletedTask.visibility=View.GONE

        }

        courseList = dbHelper.getAllCourse()

        binding.rvCompleted.also {
            it.layoutManager= LinearLayoutManager(requireContext()
                , LinearLayoutManager.VERTICAL, false)
            it.setHasFixedSize(true)
            it.adapter = CompletedTaskAdapter(taskList,courseList)
        }


    }

}