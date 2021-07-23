package com.example.mvvmretrofitroomkt.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmretrofitroomkt.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Init RecyclerView & adapter
        initList()
    }

    // Init RecyclerView & adapter
    private fun initList() {
        // Init RecyclerView adapter
        rvBook.adapter = BookRVAdapter(this)
        rvBook.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false)
        rvBook.addItemDecoration(DividerItemDecoration(this, 1))
    }
}