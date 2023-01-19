package com.example.suitmediaapp
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.suitmediaapp.model.UserNameModel
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class UserAdapter(val results: ArrayList<UserNameModel>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.list_user, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = results[position]
        Picasso.get()
            .load(result.avatar)
            .placeholder(R.drawable.ic_photo)
            .error(R.drawable.ic_photo)
            .into(holder.profileImage)
        holder.fullName.text = result.first_name + " " + result.last_name
        holder.userEmail.text = result.email

    }

    override fun getItemCount() = results.size

    class ViewHolder (val view: View) : RecyclerView.ViewHolder(view) {
        var profileImage: CircleImageView = view.findViewById(R.id.profile_image)
        var fullName: TextView = view.findViewById(R.id.fullName_text)
        var userEmail: TextView = view.findViewById(R.id.userEmail_text)
        init {
            view.setOnClickListener {
                val pref = view.context.getSharedPreferences("myPrefs", AppCompatActivity.MODE_PRIVATE)
                pref.edit().putString("username", fullName.text.toString()).apply()
                view.context.startActivity(Intent(view.context, PageTwo::class.java))
                val viewAct = view.context as Activity
                viewAct.finish()
            }
        }

    }

}