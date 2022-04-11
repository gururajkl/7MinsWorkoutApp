package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.a7minutesworkout.databinding.ActivityBmiactivityBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {
    companion object {
        private const val METRIC_UNIT_VIEW = "METRIC_UNIT_VIEW"
        private const val US_UNIT_VIEW = "US_UNIT_VIEW"
    }

    private var currentVisibleView: String = METRIC_UNIT_VIEW
    private var binding: ActivityBmiactivityBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiactivityBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarBMI)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "Calculate BMI"
        }
        binding?.toolbarBMI?.setNavigationOnClickListener {
            onBackPressed()
        }

        makeMetricUnitsVisible()

        binding?.rgUnits?.setOnCheckedChangeListener{_, checkId: Int ->
            if (checkId == R.id.rbMetricUnits) {
                makeMetricUnitsVisible()
            } else {
                makeUSUnitsVisible()
            }
        }

        binding?.bmiBtnResult?.setOnClickListener {
            if (currentVisibleView == METRIC_UNIT_VIEW) {
                if (validateUnits()) {
                    binding?.llDisplayBmiResult?.visibility = View.VISIBLE
                    calcBMI()
                } else {
                    Toast.makeText(this, "Please enter the valid values", Toast.LENGTH_SHORT).show()
                }
            } else {
                if (validateUSUnits()) {
                    binding?.llDisplayBmiResult?.visibility = View.VISIBLE
                    calcUSBMI()
                } else {
                    Toast.makeText(this, "Please enter the valid values", Toast.LENGTH_SHORT).show()
                }
            }
        }

//        binding?.rbUSUnits?.setOnClickListener {
//            binding?.etFeet?.visibility = View.VISIBLE
//            binding?.etInch?.visibility = View.VISIBLE
//            binding?.etHeight?.visibility = View.INVISIBLE
//        }
//
//        binding?.rbMetricUnits?.setOnClickListener {
//            binding?.etFeet?.visibility = View.INVISIBLE
//            binding?.etInch?.visibility = View.GONE
//            binding?.etHeight?.visibility = View.VISIBLE
//        }
    }

    private fun validateUnits(): Boolean {
        var isValid = true
        if (binding?.etHeight?.text.toString().isEmpty() || binding?.etWeight?.text.toString().isEmpty()) {
            isValid = false
        }
        return isValid
    }

    private fun validateUSUnits(): Boolean {
        var isValid = true
        if (binding?.etFeet?.text.toString().isEmpty() || binding?.etWeight?.text.toString().isEmpty() || binding?.etInch?.text.toString().isEmpty()) {
            isValid = false
        }
        return isValid
    }

    private fun calcBMI() {
        val weight: Float = binding?.etWeight?.text.toString().toFloat()
        val height: Float = binding?.etHeight?.text.toString().toFloat() / 100
        val bmi = weight / (height * height)
        displayBMIResult(bmi)
    }

    private fun calcUSBMI() {
        val weight: Float = binding?.etWeight?.text.toString().toFloat()
        val feet: String = binding?.etFeet?.text.toString()
        val inch: String = binding?.etInch?.text.toString()
        val heightValue = inch.toFloat() + feet.toFloat() * 12
        val bmi = 703 * (weight / (heightValue * heightValue))
        displayBMIResult(bmi)
    }

    private fun displayBMIResult(bmi: Float) {
        lateinit var bmiLabel: String
        lateinit var bmiDesc: String

        if (bmi.compareTo(15f) <= 0) {
            bmiLabel = "Very severely underweight"
            bmiDesc = "Oops! You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0) {
            bmiLabel = "Severely underweight"
            bmiDesc = "Oops! You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0) {
            bmiLabel = "Underweight"
            bmiDesc = "Oops! You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0) {
            bmiLabel = "Normal"
            bmiDesc = "Congratulations! You're in a good shape"
        } else if (bmi.compareTo(25f) > 0 && bmi.compareTo(30f) <= 0) {
            bmiLabel = "Overweight"
            bmiDesc = "Oops! You really need to take better care of yourself! Workout more for the best shape!"
        } else if (bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0) {
            bmiLabel = "Obese class | (Moderately obese)"
            bmiDesc = "Oops! You really need to take better care of yourself! Workout more for the best shape!"
        } else if (bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0) {
            bmiLabel = "Obese class || (Severely obese)"
            bmiDesc = "OMG! You're in a dangerous condition act now!"
        } else {
            bmiLabel = "Obese class ||| (Very Severely obese)"
            bmiDesc = "OMG! You're in a dangerous condition act now!"
        }

        binding?.tvBMIValue?.text = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()
        binding?.tvBMIType?.text = bmiLabel
        binding?.tvBMIDesc?.text = bmiDesc
    }

    private fun makeMetricUnitsVisible() {
        binding?.etWeight?.hint = "Weight (in kg)"
        currentVisibleView = METRIC_UNIT_VIEW
        binding?.etHeight?.visibility = View.VISIBLE
        binding?.etFeet?.visibility = View.INVISIBLE
        binding?.etInch?.visibility = View.GONE
        binding?.llDisplayBmiResult?.visibility = View.GONE
        binding?.etWeight?.text!!.clear()
        binding?.etHeight?.text!!.clear()
    }

    private fun makeUSUnitsVisible() {
        binding?.etWeight?.hint = "Weight (in pounds)"
        currentVisibleView = US_UNIT_VIEW
        binding?.etFeet?.visibility = View.VISIBLE
        binding?.etInch?.visibility = View.VISIBLE
        binding?.etHeight?.visibility = View.INVISIBLE
        binding?.llDisplayBmiResult?.visibility = View.GONE
        binding?.etWeight?.text!!.clear()
        binding?.etHeight?.text!!.clear()
        binding?.etFeet?.text!!.clear()
        binding?.etInch?.text!!.clear()
    }
}