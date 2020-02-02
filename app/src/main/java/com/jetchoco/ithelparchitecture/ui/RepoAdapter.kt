package com.jetchoco.ithelparchitecture.ui

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jetchoco.ithelparchitecture.data.model.Repo
import com.jetchoco.ithelparchitecture.databinding.ItemRepoBinding
import java.util.*

class RepoAdapter(private val items: MutableList<Repo>) :
    RecyclerView.Adapter<RepoAdapter.ViewHolder>() {

    data class ViewHolder(val binding: ItemRepoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(repo: Repo) {
            binding.repo = repo
            binding.executePendingBindings()
        }
    }

    fun swapItems(newItems: List<Repo>) {
        val result = DiffUtil.calculateDiff(RepoDiffCallback(items, newItems))
        items.clear()
        items.addAll(newItems)
        result.dispatchUpdatesTo(this)
    }

    fun clearItems() {
        val size = items.size
        items.clear()
        notifyItemRangeChanged(0, size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRepoBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class RepoDiffCallback(
        private val oldList: List<Repo>,
        private val newList: List<Repo>
    ) : DiffUtil.Callback() {

        @RequiresApi(Build.VERSION_CODES.KITKAT)
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldId = oldList[oldItemPosition].id
            val newId = oldList[newItemPosition].id
            // ref: https://stackoverflow.com/questions/21548989/using-objects-equals-in-android
            return Objects.equals(oldId, newId)
        }

        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        @RequiresApi(Build.VERSION_CODES.KITKAT)
        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldRepo = oldList[oldItemPosition]
            val newRepo = newList[newItemPosition]
            return Objects.equals(oldRepo, newRepo)
        }

    }

}