package com.mufeng.sample.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.mufeng.mvvmlib.basic.adapter.AdapterDataObserverProxy
import com.mufeng.mvvmlib.utils.context
import com.mufeng.mvvmlib.utils.toast
import com.mufeng.sample.R
import com.mufeng.sample.databinding.FooterLoadMoreBinding
import com.mufeng.sample.databinding.HeaderHomeBannerBinding
import com.mufeng.sample.databinding.ItemHomeListBinding
import com.mufeng.sample.db.bean.Banner
import com.mufeng.sample.db.bean.HomeArticle
import com.mufeng.sample.db.bean.Tag
import com.zhpan.bannerview.BannerViewPager
import com.zhpan.bannerview.adapter.OnPageChangeListenerAdapter
import com.zhpan.bannerview.constants.IndicatorGravity
import com.zhpan.bannerview.constants.IndicatorSlideMode

/**
 * @创建者 MuFeng-T
 * @创建时间 2019/11/21 15:26
 * @描述
 */
class HomeAdapter(val viewModel: HomeViewModel) :
    PagedListAdapter<HomeArticle, RecyclerView.ViewHolder>(callback) {

    private val bannerList = arrayListOf<Banner>()

    fun setHeaderData(banners: List<Banner>){
        this.bannerList.addAll(banners)
        notifyItemChanged(-1)
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> ITEM_TYPE_HEADER
            itemCount - 1 -> ITEM_TYPE_FOOTER
            else -> super.getItemViewType(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            ITEM_TYPE_HEADER -> HeaderViewHolder(
                HeaderHomeBannerBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )
            ITEM_TYPE_FOOTER -> FooterViewHolder(
                FooterLoadMoreBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )
            else -> ViewHolder(
                ItemHomeListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> holder.bindTo(bannerList)
            is FooterViewHolder -> holder.bindTo()
            is ViewHolder -> holder.bindTo(getHomeArticleItem(position))
        }
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
        private val callback = object : DiffUtil.ItemCallback<HomeArticle>() {
            override fun areItemsTheSame(oldItem: HomeArticle, newItem: HomeArticle): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: HomeArticle, newItem: HomeArticle): Boolean {
                return oldItem == newItem
            }
        }

        private const val ITEM_TYPE_HEADER = 99
        private const val ITEM_TYPE_FOOTER = 100
    }

    inner class ViewHolder(val binding: ItemHomeListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindTo(item: HomeArticle?) {
            binding.viewModel = viewModel
            binding.homeArticle = item
            binding.tag = if (item?.tags?.isNotEmpty() == true) item?.tags?.get(0) else Tag()
            binding.showTag = item?.tags?.isNotEmpty() == true
            binding.authorName =
                if (item?.author?.isNotEmpty() == true) item.author else item?.shareUser
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

    inner class HeaderViewHolder(val binding: HeaderHomeBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val bannerViewPager = binding.bannerViewPager as BannerViewPager<Banner, HomeBannerViewHolder>

        fun bindTo(list: List<Banner>) {
            bannerViewPager.setCanLoop(true)
                .setIndicatorSlideMode(IndicatorSlideMode.SMOOTH)
                .setIndicatorGravity(IndicatorGravity.CENTER)
                .setIndicatorColor(context.resources.getColor(R.color.secondaryDarkColor),
                    context.resources.getColor(R.color.primaryColor))
                .setHolderCreator { HomeBannerViewHolder() }
                .setOffScreenPageLimit(list.size)
                .setOnPageChangeListener(object : OnPageChangeListenerAdapter() {
                    override fun onPageSelected(position: Int) {

                    }
                })
                .setOnPageClickListener {
                    val banner = list[it]
                    viewModel.bannerItemClick(banner)
                }
                .create(list)
        }
    }

    inner class FooterViewHolder(val binding: FooterLoadMoreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindTo() {

        }
    }
}