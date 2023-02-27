package com.azamovhudstc.pdpgitmasala2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.azamovhudstc.pdpgitmasala2.databinding.ActivityMainBinding
import com.example.validator_lib.ValidatorSpinner

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpSpinner()
        setListeners()
    }
    private fun setUpSpinner() {
        val adapter = ValidatorSpinner.ListExampleAdapter(this)

        binding.validatorSpinner.setAdapter(adapter)
    }


    private fun setListeners() {
        binding.apply {
            clearBtn.setOnClickListener {
                validatorSpinner.setImgGone()
                emailEt.text?.clear()
                firstEt.text?.clear()
                phoneEt.text?.clear()
                ipaddressEt.text?.clear()
                yearEt.text?.clear()
                descEt.text?.clear()
                doublePasswordEt.clear()
                doubleEt.clear()
                emailEt.setCompoundDrawables(null, null, null, null)
                firstEt.setCompoundDrawables(null, null, null, null)
                phoneEt.setCompoundDrawables(null, null, null, null)
                ipaddressEt.setCompoundDrawables(null, null, null, null)
                yearEt.setCompoundDrawables(null, null, null, null)
                descEt.setCompoundDrawables(null, null, null, null)
                yearEt.setCompoundDrawables(null, null, null, null)

            }
            submitBtn.setOnClickListener {
                checkData()
                if (dataFull())
                    Toast.makeText(this@MainActivity, "Success", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun dataFull(): Boolean {
        binding.apply {

            return (isFull() && validatorSpinner.isItemSelected()
                    && doubleEt.isNotEmpty())
        }
    }

    private fun isFull(): Boolean {
        binding.apply {
            return (firstEt.isNotEmpty() && emailEt.isNotEmpty() && phoneEt.isNotEmpty() && ipaddressEt.isCorrectFilled() &&
                    descEt.isNotEmpty() && yearEt.isNotEmpty() && validatorSpinner.isItemSelected())
        }
    }


    private fun checkData() {
        binding.apply {
            doubleEt.checkTextAndSetError()
            doublePasswordEt.checkTextAndSetError()
            firstEt.checkCorrectAndSetError()
            emailEt.checkCorrectAndSetError()
            phoneEt.checkCorrectAndSetError()
            ipaddressEt.checkCorrectAndSetError()
            descEt.checkCorrectAndSetError()
            yearEt.checkCorrectAndSetError()
            validatorSpinner.isItemSelected()

        }
    }

}