package com.example.newchatui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newchatui.R
import com.example.newchatui.model.AlertList
import com.example.newchatui.model.StatusList
import kotlinx.android.synthetic.main.status_item.view.*

class StatusAdapter(val statusList :List<StatusList>,val context: Context):RecyclerView.Adapter<StatusAdapter.StatusViewHolder>() {
    class StatusViewHolder(view:View) : RecyclerView.ViewHolder(view){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatusViewHolder {
        return StatusAdapter.StatusViewHolder(
            LayoutInflater.from(context).inflate(R.layout.status_item, parent, false)
        )

    }

    override fun getItemCount(): Int{
        return statusList.size
    }

    override fun onBindViewHolder(holder: StatusViewHolder, position: Int) {
        holder.itemView.txtIdStatus.text = statusList.get(position).id

        if (statusList.get(position).status.toString() == "0"){
            holder.itemView.txtStatus.text = "Pending"
        }
        else if(statusList.get(position).status.toString() == "1"){
            holder.itemView.txtStatus.text = "Active"
        }
        else{
            holder.itemView.txtStatus.text = "Completed"
        }
        holder.itemView.txtCreatedAtStatus.text = statusList.get(position).createdAt
    }
}