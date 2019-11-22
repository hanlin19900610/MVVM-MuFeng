package com.mufeng.sample.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import cn.bingoogolapple.bgabanner.BGABanner
import com.mufeng.mvvmlib.binding.setImageUri
import com.mufeng.sample.R
import com.mufeng.sample.base.AdapterDataObserverProxy
import com.mufeng.sample.databinding.ItemHomeListBinding
import com.mufeng.sample.db.bean.Banner
import com.mufeng.sample.db.bean.HomeArticle
import com.mufeng.sample.db.bean.Tag
import org.jetbrains.anko.find
import java.util.ArrayList

/**
 * @创建者 MuFeng-T
 * @创建时间 2019/11/21 15:26
 * @描述
 */
class HomeAdapter(val viewModel: HomeViewModel): PagedListAdapter<HomeArticle, RecyclerView.ViewHolder>(callback){

    private val bannerData = arrayListOf<Banner>()

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> ITEM_TYPE_HEADER
            itemCount - 1 -> ITEM_TYPE_FOOTER
            else -> super.getItemViewType(position)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            ITEM_TYPE_HEADER -> {
                val headerView = LayoutInflater.from(parent.context).inflate(R.layout.item_home_header, parent, false)
                HeaderViewHolder(headerView)
            }
            ITEM_TYPE_FOOTER -> {
                val footerView = LayoutInflater.from(parent.context).inflate(R.layout.item_footer, parent, false)
                FooterViewHolder(footerView)
            }
            else -> {
                val binding = ItemHomeListBinding.inflate(LayoutInflater.from(parent.context),parent, false)
                ViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> {
                holder.bindTo(bannerData)
            }
            is FooterViewHolder -> { }
            is ViewHolder -> holder.bindTo(getHomeArticleItem(position))
        }
    }

    /**
     * 更新头部数据
     * @param banners List<Banner>
     */
    fun setHeaderData(banners: List<Banner>){
        bannerData.clear()
        bannerData.addAll(banners)
        notifyItemChanged(0)
    }

    private fun getHomeArticleItem(position: Int): HomeArticle? {
        return getItem(position - 1)
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + 2
    }

    override fun registerAdapterDataObserver(observer: RecyclerView.AdapterDataObserver) {
        super.registerAdapterDataObserver(AdapterDataObserverProxy(observer, 1))
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

        private const val ITEM_TYPE_HEADER = 10010
        private const val ITEM_TYPE_FOOTER = 10086
    }

    class HeaderViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private var bgaBanner: BGABanner = itemView.find(R.id.bgaBanner)

        init {
            bgaBanner.setAdapter { banner, itemView, model, position ->
                if (itemView is ImageView) {
                    itemView.setImageUri(model as String)
                }
            }
        }

        fun bindTo(bannerData: ArrayList<Banner>) {
            if (bannerData.isEmpty()) return
            val urls = arrayListOf<String>()
            val tips = arrayListOf<String>()
            bannerData.forEach {  banner ->
                urls.add(banner.imagePath!!)
                tips.add(banner.title!!)
            }
            bgaBanner.setData(urls, tips)
        }

    }

    class FooterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    inner class ViewHolder(val binding: ItemHomeListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindTo(item: HomeArticle?) {
            binding.viewModel = viewModel
            binding.homeArticle = item
            binding.tag = if (item?.tags?.isNotEmpty() == true) item?.tags?.get(0) else Tag()
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