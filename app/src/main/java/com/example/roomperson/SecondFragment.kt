package com.example.roomperson

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.roomperson.Room.PersonDB
import com.example.roomperson.model.Person
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */

    class SecondFragment : Fragment() {
        val db by lazy { PersonDB (this.requireContext())}
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_second, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            setupListener()

        }
        fun setupListener(){
            button_save.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    db.PersonDao().addPerson(
                        Person(
                            0,
                            edit_title.text.toString(),
                            edit_age.text.toString()
                        )

                    )

                }
                findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            }
        }

    }