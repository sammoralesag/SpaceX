package com.test.spacex.ui.features.launch

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.spacex.R
import com.test.spacex.databinding.ItemLaunchBinding
import com.test.spacex.domain.models.Launch
import javax.inject.Inject

class LaunchAdapter (private val context: Context?, private val onLaunchSelected: (launch:Launch, lastPosition : Int?, currentPosition : Int?) -> Unit) : ListAdapter<Launch, LaunchAdapter.LaunchViewHolder>(DiffCallbackLaunch()) {

    private var lastCheckedPosition = -1

    inner class LaunchViewHolder(private val itemBinding : ItemLaunchBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(launch: Launch) {
            val isTablet =  context?.resources?.getBoolean(R.bool.isTablet) ?: false
            with(itemBinding) {

                tvLaunchName.text = launch.mission_name
                if (launch.isSelected && isTablet){
                    cvLaunchItem.setCardBackgroundColor(context?.getColor(com.google.android.material.R.color.cardview_shadow_start_color) ?: Color.GRAY)
                } else {
                    cvLaunchItem.setCardBackgroundColor(context?.getColor(com.google.android.material.R.color.cardview_light_background) ?: Color.WHITE)
                }
                root.setOnClickListener {
                    if (isTablet) {
                        if (lastCheckedPosition != -1) {
                            currentList[lastCheckedPosition].isSelected = false
                        }
                        currentList[adapterPosition].isSelected = true
                        val lastCheckedPositionBackup = lastCheckedPosition
                        lastCheckedPosition = adapterPosition
                        onLaunchSelected(launch, lastCheckedPositionBackup, lastCheckedPosition)
                    } else {
                        onLaunchSelected(launch, null, null)
                    }

                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchViewHolder {
        return LaunchViewHolder(ItemLaunchBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: LaunchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class DiffCallbackLaunch : DiffUtil.ItemCallback<Launch>() {
    override fun areItemsTheSame(oldItem: Launch, newItem: Launch): Boolean {
        return oldItem.flight_number == newItem.flight_number
    }

    override fun areContentsTheSame(oldItem: Launch, newItem: Launch): Boolean {
        return oldItem == newItem
    }
}