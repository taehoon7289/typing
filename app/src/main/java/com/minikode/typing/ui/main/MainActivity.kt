package com.minikode.typing.ui.main

import com.minikode.typing.BaseActivity
import com.minikode.typing.R
import com.minikode.typing.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val layoutRes: Int = R.layout.activity_main
    override val backPressEndPointFlag: Boolean = false

    override fun initView() {

    }
}