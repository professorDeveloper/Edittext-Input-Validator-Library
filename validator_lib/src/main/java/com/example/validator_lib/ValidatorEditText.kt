package com.example.validator_lib

import android.content.Context
import android.content.res.TypedArray
import android.text.InputType
import android.text.method.DigitsKeyListener
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText


class ValidatorEditText(context: Context, attrs: AttributeSet?) :
    AppCompatEditText(context, attrs) {

    private var atributes: TypedArray =
        context.obtainStyledAttributes(attrs, R.styleable.ValidatorEditText)
    private val format: Int = atributes.getInt(R.styleable.ValidatorEditText_format, 0)

    init {
        setFormat(format)
    }


    private fun setFormat(format: Int) {
        when (format) {
            0 -> {
                inputType = InputType.TYPE_CLASS_TEXT
                hint = "Enter text"
            }
            1 -> {
                inputType = InputType.TYPE_TEXT_VARIATION_PHONETIC
                hint = "Telephone number"
            }
            2 -> {
                inputType = InputType.TYPE_CLASS_NUMBER
                keyListener = DigitsKeyListener.getInstance("0123456789.")
                hint = "IP address"
            }
            3 -> {
                inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
                transformationMethod = PasswordTransformationMethod.getInstance()
                hint = "Password"
            }
            4 -> {
                inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                hint = "Email Address"
            }
            4 -> {
                inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                hint = "Email Address"
            }
            7 -> {
                inputType = InputType.TYPE_CLASS_NUMBER
                hint = "Zip Code"
            }
            8 -> {
                inputType = InputType.TYPE_CLASS_TEXT
                hint = "Year"
            }
            9 -> {
                inputType = InputType.TYPE_CLASS_TEXT
                hint = "User ID"
            }


        }
    }

    fun setErrorMessage(msg: String) {
        setCompoundDrawablesWithIntrinsicBounds(
            0,
            0,
            R.drawable.ic_round_error_24,
            0
        )
        error = msg
    }

    fun isCorrectFilled(): Boolean {
        var checker = false
        when (format) {
            0 -> {
                if (text.toString().isNotEmpty())
                    checker = true
            }
            1 -> {
                val phonePattern = "\\+[0-9]+".toRegex()
                checker = text.toString().matches(phonePattern)
            }
            2 -> {
                val ipPattern = "[0-9]+\\.[0-9]+\\.[0-9]+\\.[0-9]+".toRegex()
                checker = text.toString().matches(ipPattern)
            }
            3 -> {
                if (text.toString().isNotEmpty())
                    checker = true
            }
            4 -> {
                val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
                checker = text.toString().matches(emailPattern)
            }
            8 -> {
                val emailPattern = "^\\d{4}".toRegex()
                checker = text.toString().matches(emailPattern)
            }

            7 -> {
                val emailPattern = "^\\d{5}(?:-\\d{4})?\$".toRegex()
                checker = text.toString().matches(emailPattern)
            }
            9 -> {
               if (text.toString().isNotEmpty()){
                   checker = true
               }
            }
        }
        return checker
    }

    fun isNotEmpty(): Boolean {
        if (text!!.isEmpty()) {
            setErrorMessage("Fill blanks!")
        }
        return text!!.isNotEmpty()
    }

    fun checkCorrectAndSetError() {
        if (isNotEmpty()) {
            if (!isCorrectFilled()) {
                setErrorMessage("Incorrect format!")
            } else {
                setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0,
                    0,
                    0
                )
                setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.ic_round_check_circle_24,
                    0
                )
            }
        }
    }


}