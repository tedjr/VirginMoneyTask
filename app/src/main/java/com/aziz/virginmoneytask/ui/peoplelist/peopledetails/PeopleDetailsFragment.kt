package com.aziz.virginmoneytask.ui.peoplelist.peopledetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aziz.virginmoneytask.R
import com.aziz.virginmoneytask.databinding.FragmentPeopleDetailsBinding
import com.aziz.virginmoneytask.model.PeopleItem
import com.aziz.virginmoneytask.utils.loadImagefromUrl

const val ARG_USERITEM = "useritem"

class PeopleDetailsFragment : Fragment() {
    private var peopleItem: PeopleItem? = null
    private val binding by lazy{
        FragmentPeopleDetailsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            peopleItem = it.getSerializable(ARG_USERITEM) as PeopleItem
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        peopleItem?.let {peopleItem ->
            binding.personFirstName.text=" ${peopleItem.firstName} ${peopleItem.lastName}"
            binding.personImage.loadImagefromUrl(peopleItem.avatar)
            binding.personEmail.text=peopleItem.email
            binding.personTelephone.text=peopleItem.phone
        }
        return binding.root
    }

}