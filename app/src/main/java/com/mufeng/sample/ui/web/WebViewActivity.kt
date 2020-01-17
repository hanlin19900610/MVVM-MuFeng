package com.mufeng.sample.ui.web

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.mufeng.mvvmlib.basic.view.BaseActivity
import com.mufeng.mvvmlib.ext.getString
import com.mufeng.sample.R
import com.ycbjie.webviewlib.*
import kotlinx.android.synthetic.main.activity_web_view.*
import timber.log.Timber

/**
 * @创建者 MuFeng-T
 * @创建时间 2020/1/17 16:27
 * @描述
 */
class WebViewActivity : BaseActivity() {
    override val layoutResId: Int
        get() = R.layout.activity_web_view

    private var x5WebChromeClient: X5WebChromeClient? = null
    private var x5WebViewClient: X5WebViewClient? = null
    private var webView: X5WebView? = null

    private var url: String = ""

    override fun initView(savedInstanceState: Bundle?) {
        url = intent.getString("url")
        webView = web_view

        //显示进度条
        progress.show()
        //设置进度条过度颜色
        progress.setColor(Color.BLUE, Color.RED)
        //设置单色进度条
        progress.setColor(Color.BLUE)

        title_tool_bar.setNavigationOnClickListener { finish() }
        title_tool_bar.overflowIcon = ContextCompat.getDrawable(
            this,
            com.ycbjie.webviewlib.R.drawable.icon_more
        )
        tv_title.postDelayed({ tv_title.isSelected = true }, 1000)
        tv_title.text = "加载中……"

        x5WebChromeClient = webView?.x5WebChromeClient
        x5WebViewClient = webView?.x5WebViewClient
        x5WebChromeClient?.setVideoWebListener(videoWebListener)
        x5WebViewClient?.setWebListener(interWebListener)
        x5WebChromeClient?.setWebListener(interWebListener)

        webView?.loadUrl(url)
    }

    override fun initData() {

    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onResume() {
        super.onResume()
        if (webView != null) {
            webView?.settings?.javaScriptEnabled = true
        }
    }

    override fun onStop() {
        super.onStop()
        if (webView != null) {
            webView?.settings?.javaScriptEnabled = false
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        //这个是处理回调逻辑，上传图片成功后的回调
        if (requestCode == X5WebChromeClient.FILE_CHOOSER_RESULT_CODE) {
            x5WebChromeClient?.uploadMessage(intent, resultCode)
        } else if (requestCode == X5WebChromeClient.FILE_CHOOSER_RESULT_CODE_5) {
            x5WebChromeClient?.uploadMessageForAndroid5(intent, resultCode)
        }
    }

    private val videoWebListener = object : VideoWebListener {
        override fun showVideoFullView() {
            //视频全频播放时监听
        }

        override fun hindVideoFullView() {
            //隐藏全频播放，也就是正常播放视频
        }

        override fun showWebView() {
            //显示webView
        }

        override fun hindWebView() {
            //隐藏webView
        }
    }

    private val interWebListener = object : InterWebListener {
        override fun hindProgressBar() {
            //进度完成后消失
            progress.hide()
        }

        override fun showErrorView(type: Int) {
            when (type) {
                //没有网络
                X5WebUtils.ErrorMode.NO_NET -> {
                }
                //404，网页无法打开
                X5WebUtils.ErrorMode.STATE_404 -> {
                }
                //onReceivedError，请求网络出现error
                X5WebUtils.ErrorMode.RECEIVED_ERROR -> {
                }
                //在加载资源时通知主机应用程序发生SSL错误
                X5WebUtils.ErrorMode.SSL_ERROR -> {
                }
                else -> {
                }
            }
        }

        override fun startProgress(newProgress: Int) {
            //为单独处理WebView进度条
            progress.setWebProgress(newProgress)
        }

        override fun showTitle(title: String) {
            tv_title.text = title
        }
    }

    override fun onDestroy() {
        try {
            if (x5WebChromeClient != null) {
                x5WebChromeClient?.removeVideoView()
            }
            //有音频播放的web页面的销毁逻辑
            //在关闭了Activity时，如果Webview的音乐或视频，还在播放。就必须销毁Webview
            //但是注意：webview调用destory时,webview仍绑定在Activity上
            //这是由于自定义webview构建时传入了该Activity的context对象
            //因此需要先从父容器中移除webview,然后再销毁webview:
            if (webView != null) {
                val parent = webView!!.parent as ViewGroup
                parent?.removeView(webView)
                webView!!.removeAllViews()
                webView!!.destroy()
                webView = null
            }
        } catch (e: Exception) {
            Timber.tag("X5WebViewActivity").e(e.message)
        }

        super.onDestroy()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //全屏播放退出全屏
            if (x5WebChromeClient != null && x5WebChromeClient?.inCustomView() == true) {
                x5WebChromeClient?.hideCustomView()
                return true
                //返回网页上一页
            } else if (webView?.canGoBack() == true) {
                webView?.goBack()
                return true
                //退出网页
            } else {
                handleFinish()
            }
        }
        return false
    }

    fun handleFinish() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition()
        } else {
            finish()
        }
    }

}