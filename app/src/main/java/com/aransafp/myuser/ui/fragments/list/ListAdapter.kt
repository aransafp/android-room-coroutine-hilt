package com.aransafp.myuser.ui.fragments.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.aransafp.myuser.R
import com.aransafp.myuser.data.entity.User
import com.aransafp.myuser.databinding.ItemUserBinding

class ListAdapter : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private val listUser = ArrayList<User>()

    fun setData(users: List<User>?) {
        if (users == null) return
        listUser.clear()
        listUser.addAll(users)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listUser[position])
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

    class ViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {

        lateinit var getUser: User

        fun bind(user: User) {
            getUser = user
            with(binding) {
                tvId.text = user.id.toString()
                tvFirstName.text = user.firstName
                tvLastName.text = user.lastName
                tvAge.text =
                    itemView.resources.getString(R.string.age_placeholder, user.age.toString())
            }

            itemView.setOnClickListener {
                val action = ListFragmentDirections.actionListFragmentToUpdateFragment(user)
                itemView.findNavController().navigate(action)
            }
        }
    }
}