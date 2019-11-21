package com.mufeng.sample.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mufeng.sample.databinding.ItemHomeListBinding
import com.mufeng.sample.db.bean.HomeArticle

/**
 * @创建者 MuFeng-T
 * @创建时间 2019/11/21 15:26
 * @描述
 */
class HomeAdapter(val viewModel: HomeViewModel): PagedListAdapter<HomeArticle, HomeAdapter.ViewHolder>(callback){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHomeListBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    companion object {
        private val callback = object : DiffUtil.ItemCallback<HomeArticle>(){
            override fun areItemsTheSame(oldItem: HomeArticle, newItem: HomeArticle): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: HomeArticle, newItem: HomeArticle): Boolean {
                return oldItem == newItem
            }

        }
    }

    inner class ViewHolder(val binding: ItemHomeListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindTo(item: HomeArticle?) {
            binding.viewModel = viewModel
            binding.homeArticle = item
            binding.tag = item?.tags?.get(0)
            binding.showTag = item?.tags?.isNotEmpty() == true
            binding.authorName = if (item?.author?.isNotEmpty() == true) item.author else item?.shareUser
            binding.showThumbnail = item?.envelopePic?.isNotEmpty() == true
            binding.chapterName = when {
                item?.superChapterName?.isNotEmpty() == true and (item?.chapterName?.isNotEmpty() == true) ->
                    "${item.superChapterName} / ${item.chapterName}"
                item?.superChapterName?.isNotEmpty() == true -> item.superChapterName
                item?.chapterName?.isNotEmpty() == true -> item.chapterName
                else -> ""
            }
        }
    }
}