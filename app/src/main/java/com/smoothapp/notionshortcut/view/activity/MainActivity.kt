package com.smoothapp.notionshortcut.view.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import com.smoothapp.notionshortcut.R
import com.smoothapp.notionshortcut.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = this.getColor(R.color.transparent)

        binding.apply {

            root.setOnClickListener {
                val intent = Intent(Intent.ACTION_EDIT, Uri.EMPTY, this@MainActivity, ShortcutActivity::class.java)

                val shortcut = ShortcutInfoCompat.Builder(this@MainActivity, "test2")
                    .setShortLabel("test2")
                    .setLongLabel("test2_longtext")
                    .setIntent(intent)
                    .build()
                ShortcutManagerCompat.pushDynamicShortcut(this@MainActivity, shortcut)
            }

        }
    }
}