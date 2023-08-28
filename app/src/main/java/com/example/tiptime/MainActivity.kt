package com.example.tiptime

import  androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculate.setOnClickListener {
            calculateCheck()
        }


    }
    private fun calculateCheck(){
        val cuenta = binding.totalCost.text.toString()
        val costTotal = cuenta.toDoubleOrNull()
        if(costTotal==null){
            binding.tipResult.text = ""
            return
        }
        val radioGroup = binding.optionsTip.checkedRadioButtonId
        val switch = binding.roundUpTip.isChecked
        val percent = when(radioGroup){
            R.id.twentyPercent -> 0.20
            R.id.eighteenPercent -> 0.18
            else -> 0.15
        }
        var tip = costTotal * percent
        if(switch){
            tip = ceil(tip)
        }

        var formmater = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tipTotal,formmater)

    }

}

