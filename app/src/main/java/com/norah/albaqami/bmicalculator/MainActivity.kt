package com.norah.albaqami.bmicalculator

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.*
import androidx.cardview.widget.CardView
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val weight = findViewById<EditText>(R.id.weight_input)
        val height = findViewById<EditText>(R.id.height_input)
        val calculateButton = findViewById<Button>(R.id.calculate)
        val bmi = findViewById<TextView>(R.id.bmi_number)
        val bmiStatus = findViewById<TextView>(R.id.bmi_status)
        val bmiView = findViewById<CardView>(R.id.bmiView)
        val calculateAgainButton = findViewById<TextView>(R.id.again)

        calculateButton.setOnClickListener {
            var weightValue = 0.0
            var heightValue = 0.0
            if (weight.text.toString().isNotEmpty()) {
                weightValue = weight.text.toString().toDouble()
            }
            if (height.text.toString().isNotEmpty()) {
                heightValue = (height.text.toString().toDouble() / 100)
            }
            if (weightValue > 0.0 && heightValue > 0.0) {
                val bmiValue = String.format("%.2f", weightValue / heightValue.pow(2))
                bmi.text = bmiValue
                bmiStatus.text = bmiStatusValue(weightValue / heightValue.pow(2))
                bmiView.visibility = VISIBLE
                calculateButton.visibility = GONE
            } else
                Toast.makeText(this, getString(R.string.Please), Toast.LENGTH_LONG).show()
        }

        calculateAgainButton.setOnClickListener {
            bmiView.visibility = GONE
            calculateButton.visibility = VISIBLE
            weight.text.clear()
            height.text.clear()
            height.requestFocus()
        }


        //Change bottom navigation bar color
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.dark_blue))
        }

    }
    private fun bmiStatusValue(bmi: Double): String {
        lateinit var bmiStatus: String
        if (bmi < 18.5)
            bmiStatus = getString(R.string.Underweight)
        else if (bmi >= 18.5 && bmi < 25)
            bmiStatus = getString(R.string.Normal)
        else if (bmi >= 25 && bmi < 30)
            bmiStatus = getString(R.string.Overweight)
        else if (bmi > 30)
            bmiStatus = getString(R.string.Obese)
        return bmiStatus
    }
}