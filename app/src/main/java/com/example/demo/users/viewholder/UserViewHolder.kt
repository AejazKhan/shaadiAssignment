package com.example.demo.users.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demo.R
import com.example.demo.users.db.entity.UserWithLocation
import com.example.demo.users.interfaces.MatchClickListener
import kotlinx.android.synthetic.main.matches_row.view.*
/**
 * Created by aejaz.khan.
 * ViewHolder for user list.
 */
class UserViewHolder(itemView : View,private val matchClickListener: MatchClickListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    init {
        itemView.btnAccept.setOnClickListener(this)
        itemView.btnDecline.setOnClickListener(this)
    }

    fun bind(user : UserWithLocation){
        loadProfileImage(user.userWithMatchStatus.user.picLarge)
        itemView.tvName.text = "${user.userWithMatchStatus.user.firstName} ${user.userWithMatchStatus.user.lastName}"
        itemView.tvAge.text = "${user.userWithMatchStatus.user.age}, ${user.userWithMatchStatus.user.gender}"
        itemView.tvAddress.text = "${user.location.city}, ${user.location.country}"

        if(user.userWithMatchStatus.matchStatus?.status == null){
            showMatchButtons()
        }else {
            if(user.userWithMatchStatus.matchStatus?.status!!){
                itemView.tvMatchStatus.text = itemView.context.getString(R.string.accept_msg)
                itemView.tvMatchStatus.setTextColor(itemView.context.resources.getColor(R.color.green))
            }else{
                itemView.tvMatchStatus.text = itemView.context.getString(R.string.decline_msg)
                itemView.tvMatchStatus.setTextColor(itemView.context.resources.getColor(R.color.red_light))
            }
            hideMatchButtons()
        }

    }

    private fun showMatchButtons() {
        itemView.btnDecline.visibility = View.VISIBLE
        itemView.tvDecline.visibility = View.VISIBLE
        itemView.btnAccept.visibility = View.VISIBLE
        itemView.tvAccept.visibility = View.VISIBLE
        itemView.tvMatchStatus.visibility = View.GONE
    }

    private fun hideMatchButtons() {
        itemView.btnDecline.visibility = View.GONE
        itemView.tvDecline.visibility = View.GONE
        itemView.btnAccept.visibility = View.GONE
        itemView.tvAccept.visibility = View.GONE
        itemView.tvMatchStatus.visibility = View.VISIBLE
    }

    private fun loadProfileImage(picThumbnail: String?) {
        if(picThumbnail != null){
            Glide.with(itemView.context)
                .load(picThumbnail)
                .circleCrop()
                .into(itemView.ivProfilePic)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id){
            R.id.btnAccept ->{
                matchClickListener.onAccept(adapterPosition)
            }
            R.id.btnDecline ->{
                matchClickListener.onDecline(adapterPosition)
            }
        }
    }
}