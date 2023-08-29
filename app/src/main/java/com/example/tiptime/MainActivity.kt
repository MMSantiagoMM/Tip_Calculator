package com.example.tiptime

import  androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculate.setOnClickListener {
            calculateTip()

        }

    }
     private fun calculateTip(){
        val stringInTextField = binding.costOfServiceEditText.text.toString()
        val cost = stringInTextField.toDoubleOrNull()

        if(cost == null){
            binding.tipResult.text = ""
            return
        }
        val selectId = binding.tipOptions.checkedRadioButtonId
        val roundUp = binding.setUpTrip.isChecked
        val tipPercentaje = when(selectId){
            R.id.option_twenty_percent -> 0.20
            R.id.option_fifteen_percent -> 0.18
            else -> 0.15
        }
        var tip = cost * tipPercentaje
        if(roundUp){
            tip = ceil(tip)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

        binding.tipResult.text = getString(R.string.tip_amount,formattedTip)
    }
}