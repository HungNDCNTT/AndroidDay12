package com.hungnd.androidday12

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.hungnd.androidday12.lesson1.Lesson1Activity
import com.hungnd.androidday12.lesson2.Lesson2Activity
import com.hungnd.androidday12.lesson3.Lesson3Activity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_bai1.setOnClickListener(this)
        btn_Bai2.setOnClickListener(this)
        btn_Bai3.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn_bai1 -> {
                startActivity(Intent(this, Lesson1Activity::class.java))
            }
            R.id.btn_Bai2 -> {
                startActivity(Intent(this, Lesson2Activity::class.java))
            }
            R.id.btn_Bai3 -> {
                startActivity(Intent(this, Lesson3Activity::class.java))
            }
        }
    }
}