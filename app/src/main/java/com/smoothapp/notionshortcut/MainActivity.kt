package com.smoothapp.notionshortcut

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import com.smoothapp.notionshortcut.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            root.setOnClickListener {
                val intent = Intent(Intent.ACTION_MAIN)

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