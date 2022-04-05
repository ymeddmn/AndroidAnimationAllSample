package com.ananananzhuo.anim.second

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ananananzhuo.anim.R
import com.ananananzhuo.anim.databinding.ActivityPropertyAnimBinding
import com.ananananzhuo.mvvm.activity.ViewBindingActivity

class PropertyAnimActivity : ViewBindingActivity<ActivityPropertyAnimBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun initBinding(): ActivityPropertyAnimBinding = ActivityPropertyAnimBinding.inflate(layoutInflater)
}