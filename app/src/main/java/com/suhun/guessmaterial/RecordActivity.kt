package com.suhun.guessmaterial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.suhun.guessmaterial.databinding.ActivityRecordBinding
import kotlin.math.log

private lateinit var binding:ActivityRecordBinding

class RecordActivity : AppCompatActivity() {
    var tag = RecordActivity::class.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recordCount.text = intent.getIntExtra("COUNT",-1).toString()+"times"
    }
}