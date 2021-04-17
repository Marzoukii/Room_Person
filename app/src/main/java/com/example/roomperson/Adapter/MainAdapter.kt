package com.example.roomperson.Adapter

import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomperson.R
import com.example.roomperson.model.Person
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.person_item.view.*

class MainAdapter(var items: List<Person>, private val listener: OnAdapterListener): RecyclerView.Adapter<MainAdapter.PostHolder>() {

    override fun getItemCount() = items.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PostHolder(
        LayoutInflater.from(parent.context).inflate(
        R.layout.person_item, parent, false))
    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        holder.bind(items[position])
        val n=  items[position]
        holder.containerView.icon_delete.setOnClickListener {
            // Log.d("ppp", holder.adapterPosition.toString())
            listener.OnDelete(n)
            //listener.OnUpdate(n)

        }
    }

    inner class PostHolder(override val containerView: View): RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bind(item: Person) {
            // postId.text = item.id.toString()
            // containerView.IDD.text = item.id.toString()
            containerView.text_title.text = item.name

            // containerView.txt_Note.text = item.note
            //   postBody.text = item.description
            // Glide.with(containerView.context).load(item.photo).into(containerView.image)

        }
    }

    interface OnAdapterListener{

        fun OnDelete(person: Person)
        //  fun OnUpdate(note: Note)
    }

}