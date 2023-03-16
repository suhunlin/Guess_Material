package com.suhun.guessmaterial

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.suhun.guessmaterial.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var tag = MainActivity::class.simpleName
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    var secretNumber:SecretNumber = SecretNumber()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        Log.d(tag, "secret number:${secretNumber.randomNumber}")

        binding.fab.setOnClickListener { view ->
            AlertDialog.Builder(this)
                .setTitle("Replay game")
                .setMessage("Are you sure?")
                .setPositiveButton("ok", {dialog, which ->
                    secretNumber.resetAll()
                    binding.contentLayout.userInput.text = null
                    binding.contentLayout.userInput.hint = "0"
                    binding.contentLayout.guessCount.text = "0"
                })
                .setNeutralButton("Cancel", null)
                .show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun runGuess(view: View){
        var message:String = secretNumber
            .verify(resources, binding.contentLayout.userInput.text.toString().toInt())
        var guessResult:Boolean = if(message.equals("You got it!!!")
            || message.equals("Excellent! The number is ${secretNumber.randomNumber}"))
            true else false
        binding.contentLayout.guessCount.text = secretNumber.count.toString()
        AlertDialog.Builder(this)
            .setTitle("Guess message").setMessage(message)
            .setPositiveButton("ok", {dialog, which->
                if(guessResult){
                    val intent:Intent = Intent(this, RecordActivity::class.java)
                    intent.putExtra("COUNT", secretNumber.count)
                    startActivity(intent)
                }

            })
            .show()
    }
}