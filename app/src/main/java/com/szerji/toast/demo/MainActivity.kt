package com.szerji.toast.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.szerji.toast.configs.ToastPosition
import com.szerji.toast.demo.databinding.ActivityMainBinding
import com.szerji.toast.views.ToastView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setClickEvents()

    }

    private fun setClickEvents() {
        //默认
        binding.btnDefault.setOnClickListener {
            ToastView(this).setMessage(R.string.app_name).show()
        }

        //不同位置显示
        binding.btnDefaultTop.setOnClickListener {
            ToastView(this).setMessage(R.string.app_name).setPosition(ToastPosition.TOP).show()
        }

        //背景色
        binding.btnBackground.setOnClickListener {
            ToastView(this).setMessage(R.string.app_name).setBackground("#FFB225").show()
        }

        //文字颜色
        binding.btnColorText.setOnClickListener {
            ToastView(this).setMessage(R.string.app_name)
                .setBackgroundDrawable(R.drawable.bg_gradient)
                .setTextColor(R.color.colorPrimary)
                .show()
        }

        //圆角
        binding.btnCorners.setOnClickListener {
            ToastView(this)
                .setMessage("背景圆角，图片背景，白色字")
                .setBackgroundDrawable(R.drawable.bg_gradient)
                .setTextColor(R.color.c_ffffff)
                .setCorners(80)
                .setDuration(3000)
                .setTextSize(25f)
                .show()
        }

        //设置图片
        binding.btnImg.setOnClickListener {
            ToastView(this).setMessage("带图片").setImageDrawable(R.drawable.ic_toast).show()
        }

        //错误显示
        binding.btnError.setOnClickListener {
            ToastView(this).setPosition(ToastPosition.TOP).showError("错误信息")
        }

        //成功显示
        binding.btnSuccess.setOnClickListener {
            ToastView(this).showSuccess("成功信息")
        }

        //警告显示
        binding.btnWarning.setOnClickListener {
            ToastView(this).showWarning("警告信息、警告信息警告信息警告信息警告信息警告信息警告信息警告信息警告信息警告信息警告信息警告信息警告信息警告信息")
        }

    }
}