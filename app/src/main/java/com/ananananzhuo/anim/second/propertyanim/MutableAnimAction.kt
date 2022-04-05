package com.ananananzhuo.anim.second.propertyanim

import android.animation.*
import android.util.Property
import androidx.viewbinding.ViewBinding
import com.ananananzhuo.anim.AnimationAction
import com.ananananzhuo.anim.databinding.ActivityTransationxBinding
import com.ananananzhuo.mvvm.utils.logEE
import kotlin.math.cos
import kotlin.math.sin

/**
 * author  :mayong
 * function:
 * date    :2022/4/4
 **/

/**
 * 使用ValueAnimator实现属性平移动画
 */
class ValueAnimatorAction(binding: ActivityTransationxBinding) :
    AnimationAction<ActivityTransationxBinding>(binding) {
    override fun startAnim() {
        ValueAnimator.ofFloat(0f, 600f).apply {
            start()
            addUpdateListener {
                duration = 2000
                mBinding.ivTransx.translationX = it.animatedValue as Float
            }
        }
    }
}

/**
 * 使用ObjectAnimator实现属性平移动画
 */
class ObjectAnimatorAction(binding: ActivityTransationxBinding) :
    AnimationAction<ActivityTransationxBinding>(binding) {
    override fun startAnim() {
        ObjectAnimator.ofFloat(mBinding.ivTransx, "translationX", 0f, 900f).apply {
            duration = 1000
            start()
        }
    }
}

/**
 * 本例使用AnimatorSet实现两个动画一起运行的效果
 */
class AnimatorSetAction(binding: ActivityTransationxBinding) :
    AnimationAction<ActivityTransationxBinding>(binding) {
    override fun startAnim() {
        val maxTransY = -500f
        val maxTransX = 800f
        val durationTime = 4 * 1000L
        //animX实现水平平移的效果
        val animX = ObjectAnimator.ofFloat(mBinding.ivTransx, "translationX", 0f, maxTransX).apply {
            duration = durationTime
        }
        val animY = ObjectAnimator.ofFloat(mBinding.ivTransx, "translationY", 0f, 0f)
        //animY实现一个二次函数的运动曲线，函数公式为 y=(1/320f)*x*x -(5/2)x,也就是从ImageView原点出发，最高点(400,-500f),最终点（800,0）
        animY.setEvaluator { fraction, startValue, endValue ->
            val currX0 = fraction * maxTransX
            val a = 1 / 320f
            val currY = a * currX0 * currX0 - (5 / 2f) * currX0
            return@setEvaluator currY
        }
        animY.duration = durationTime
        AnimatorSet().apply {
            play(animX).with(animY)
            start()
        }
    }
}

class Point(val x: Float, val y: Float)
class ValueAnimatorOfObjectAnim(binding: ActivityTransationxBinding) :
    AnimationAction<ActivityTransationxBinding>(binding) {
    override fun startAnim() {
        //第一个参数必须是TypeEvaluatar，TypeEvalutar中的参数可以是任意类型
        val anim = ValueAnimator.ofObject(
            { fraction: Float, startValue: Any, endValue: Any ->
                startValue as Point
                endValue as Point
                val xDistance = endValue.x - startValue.x
                val x = fraction * xDistance
                val y = 250 * sin(x.toDouble() / 50f)
                Point(x, y.toFloat())//这里返回的类型，必须和ofObject后两个参数类型一致
            },
            Point(0f, 0f),//动画开始的value
            Point(800f, 0f)//动画结束的value
        )
        anim.addUpdateListener {//监听动画进度，在每次动画更新节点的位置更好控件的偏移位置
            val value = it.animatedValue as Point
            mBinding.ivTransx.translationX = value.x
            mBinding.ivTransx.translationY = value.y
        }
        anim.duration = 5000
        anim.start()
        anim.interpolator
    }
}

/**
 * 使用差值器实现水平正弦重复往返运动
 */
class SinInterceptorAnim(binding: ActivityTransationxBinding) :
    AnimationAction<ActivityTransationxBinding>(binding) {
    override fun startAnim() {
        ObjectAnimator.ofFloat(mBinding.ivTransx, "translationX", 400f, 700f).apply {
            duration = 5000
            interpolator = TimeInterpolator { input ->
                val output = sin(input * 20)
                logEE("输入:$input  输出:$output")
                output
            }
            start()
        }
    }
}

class KeyFrameAnim(binding: ActivityTransationxBinding) :
    AnimationAction<ActivityTransationxBinding>(binding) {
    override fun startAnim() {
        val key1 = Keyframe.ofFloat(0.25f, 3.14f/2)
        val key2 = Keyframe.ofFloat(0.5f, 3.14f)
        val key3 = Keyframe.ofFloat(0.75f, 3.14f*3/2)
        val key4 = Keyframe.ofFloat(1f, 3.14f*2)
        val holder = PropertyValuesHolder.ofKeyframe("1", key1, key2, key3, key4)
        ObjectAnimator.ofPropertyValuesHolder(mBinding.ivTransx, holder).apply {
            duration = 5000
            addUpdateListener {
                val value = it.animatedValue as Float
                val x = 400f * sin(value)
                val y = 400f * cos(value)
                logEE("角度：$value x:$x   y:$y")
                mBinding.ivTransx.translationX = x+400
                mBinding.ivTransx.translationY = y
            }
            start()
        }
    }
}

class FastAnim(binding: ActivityTransationxBinding) :
    AnimationAction<ActivityTransationxBinding>(binding) {
    override fun startAnim() {
        mBinding.ivTransx.animate().xBy(700f).yBy(-200f).rotation(360f).scaleX(2f).scaleYBy(2f).apply {
            duration = 5000
            start()
        }
    }
}