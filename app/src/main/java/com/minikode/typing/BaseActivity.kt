package com.minikode.typing

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import timber.log.Timber

abstract class BaseActivity<View : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var binding: View

    protected abstract val layoutRes: Int

    protected abstract val backPressEndPointFlag: Boolean

    private var backKeyPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = createBinding(layoutRes)
        initView()

    }

    private fun createBinding(@LayoutRes layoutRes: Int): View {
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.lifecycleOwner = this
        return binding
    }

    abstract fun initView()

    private lateinit var call: ActivityResultLauncher<Intent>

    fun createActivityResultLauncher(callbackResult: (ActivityResult) -> Unit): ActivityResultLauncher<Intent> {
        return registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            callbackResult,
        )
    }

    override fun onBackPressed() {
        Timber.d("activity의 onBackPressed 동작!!!!!!")
        if (backPressEndPointFlag) {
            handlerEndPoint()
        } else {
            super.onBackPressed()
        }
    }

    private fun handlerEndPoint() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis()
            App.instance.showToast("\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.")
            return
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            finish()
            App.instance.cancelToast()
        }

    }
}