package com.ananananzhuo.anim.second.propertyanim

import android.os.Bundle
import android.view.Menu
import com.ananananzhuo.anim.AnimationAction
import com.ananananzhuo.anim.R
import com.ananananzhuo.anim.databinding.ActivityTransationxBinding
import com.ananananzhuo.mvvm.activity.ViewBindingActivity

class ValueAnimTransXActivity : ViewBindingActivity<ActivityTransationxBinding>() {

    var currentAnim: AnimationAction<ActivityTransationxBinding>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(mBinding.toolbar)
        val property = mutableListOf(
            ValueAnimatorAction(mBinding),
            ObjectAnimatorAction(mBinding),
            AnimatorSetAction(mBinding),
            ValueAnimatorOfObjectAnim(mBinding),
            SinInterceptorAnim(mBinding),
            KeyFrameAnim(mBinding),
            FastAnim(mBinding),
        )
        currentAnim = property[0]
        mBinding.btnStart.setOnClickListener {
            currentAnim?.startAnim()
        }
        mBinding.btnReset.setOnClickListener {
            mBinding.ivTransx.translationX = 0f
        }
        mBinding.toolbar.setOnMenuItemClickListener {item->
            currentAnim = when (item.itemId) {
                R.id.objectanimator_anim->{
                    property[1]
                }
                R.id.animation_set->{
                    property[2]
                }
                R.id.valueanimator_ofobject_anim->{
                    property[3]
                }
                R.id.custom_interceptor->{
                    property[4]
                }
                R.id.keyframe_anim->{
                    property[5]
                }
                R.id.fastanim->{
                    property[6]
                }
                else->{
                    property[0]
                }
            }
            return@setOnMenuItemClickListener true
        }
    }

    override fun initBinding(): ActivityTransationxBinding =
        ActivityTransationxBinding.inflate(layoutInflater)

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_property, menu)
        return true
    }
}