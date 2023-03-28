package com.example.qihangcooperation.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB: ViewBinding>(private val bindingInflate: (inflate: LayoutInflater) -> VB): AppCompatActivity() {
    private var _viewBinding: VB? = null

    val viewBinding: VB
        get() = _viewBinding ?: throw java.lang.IllegalArgumentException("Binding cannot be null")

    override fun onCreate(savedInstanceState: Bundle?) {
        // 自适配手机尺寸，避免底部出现白边
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        _viewBinding = bindingInflate.invoke(layoutInflater)
        setContentView(viewBinding.root)
        onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        _viewBinding = null
    }

    abstract fun onCreate()
}