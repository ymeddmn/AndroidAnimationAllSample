package com.ananananzhuo.anim

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ananananzhuo.anim.second.AnimatedStateListDrawableActivity
import com.ananananzhuo.anim.second.StateListAdapterAnimActivity
import com.ananananzhuo.anim.second.propertyanim.ValueAnimTransXActivity
import com.ananananzhuo.mvvm.activity.CustomAdapterActivity
import com.ananananzhuo.mvvm.bean.bean.ItemData

class MainActivity : CustomAdapterActivity() {
    override fun getAdapterDatas(): MutableList<ItemData> = mutableListOf(
        ItemData(title = "属性动画ValueAnimator", activity = ValueAnimTransXActivity::class.java){
            startActivity(Intent(this,it.itemData.activity))
        },
        ItemData(title = "使用自定义selector动画实现控件状态变化动画效果", activity = StateListAdapterAnimActivity::class.java){
            startActivity(Intent(this,it.itemData.activity))
        }
        ,
        ItemData(title = "使用AnimatedStateListDrawable实现动画", activity = AnimatedStateListDrawableActivity::class.java){
            startActivity(Intent(this,it.itemData.activity))
        }
    )

    override fun showFirstItem(): Boolean = true

}
