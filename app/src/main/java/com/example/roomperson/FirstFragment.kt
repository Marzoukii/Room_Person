package com.example.roomperson

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.roomperson.Adapter.MainAdapter
import com.example.roomperson.Room.PersonDB
import com.example.roomperson.model.Person
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    lateinit var adapter: MainAdapter
    var listTable: ArrayList<Person> =ArrayList()
    val db by lazy { PersonDB (this.requireContext()) }
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_Add.setOnClickListener { view ->
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        setupRecyclerView()
        recycler.adapter = adapter

        DeleteAllListener()
    }

    fun setupRecyclerView() {
        adapter = MainAdapter(arrayListOf(), object : MainAdapter.OnAdapterListener {


            override fun OnDelete(person: Person) {
                GlobalScope.launch(Dispatchers.Main) {
                    db.PersonDao().deletePerson(person)
                    //leadRead()
                    onStart()
                }

            }


        })
    }


    override fun onStart() {
        super.onStart()

        listTable.clear()
        leadRead()

        /*   CoroutineScope(Dispatchers.IO).launch {
             val n= db.noteDao().getNote()
               Log.d("chadi", n.toString())

           }*/
    }
    fun leadRead(){
        setupRead()
    }

    fun setupRead(){


        GlobalScope.launch(Dispatchers.Main) {

            progress.visibility = View.VISIBLE
            val n= db.PersonDao().getPerson()
            //    val postsResponse = postsRequest.
            //    val x= db.noteDao().getNote().indices

/*for ( cmd in n){
    listTable.add(cmd)
}*/
            for (cmd in n){
                listTable.addAll(listOf(
                    Person(
                        cmd.id,
                        cmd.name,
                        cmd.age
                    )
                ))
            }


            // n?.let { listTable.addAll(it) }
            //    Log.d("abc", n.toString())

            progress.visibility = View.GONE
            adapter.items=listTable
            adapter.notifyDataSetChanged()



        }
    }


    fun DeleteAllListener(){
        button_delete.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {

                db.PersonDao().delete()
                //leadRead()
                onStart()
            }
        }
    }



}