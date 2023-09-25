package com.smoothapp.notionshortcut.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.smoothapp.notionshortcut.R
import com.smoothapp.notionshortcut.databinding.ActivityShortcutBinding

class ShortcutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShortcutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShortcutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = this.getColor(R.color.transparent)

        binding.apply {

        }
    }
}