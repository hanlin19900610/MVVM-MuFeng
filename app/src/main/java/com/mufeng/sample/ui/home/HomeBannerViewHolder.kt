package com.mufeng.sample.ui.home

import android.view.View
import android.widget.ImageView
import com.mufeng.mvvmlib.binding.setImageUri
import com.mufeng.sample.R
import com.mufeng.sample.db.bean.Banner
import com.zhpan.bannerview.holder.ViewHolder

/**
 * @创建者 MuFeng-T
 * @创建时间 2020/1/17 15:40
 * @描述
 */
class HomeBannerViewHolder : ViewHolder<Banner>{
    override fun getLayoutId(): Int {
        return R.layout.item_home_banner
    }

    override fun onBind(itemView: View?, data: Banner?, position: Int, size: Int) {
        val imageView = itemView?.findViewById<ImageView>(R.id.ivBanner)
        imageView?.setImageUri(data?.imagePath)
    }

}