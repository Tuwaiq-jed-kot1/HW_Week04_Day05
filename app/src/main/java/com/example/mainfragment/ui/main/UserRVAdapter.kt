package com.example.mainfragment.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mainfragment.R
import com.example.mainfragment.data.model.User
import com.example.mainfragment.ui.details.DetailsFragment

class UserRVAdapter(private val users: List<User>) : RecyclerView.Adapter<CustomAdapter>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)
        return CustomAdapter(view)
    }


    override fun onBindViewHolder(holder: CustomAdapter, position: Int) {
        val user = users[position]
        holder.idTextView.text = user.id.toString()
        holder.nameTextView.text = "${user.fName} ${user.lName}"
        holder.itemView.setOnClickListener { view ->
            //fragment
            val activity= view.context as AppCompatActivity
            val bundel =Bundle()
            bundel.putParcelable("user key ",user)

            val fragment=DetailsFragment.newInstance()
             fragment.arguments=bundel
            activity.supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
                //ترجع ورا مو اول م تدخلين يطلع في وجهك المين
                .addToBackStack("detalis")
                .commit()
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }
}

class CustomAdapter(itemView: View): RecyclerView.ViewHolder(itemView) {
    val idTextView = itemView.findViewById<TextView>(R.id.tvId)
    val nameTextView = itemView.findViewById<TextView>(R.id.tvName)
   /* override fun onClick(p0: View?) {
        Toast.makeText(itemView.context, "${idTextView.text } ${nameTextView.text}", Toast.LENGTH_LONG).show()
    }
    init {
        itemView.setOnClickListener(this)
    }*/

}
