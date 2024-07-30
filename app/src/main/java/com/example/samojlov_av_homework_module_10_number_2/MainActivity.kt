package com.example.samojlov_av_homework_module_10_number_2

import android.graphics.Color
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.samojlov_av_homework_module_10_number_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var toolbarMain: androidx.appcompat.widget.Toolbar

    private lateinit var textET: EditText

    private lateinit var generateRandomBT: Button

    private var generateRandomBTData = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
//        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        init()

        registerForContextMenu(textET)

        setSupportActionBar(toolbarMain)
        title = getString(R.string.toolbar_title)
        toolbarMain.setLogo(R.drawable.toolbar_icon)

        generateRandomBT.setOnClickListener(this)
    }

    private fun init() {
        toolbarMain = binding.toolbarMain
        textET = binding.textET
        generateRandomBT = binding.generateRandomBT
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var result = ""
        if (generateRandomBTData) when (textET.text.toString().toInt()) {
            in 1..10 -> result = "a"
            in 11..20 -> result = "b"
            in 21..30 -> result = "c"
            in 31..40 -> result = "d"
            in 41..50 -> result = "e"
        } else result = textET.text.toString()

        when (item.itemId) {
            R.id.menu_change_color -> {
                when (result) {
                    "1" -> textET.setBackgroundColor(Color.parseColor("#ffa500"))
                    "2" -> textET.setBackgroundColor(Color.parseColor("#ffff00"))
                    "3" -> textET.setBackgroundColor(Color.parseColor("#008000"))
                    "4" -> textET.setBackgroundColor(Color.parseColor("#0000ff"))
                    "5" -> textET.setBackgroundColor(Color.parseColor("#ff0000"))
                    "a" -> {
                        textET.setBackgroundColor(Color.parseColor("#ff0000"))
                        generateRandomBTData = false
                    }

                    "b" -> {
                        textET.setBackgroundColor(Color.parseColor("#ffa500"))
                        generateRandomBTData = false
                    }

                    "c" -> {
                        textET.setBackgroundColor(Color.parseColor("#ffff00"))
                        generateRandomBTData = false
                    }

                    "d" -> {
                        textET.setBackgroundColor(Color.parseColor("#008000"))
                        generateRandomBTData = false
                    }

                    "e" -> {
                        textET.setBackgroundColor(Color.parseColor("#0000ff"))
                        generateRandomBTData = false
                    }

                    else -> textET.setBackgroundColor(Color.parseColor("#ffffff"))
                }
            }

            R.id.menu_exit -> finish()
        }

        return super.onContextItemSelected(item)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.generateRandomBT -> {
                val randomNumber = (1..50).random().toString()
                textET.setText(randomNumber)
                generateRandomBTData = true
            }
        }
    }
}
