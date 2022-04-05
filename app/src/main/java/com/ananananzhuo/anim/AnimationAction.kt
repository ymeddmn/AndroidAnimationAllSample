package com.ananananzhuo.anim

import androidx.viewbinding.ViewBinding

/**
 * author  :mayong
 * function:
 * date    :2022/4/4
 **/
abstract class AnimationAction<T:ViewBinding>(val mBinding: T ) {
    abstract  fun startAnim()

}