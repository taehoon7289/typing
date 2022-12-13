package com.minikode.typing.ui

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import com.minikode.typing.BaseActivity
import com.minikode.typing.R
import com.minikode.typing.databinding.ActivitySplashBinding
import com.minikode.typing.ui.main.MainActivity
import timber.log.Timber

class SplashActivity : BaseActivity<ActivitySplashBinding>() {
    override val layoutRes: Int = R.layout.activity_splash
    override val backPressEndPointFlag: Boolean = false

    private lateinit var mainLauncher: ActivityResultLauncher<Intent>

    override fun initView() {
        Timber.d("hello world!")
        mainLauncher =
            createActivityResultLauncher { activityResult ->
                Timber.d("activityResult.resultCode: ${activityResult.resultCode}")
            }
        val animator = ValueAnimator.ofFloat(0f, 1f).setDuration(2000)
        animator.addUpdateListener {
            val animatedValue = it.animatedValue as Float
            binding.animationView.progress = animatedValue
        }
        animator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator?) {
                Timber.d("onAnimationStart: ")
            }

            override fun onAnimationEnd(p0: Animator?) {
                Timber.d("onAnimationEnd: ")
                mainLauncher.launch(Intent(this@SplashActivity, MainActivity::class.java))
            }

            override fun onAnimationCancel(p0: Animator?) {
                Timber.d("onAnimationCancel: ")
            }

            override fun onAnimationRepeat(p0: Animator?) {
                Timber.d("onAnimationRepeat: ")
            }
        })
        animator.start()
    }

}