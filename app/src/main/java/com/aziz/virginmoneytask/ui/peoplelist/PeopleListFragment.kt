package com.aziz.virginmoneytask.ui.peoplelist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.aziz.virginmoneytask.R
import com.aziz.virginmoneytask.adapter.PeopleListAdapter
import com.aziz.virginmoneytask.databinding.FragmentPeopleListBinding
import com.aziz.virginmoneytask.model.People
import com.aziz.virginmoneytask.utils.UIState
import com.aziz.virginmoneytask.utils.showErrorMsg
import org.koin.androidx.viewmodel.ext.android.viewModel

const val ARG_USERITEM = "useritem"

class PeopleListFragment : Fragment() {

    private val binding by lazy{
        FragmentPeopleListBinding.inflate(layoutInflater)
    }
    private val peopleViewModel: PeopleViewModel by viewModel()
    private lateinit var peopleListAdapter: PeopleListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        peopleListAdapter= PeopleListAdapter()
        binding.peopleList.adapter=peopleListAdapter
        peopleViewModel.peopleLiveData.observe(viewLifecycleOwner) { uistate ->
            when (uistate) {
                is UIState.LOADING -> {}
                is UIState.SUCCESS -> {
                    val peopleList = uistate.success as People
                    peopleListAdapter.loadData(peopleList)
                }
                is UIState.ERROR -> {
                    showErrorMsg(binding.root, uistate.error.localizedMessage.toString())
                }
            }

        }
        peopleListAdapter.onItemClick={peopleItem ->
            val bundle=Bundle().apply{
                putSerializable(ARG_USERITEM,peopleItem)
            }
            findNavController().navigate(R.id.action_peopleListFragment_to_peopleDetailsFragment,bundle)
        }
        peopleViewModel.subscribeToPeopleList()
        return binding.root
    }
}