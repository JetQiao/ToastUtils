package com.szerji.toast.views

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import com.szerji.toast.R
import com.szerji.toast.configs.ToastPosition
import com.szerji.toast.databinding.LayoutToastMessageBinding
import com.szerji.toast.utils.ScreenUtils


/**
 * description toast
 * use
 * param
 * return
 *
 * @author JetQiao
 * @date 2024/9/5 21:53
 */
class ToastView(private val context: Context) {

    private val mHandler: Handler = Handler(Looper.getMainLooper())

    private var message: String? = ""
    private var duration: Long = 1000
    private var position = ToastPosition.BOTTOM
    private var background = Color.BLACK
    private var backgroundRes: Drawable? = null
    private var textColor = Color.WHITE
    private var cornerRadius = 20
    private var textSize = 18f
    private var imgRes: Drawable? = null

    private var toast: Toast? = null

    /**
     * 设置文字
     * @param message String
     */
    fun setMessage(message: String): ToastView {
        this.message = message
        return this
    }

    /**
     * 设置文字
     * @param message CharSequence
     */
    fun setMessage(message: CharSequence): ToastView {
        this.message = message.toString()
        return this
    }

    /**
     * 设置文字（资源文件）
     * @param message R.string.xxx
     */
    fun setMessage(@StringRes message: Int): ToastView {
        this.message = context.getString(message)
        return this
    }

    /**
     * 设置位置
     * @param position ToastPosition.TOP, ToastPosition.BOTTOM, ToastPosition.CENTER
     */
    fun setPosition(position: ToastPosition): ToastView {
        this.position = position
        return this
    }

    /**
     * 设置文字颜色(资源文件)
     * @param textColor R.color.xxx
     */
    fun setTextColor(@ColorRes textColor: Int): ToastView {
        this.textColor = ContextCompat.getColor(context, textColor)
        return this
    }

    /**
     * 设置文字颜色
     * @param textColor "#FF0000"
     */
    fun setTextColor(textColor: String): ToastView {
        this.textColor = Color.parseColor(textColor)
        return this
    }

    /**
     * 设置背景颜色(资源文件)
     * @param backgroundColor R.color.xxx
     * @param 与[setBackgroundDrawable]不能共存，优先级为[setBackgroundDrawable]
     */
    fun setBackground(@ColorRes backgroundColor: Int): ToastView {
        this.background = ContextCompat.getColor(context, backgroundColor)
        return this
    }

    /**
     * 设置背景颜色
     * @param backgroundColor "#FF0000"
     * @param 与[setBackgroundDrawable]不能共存，优先级为[setBackgroundDrawable]
     */
    fun setBackground(backgroundColor: String): ToastView {
        this.background = Color.parseColor(backgroundColor)
        return this
    }

    /**
     * 设置背景图片(资源文件)
     * @param background R.drawable.xxx
     * @param 与[setBackground]不能共存，优先级为[setBackgroundDrawable]
     */
    fun setBackgroundDrawable(@DrawableRes background: Int): ToastView {
        this.backgroundRes = ContextCompat.getDrawable(context, background)
        return this
    }

    /**
     * 设置背景图片
     * @param imgRes R.drawable.xxx
     */
    fun setImageDrawable(@DrawableRes imgRes: Int): ToastView {
        this.imgRes = ContextCompat.getDrawable(context, imgRes)
        return this
    }

    /**
     * 设置圆角
     * @param corners Int
     */
    fun setCorners(corners: Int): ToastView {
        this.cornerRadius = corners
        return this
    }

    /**
     * 设置显示时长
     * @param duration Long
     */
    fun setDuration(duration: Long): ToastView {
        this.duration = duration
        return this
    }

    /**
     * 设置文字大小
     * @param textSize Float
     */
    fun setTextSize(textSize: Float): ToastView {
        this.textSize = textSize
        return this
    }

    /**
     * 显示toast
     * @param  actions  [showError]、 [showSuccess] 、[showWarning] 无需调用
     */
    fun show() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            showUtils()
        } else {
            mHandler.post { showUtils() }
        }
    }


    @SuppressLint("InflateParams")
    private fun showUtils() {
        if (toast != null) {
            toast?.cancel()
        }
        toast = Toast(context)

        val binding = LayoutToastMessageBinding.inflate(LayoutInflater.from(context))

        if (backgroundRes != null) {
            binding.cvToast.background = backgroundRes
        } else {
            binding.cvToast.setCardBackgroundColor(background)
        }

        binding.cvToast.radius = cornerRadius.toFloat()
        binding.cvToast.cardElevation = ScreenUtils.dpToPx(context, 10).toFloat()
        binding.cvToast.maxCardElevation = ScreenUtils.dpToPx(context, 10).toFloat()

        binding.llToastContent.setPadding(20)
        binding.tvToastMessage.text = message
        binding.tvToastMessage.textSize = textSize
        binding.tvToastMessage.setTextColor(textColor)

        if (imgRes != null) {
            binding.imgToastIcon.visibility = View.VISIBLE
            binding.imgToastIcon.setImageDrawable(imgRes)
        } else {
            binding.imgToastIcon.visibility = View.GONE
        }


        // 使用Handler来控制显示时间
        mHandler.postDelayed({
            toast?.cancel()
        }, duration)

        toast!!.view = binding.root

        //设置toast位置
        when (position) {
            ToastPosition.TOP -> {
                toast!!.setGravity(Gravity.TOP or Gravity.FILL_HORIZONTAL, 0, 0)
            }

            ToastPosition.BOTTOM -> {
                toast!!.setGravity(Gravity.BOTTOM or Gravity.FILL_HORIZONTAL, 0, 0)
            }

            ToastPosition.CENTER -> {
                toast!!.setGravity(Gravity.CENTER or Gravity.FILL_HORIZONTAL, 0, 0)
            }
        }

        toast!!.show()

    }

    /**
     * 调用默认错误案例
     */
    fun showError(s: String) {
        this.message = s
        errorSetting()
        showUtils()
    }

    fun showError(@StringRes message: Int) {
        this.message = context.getString(message)
        errorSetting()
        showUtils()
    }

    fun showError(message: CharSequence) {
        this.message = message.toString()
        errorSetting()
        showUtils()
    }


    /**
     * 调用默认警告案例
     */
    fun showWarning(s: String) {
        this.message = s
        warningSetting()
        showUtils()
    }

    fun showWarning(@StringRes message: Int) {
        this.message = context.getString(message)
        warningSetting()
        showUtils()
    }

    fun showWarning(message: CharSequence) {
        this.message = message.toString()
        warningSetting()
        showUtils()
    }


    /**
     * 调用默认成功案例
     */
    fun showSuccess(s: String) {
        this.message = s
        successSetting()
        showUtils()
    }

    fun showSuccess(@StringRes message: Int) {
        this.message = context.getString(message)
        successSetting()
        showUtils()
    }

    fun showSuccess(message: CharSequence) {
        this.message = message.toString()
        successSetting()
        showUtils()
    }

    private fun successSetting() {
        this.background = Color.parseColor("#8BCA48")
        this.textColor = Color.parseColor("#FFFFFF")
        this.duration = 1500
        setImageDrawable(R.drawable.ic_success)
    }

    private fun errorSetting() {
        this.background = Color.parseColor("#E78585")
        this.textColor = Color.parseColor("#FFFFFF")
        this.duration = 1500
        setImageDrawable(R.drawable.ic_error)
    }

    private fun warningSetting() {
        this.background = Color.parseColor("#FFB225")
        this.textColor = Color.parseColor("#FFFFFF")
        this.duration = 1500
        setImageDrawable(R.drawable.ic_warning)
    }

}